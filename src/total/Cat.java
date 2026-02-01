package total;

import total.Exeption.InputExeption;

import java.util.List;
import java.util.Random;
import java.util.Scanner;




public class Cat {
    private String name;
    private int age;
    private int satietyLevel;
    private int moodLevel;
    private int healthyLevel;

    public Cat(String name, int age) {
        Random rnd = new Random();
        this.name = name;
        this.age = age;
        this.satietyLevel = rnd.nextInt(21)+60;
        this.moodLevel = rnd.nextInt(21)+60;
        this.healthyLevel = rnd.nextInt(21+60);
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
        scanner.close();
        cats.add(new Cat(name, age));



    }
    public static int intValidate(String input)throws InputExeption {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            throw new InputExeption("Ввод должен быть целым числом!");
        }
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

}
