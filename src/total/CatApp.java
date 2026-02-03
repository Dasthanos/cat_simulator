package total;

import total.fileUtil.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CatApp {

    private List<Cat> cats = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int day = 1;

    public void run() {
        cats = FileUtil.readFile();

        if (cats == null || cats.isEmpty()) {
            System.out.println("Список котов пуст");
            return;
        }

        while (true) {
            cats.sort(Comparator.comparingDouble(Cat::getAverageLevel).reversed());

            System.out.println("\n📅 День " + day);
            CatTablePrinter.printTable(cats);

            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> doAction("feed");
                case "2" -> doAction("play");
                case "3" -> doAction("treat");
                case "4" -> nextDay();
                case "5" -> Cat.addCat(cats);
                case "0" -> {
                    System.out.println("👋 Выход");
                    return;
                }
                default -> System.out.println("❌ Неверный выбор");
            }
        }
    }

    private void doAction(String action) {
        if (cats.isEmpty()) {
            System.out.println("❗ Нет котов");
            return;
        }

        Cat cat = chooseCat();
        if (cat == null) return;

        if (cat.isActionDoneToday()) {
            System.out.println("❗ С этим котом сегодня уже выполняли действие");
            return;
        }

        switch (action) {
            case "feed" -> Cat.feedCat(cat);
            case "play" -> Cat.playCat(cat);
            case "treat" -> Cat.treatCat(cat);
        }

        System.out.println("✅ Действие выполнено с котом " + cat.getName());
    }

    private void printMenu() {
        System.out.println("""
                
                1 — Покормить кота
                2 — Поиграть с котом
                3 — Лечить кота
                4 — Следующий день
                5 — Завести нового кота
                0 — Выход
                """);
        System.out.print("Выбор: ");
    }

    private Cat chooseCat() {
        System.out.print("Введите номер кота: ");

        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index < 0 || index >= cats.size()) {
                System.out.println("❌ Неверный номер");
                return null;
            }
            return cats.get(index);
        } catch (NumberFormatException e) {
            System.out.println("❌ Нужно ввести число");
            return null;
        }
    }

    private void nextDay() {
        cats.forEach(Cat::nextDay);
        cats.forEach(cat -> cat.setActionDoneToday(false));

        day++;
        System.out.println("🌙 Наступил новый день");
    }
}