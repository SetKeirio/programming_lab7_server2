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
import java.util.*;

/**
 * Class which works with reading collection from file and writing collection to file.
 */
public class FileManager {
    private String envVar;
    private Gson gson = new Gson();
    private LabWorkAsker lwasker;

    public FileManager(String envVar, LabWorkAsker l){
        this.envVar = envVar;
        lwasker = l;
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
                if (collection.size() == 0){
                    Console.println("Первоначальная коллекция пуста");

                }
                else {
                    Console.println("Коллекция загружена.");
                }
                for (Integer i: collection.keySet()){
                    int first = collection.get(i).getId();
                    for (Integer i1: collection.keySet()){
                        int second = collection.get(i1).getId();
                        if (!(i1 == i) && (first == second)){
                            Console.printerr("Обнаружены одинаковые id! Генерируем новые!");
                            if (collection.isEmpty()){
                                second = 1;
                            }
                            else{
                                Iterator it = collection.keySet().iterator();
                                int max = 1;
                                while (it.hasNext()){
                                    LabWork k = collection.get(it.next());
                                    if (k.getId() > max){
                                        max = k.getId();
                                    }
                                }
                                second = max + 1;
                            }
                            collection.get(i1).setId(second);
                        }

                        if (second <= 0){
                            Console.printerr("Обнаружено отрицательное id! Генерируем новые!");
                            if (collection.isEmpty()){
                                second = 1;
                            }
                            else{
                                Iterator it = collection.keySet().iterator();
                                int max = 1;
                                boolean b = true;
                                while (it.hasNext()){
                                    LabWork k = collection.get(it.next());
                                    if (k.getId() > max){
                                        max = k.getId();
                                        b = false;
                                    }
                                }
                                if (!b){
                                    second = 1;
                                }
                                else{
                                    second = max + 1 > 0 ? max + 1 : 1;
                                }

                            }
                            collection.get(i1).setId(second);
                        }
                    }
                }
                for (Integer l: collection.keySet()){
                    LabWork temp = lwasker.checkElement(collection.get(l));
                    collection.remove(l);
                    collection.put(l, temp);
                }
                return collection;

            } catch (FileNotFoundException e) {
                Console.printerr("Файл с названием " + System.getenv().get(envVar) + " не найден.");
                System.exit(0);
            } catch (NoSuchElementException e) {
                Console.printerr("Файл с названием " + System.getenv().get(envVar) + " не может быть конвертирован в коллекцию.");
                //System.exit(0);
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerr(exception);
                Console.printerr("В загрузочном файле не обнаружена необходимая коллекция!");
                //System.exit(0);
            } catch (IllegalStateException exception) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(0);
            }

        }
            else{
                Console.printerr("Переменная окружения не найдена.");
                System.exit(0);
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
                System.exit(0);
            }
        }
        else {
            Console.printerr("Переменная окружения не найдена.");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return "Класс для работы с файлом " + envVar;
    }
}
