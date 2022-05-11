package util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import core.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class which works with reading collection from file and writing collection to file.
 */
public class FileManager {
    private String envVar;
    private Gson gson = new Gson();

    public FileManager(String envVar){
        this.envVar = envVar;
    }

    /**
     * Reads the collection from file (required from start) (JSON)
     * @return
     */
    public HashMap<Integer, LabWork> readCollection(){
        if (System.getenv(envVar) != null) {
            try (Scanner sc = new Scanner(new File(System.getenv().get(envVar)))) {
                HashMap<Integer, LabWork> collection;
                Type type = new TypeToken<HashMap<Integer, LabWork>>() {

                }.getType();
                collection = gson.fromJson(sc.nextLine().trim(), type);
                Console.println("Коллекция загружена.");
                return collection;
            } catch (FileNotFoundException e) {
                Console.printerr("Файл с названием " + System.getenv().get(envVar) + " не найден.");
            } catch (NoSuchElementException e) {
                Console.printerr("Файл с названием " + System.getenv().get(envVar) + " пуст.");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerr("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(0);
            }
        }
            else{
                Console.printerr("Переменная окружения не найдена.");
            }
            return new HashMap<Integer, LabWork>();
    }

    /**
     * Writes collection to the file (JSON)
     * @param collection
     */
    public void writeCollection(Map<Integer, LabWork> collection){
        if (System.getenv(envVar) != null){
            try (PrintWriter pv = new PrintWriter(new File(System.getenv().get(envVar)))){
                pv.write(gson.toJson(collection));
                Console.println("Коллекция сохранена.");
            }
            catch (IOException e) {
                Console.printerr("Невозможно открыть файл с названием " + System.getenv().get(envVar) + ".");
            }
        }
        else {
            Console.printerr("Переменная окружения не найдена.");
        }
    }

    @Override
    public String toString() {
        return "Класс для работы с файлом " + envVar;
    }
}
