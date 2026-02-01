package total.fileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import total.Cat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class FileUtil {

    public static List<Cat> readFile() {
        Gson gson = new Gson();
        try {
            String json = Files.readString(Paths.get("src/total/cats.json"));
            Cat[] cats = gson.fromJson(json, Cat[].class);
            return new ArrayList<>(List.of(cats));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void writeFile(List<Cat> cats){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path path = Paths.get("src/total/cats.json");
        String newJson = gson.toJson(cats);
        byte[] strToByte = newJson.getBytes();
        try {
            Files.write(path, strToByte);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
