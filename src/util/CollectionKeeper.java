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

public class CollectionKeeper {
    private String envVar;
    private Gson gson = new Gson();

    public CollectionKeeper(String envVar){
        this.envVar = envVar;
    }

    public HashMap<Integer, LabWork> readCollection(){
        String env = System.getenv().get(envVar);
        if (env != null) {
            File file = new File(env);
            try (Scanner collectionScanner = new Scanner(file)){
                HashMap<Integer, LabWork> collection;
                Type type = new TypeToken<HashMap<Integer, LabWork>>() {

                }.getType();
                collection = gson.fromJson(collectionScanner.nextLine().trim(), type);
                if (collection.size() == 0){
                    Console.println("Первоначальная коллекция пуста");
                }
                else {
                    Console.println("Коллекция загружена.");
                    collection.values().stream().forEach(a -> a.refreshCreation());
                }
                return collection;

            }
            catch (FileNotFoundException e){
                Console.printerr("Файл не найден!");
            }
            catch (JsonParseException | NullPointerException exception) {
                Console.printerr(exception);
                Console.printerr("В загрузочном файле не обнаружена необходимая коллекция!");
                //System.exit(0);
            }
            catch (NoSuchElementException e) {
                Console.printerr("Файл с названием " + System.getenv().get(envVar) + " не может быть конвертирован в коллекцию.");
                //System.exit(0);
            }
            catch (IllegalStateException exception) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(0);
            }
        }
        else{
            Console.printerr("Не обнаружена переменная загрузочного файла!");
            return new HashMap<Integer, LabWork>();
        }
        return new HashMap<Integer, LabWork>();
    }

    public void writeCollection(Map<Integer, LabWork> collection){
        String env = System.getenv().get(envVar);
        if (env != null){
            File file = new File(env);
            try (PrintWriter pv = new PrintWriter(file)){
                pv.write(gson.toJson(collection));
                Console.println("Коллекция сохранена.");
            }
            catch (IOException e) {
                Console.printerr("Невозможно открыть файл с названием " + env + ".");
                System.exit(0);
            }
        }

    }
}
