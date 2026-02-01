package total;

import java.util.List;

public class CatTablePrinter {
    public static void printTable(List<Cat> cats) {


        System.out.printf(
                "%-10s | %-5s | %-8s | %-6s | %-8s | %-10s%n",
                "Name", "Age", "Satiety", "Mood", "Health", "Average"
        );
        System.out.println("---------------------------------------------------------------");

        for (Cat cat : cats) {
            double average = calculateAverage(cat);

            System.out.printf(
                    "%-10s | %-5d | %-8d | %-6d | %-8d | %-10.2f%n",
                    cat.getName(),
                    cat.getAge(),
                    cat.getSatietyLevel(),
                    cat.getMoodLevel(),
                    cat.getHealthyLevel(),
                    average
            );
        }
    }

    private static double calculateAverage(Cat cat) {
        return (cat.getSatietyLevel()
                + cat.getMoodLevel()
                + cat.getHealthyLevel()) / 3.0;
    }
}
