package util;

import core.LabWork;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Class which works with collection.
 * Includes methods for all commands which work with collection.
 */
public class CollectionManager {
    private FileManager fileManager;
    private Map<Integer, LabWork> collection = new HashMap<>();

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    private ZonedDateTime creationTime;

    public ZonedDateTime getSaveTime() {
        return saveTime;
    }

    private ZonedDateTime saveTime;

    public CollectionManager(FileManager fm){
        fileManager = fm;
        loadCollection();
    }

    /**
     * Loads the collection from file.
     */
    public void loadCollection(){
        collection = fileManager.readCollection();
        creationTime = ZonedDateTime.now();
    }

    /**
     * Saves the collection into file.
     */
    public void saveCollection(){
        fileManager.writeCollection(collection);
        saveTime = ZonedDateTime.now();
    }

    public String getType(){
        return collection.getClass().getName();
    }

    public int getSize(){
        return collection.size();
    }

    /**
     * Returns string with all elements in collection.
     * @return answer
     */
    public String showAll(){
        String answer = "";
        if (getSize() == 0){
            return "Коллекция пока пуста.";
        }
        else {
            for (Integer i : collection.keySet()) {
                answer += "Key " + i + ": ";
                LabWork l = collection.get(i);
                answer += l + "\n";
            }
        }
        return answer;
    }

        public void clear(){
        collection.clear();
    }

    /**
     * Adds the element into collection.
     * @param lw
     * @param key
     */
    public void addToCollection(LabWork lw, Integer key){
        collection.put(key, lw);
    }

    /**
     * Returns the element by id.
     * @param id
     * @return answer
     */
    public LabWork getById(Integer id){
        Iterator it = collection.values().iterator();
        while (it.hasNext()){
            LabWork temp = (LabWork) it.next();
            if (temp.getId() == id){
                return temp;
            }
        }
        return null;
    }

    /**
     * Returns the element by key.
     * @param key
     * @return answer
     */
    public LabWork getByKey(Integer key){
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            Integer temp = (Integer) it.next();
            if (temp == key){
                return collection.get(temp);
            }
        }
        return null;
    }

    /**
     * Updates the element by id.
     * @param lw
     * @param key
     */
    public void updateById(LabWork lw, Integer key){
        collection.remove(key);
        addToCollection(lw, key);
    }

    /**
     * Removes from collection by key.
     * @param i
     */
    public void removeFromCollectionByKey(Integer i){
        collection.remove(i);
    }

    /**
     * Replaces the element if new element is greater.
     * @param lw
     * @param key
     */
    public void replaceIfGreater(LabWork lw, Integer key){
        if (lw.compareTo(collection.get(key)) > 0){
            updateById(lw, key);
        }
    }

    /**
     * Replaces the element if new element is lower.
     * @param lw
     * @param key
     */
    public void replaceIfLower(LabWork lw, Integer key){
        if (lw.compareTo(collection.get(key)) < 0){
            updateById(lw, key);
        }
    }

    /**
     * Returns all elements in String ordered by standard order.
     * @return
     */
    public String printDescending(){
        String answer = "";
        TreeMap<LabWork, Integer> temp = new TreeMap<>();
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            int key= (int) it.next();
            temp.put(collection.get(key), key);
        }
        it = temp.keySet().iterator();
        while (it.hasNext()){
            LabWork k = (LabWork) it.next();
            answer += k + "\n";
        }
        return answer;
    }

    /**
     * Removes all elements greater than argument.
     * @param key
     */
    public void removeGreater(Integer key){
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            int k = (int) it.next();
            if (k > key){
                removeFromCollectionByKey(k);
            }
        }
    }

    /**
     * Removes first elements with personal qualities equals to argument.
     * @param maximum
     */
    public void removeAnyByPersonalQualitiesMaximum(long maximum){
        Iterator it = collection.keySet().iterator();
        boolean stop = true;
        while (it.hasNext() && stop){
            int k = (int) it.next();
            if (collection.get(k).getPersonalQualitiesMaximum() ==  maximum){
                Console.println("Удалено одного LabWork произведено (id = " + collection.get(k).getId() + ")");
                removeFromCollectionByKey(k);
                stop = false;
            }
        }
    }

    /**
     * Returns all elements in String ordered by personal qualities.
     * @return
     */
    public String groupCountingByPersonalQualitiesMaximum(){
        String answer = "";
        Comparator<LabWork> comparator = new Comparator<LabWork>(){
            @Override
            public int compare(LabWork o1, LabWork o2) {
                if (o1.getPersonalQualitiesMaximum() - o2.getPersonalQualitiesMaximum() > 0){
                    return 1;
                }
                else if (o1.getPersonalQualitiesMaximum() - o2.getPersonalQualitiesMaximum() < 0){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        };
        TreeMap<LabWork, Integer> temp = new TreeMap<>(comparator);
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            int key= (int) it.next();
            temp.put(collection.get(key), key);
        }
        it = temp.keySet().iterator();
        long nowmax = 0;
        boolean start = true;
        int count = 0;
        while (it.hasNext()){
            LabWork k = collection.get(it.next());
            if (nowmax != k.getPersonalQualitiesMaximum()){
                if (start != true){
                    answer += "Количество элементов с максимумом " + nowmax + " равно " + count + "\n";
                }
                else{
                    start = false;
                }
                nowmax = k.getPersonalQualitiesMaximum();
                count = 1;
            }
            else{
                count += 1;
            }
        }
        answer += "Количество элементов с максимумом " + nowmax + " равно " + count;
        return answer;
    }

    /**
     * Generates next primary-key id.
     * @return
     */
    public Integer generateNextId(){
        if (collection.isEmpty()){
            return 1;
        }
        else{
            Iterator it = collection.keySet().iterator();
            int max = 0;
            while (it.hasNext()){
                LabWork k = collection.get(it.next());
                if (k.getId() > max){
                    max = k.getId();
                }
            }
            return max;
        }
    }





}
