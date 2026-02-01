package total;

import total.fileUtil.FileUtil;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Cat> cats = FileUtil.readFile();
        if (cats == null || cats.isEmpty()) {
            System.out.println("Список котов пуст");
            return;
        }
        cats.sort(Comparator.comparingDouble(Cat::getAverageLevel).reversed());

        CatTablePrinter.printTable(cats);
        Cat.addCat(cats);
        FileUtil.writeFile(cats);
        CatTablePrinter.printTable(cats);


    }

}
