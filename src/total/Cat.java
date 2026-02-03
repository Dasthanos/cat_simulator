package total;

import total.Exeption.InputExeption;
import total.fileUtil.FileUtil;

import java.util.List;
import java.util.Random;
import java.util.Scanner;




public class Cat {
    private String name;
    private int age;
    private int satietyLevel;
    private int moodLevel;
    private int healthyLevel;
    private boolean actionDoneToday = false;

    public boolean isActionDoneToday() {
        return actionDoneToday;
    }

    public void setActionDoneToday(boolean actionDoneToday) {
        this.actionDoneToday = actionDoneToday;
    }

    public void setSatietyLevel(int satietyLevel) {
        this.satietyLevel = satietyLevel;
    }

    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }

    public void setHealthyLevel(int healthyLevel) {
        this.healthyLevel = healthyLevel;
    }

    public Cat(String name, int age) {
        Random rnd = new Random();
        this.name = name;
        this.age = age;
        this.satietyLevel = rnd.nextInt(21)+60;
        this.moodLevel = rnd.nextInt(21)+60;
        this.healthyLevel = rnd.nextInt(21)+60;
    }

    public double getAverageLevel() {
        return (satietyLevel + moodLevel + healthyLevel) / 3.0;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSatietyLevel() {
        return satietyLevel;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public int getHealthyLevel() {
        return healthyLevel;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", satietyLevel=" + satietyLevel +
                ", moodLevel=" + moodLevel +
                ", healthyLevel=" + healthyLevel +
                '}';
    }

    public static void addCat(List<Cat> cats ){
        Scanner scanner = new Scanner(System.in);
        String name;
        int age;
        while (true){
            try {
                System.out.println("Введите имя кота: ");
                name = validateString(scanner.nextLine());
                System.out.println("Введите возраст кота" );
                age = intValidate(scanner.nextLine());
                break;
            }
            catch (InputExeption e){
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ещё раз.\n");
            }

        }
        cats.add(new Cat(name, age));
        FileUtil.writeFile(cats);



    }
    public static int intValidate(String input)throws InputExeption {
        int age;
        try {
            age = Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            throw new InputExeption("Ввод должен быть целым числом!");
        }
        if (age < 0 || age > 120)
            throw new InputExeption("Возраст должен быть от 0 до 120");
        return age;
    }

    static String validateString(String input)throws InputExeption{
        if (input == null || input.trim().isEmpty()){
            throw new InputExeption("Строка не должна быть пустой!");
        }
        input = input.trim();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new InputExeption("Строка не должна содержать цифры!");
            }
        }
        return input;
    }

    public static void feedCat(Cat cat){
        if (cat.isActionDoneToday()) {
            System.out.println("Вы уже выполняли действие с этим котом");
            return;
        }
        if (cat.getAge()<6){
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+7));
            cat.setSatietyLevel(Math.min(100, cat.getSatietyLevel()+7));
        }
        else if (cat.getAge()<11){
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+5));
            cat.setSatietyLevel(Math.min(100, cat.getSatietyLevel()+5));
        }
        else{
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+4));
            cat.setSatietyLevel(Math.min(100, cat.getSatietyLevel()+4));
        }
        System.out.println("Покормили");
        cat.setActionDoneToday(true);
    }

    public static void treatCat(Cat cat){
        if (cat.isActionDoneToday()) {
            System.out.println("Вы уже выполняли действие с этим котом");
            return;
        }
        if (cat.getAge()<6){
            cat.setMoodLevel(Math.max(0, cat.getMoodLevel()-3));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-3));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+7));
        }
        else if (cat.getAge()<11){
            cat.setMoodLevel(Math.max(0, cat.getMoodLevel()-5));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-5));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+5));
        }
        else{
            cat.setMoodLevel(Math.max(0, cat.getMoodLevel()-6));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-6));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+4));
        }
        System.out.println("Лечили");
        cat.setActionDoneToday(true);
    }

    public static void playCat(Cat cat){
        if (cat.isActionDoneToday()) {
            System.out.println("Вы уже выполняли действие с этим котом");
            return;
        }
        if (cat.getAge()<6){
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+7));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-3));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+7));
        }
        else if (cat.getAge()<11){
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+5));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-5));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+5));
        }
        else{
            cat.setMoodLevel(Math.min(100, cat.getMoodLevel()+4));
            cat.setSatietyLevel(Math.max(0, cat.getSatietyLevel()-6));
            cat.setHealthyLevel(Math.min(100, cat.getHealthyLevel()+4));
        }
        cat.setActionDoneToday(true);
        System.out.println("Поиграли");
    }

    public void nextDay() {
        Random rnd = new Random();
        int satietyChange = -(rnd.nextInt(5) + 1);

        int moodChange = rnd.nextInt(7) - 3;

        int healthChange = rnd.nextInt(7) - 3;

        satietyLevel = Math.max(0, Math.min(100, satietyLevel + satietyChange));
        moodLevel = Math.max(0, Math.min(100, moodLevel + moodChange));
        healthyLevel = Math.max(0, Math.min(100, healthyLevel + healthChange));


    }



}
