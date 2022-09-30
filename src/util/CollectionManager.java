package util;

import core.LabWork;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Class which works with collection.
 * Includes methods for all commands which work with collection.
 */
// for (LabWork x: new CollectionManager())
public class CollectionManager {
    private CollectionKeeper fileManager;
    private Map<Integer, LabWork> collection = new HashMap<>();

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    private ZonedDateTime creationTime;

    public ZonedDateTime getSaveTime() {
        return saveTime;
    }

    private ZonedDateTime saveTime;

    public CollectionManager(CollectionKeeper fm){
        fileManager = fm;
        creationTime = null;
        saveTime = null;
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
    /**public String showAll(){
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
     **/
    public String showAll(){
        if (getSize() == 0){
            return "Коллекция пока пуста.";
        }
        else{
            Comparator<Map.Entry<Integer,LabWork>> comparator = new Comparator<Map.Entry<Integer,LabWork>>(){
                @Override
                public int compare(Map.Entry<Integer,LabWork> o1, Map.Entry<Integer,LabWork> o2) {
                    long d1 = (long) o1.getValue().getCoordinates().getX() * (long) o1.getValue().getCoordinates().getX() + (long) o1.getValue().getCoordinates().getY() * (long) o1.getValue().getCoordinates().getY();
                    long d2 = (long) o2.getValue().getCoordinates().getX() * (long) o2.getValue().getCoordinates().getX() + (long) o2.getValue().getCoordinates().getY() * (long) o2.getValue().getCoordinates().getY();
                    if (d1 - d2 > 0){
                        return 1;
                    }
                    else if (d1 - d2 < 0){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            };
            return "Коллекция, отсортированная по увеличению модуля местоположения:\n" + collection.entrySet().stream().sorted(comparator).reduce("", (a, b) -> a += "Key " + b + ": \n", (a1, b1) -> a1 + b1).trim();
            //return collection.keySet().stream().reduce("", (a, b) -> a += "Key " + b + ": \n", (a1, b1) -> a1 + b1).trim();
        }
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
    /**public LabWork getById(Integer id){
        Iterator it = collection.values().iterator();
        while (it.hasNext()){
            LabWork temp = (LabWork) it.next();
            if (temp.getId() == id){
                return temp;
            }
        }
        return null;
    }**/

    public LabWork getById(Integer id){
        return collection.values().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Returns the element by key.
     * @param key
     * @return answer
     */
    /**public LabWork getByKey(Integer key){
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            Integer temp = (Integer) it.next();
            if (temp == key){
                return collection.get(temp);
            }
        }
        return null;
    }**/
    public LabWork getByKey(Integer key){
        Integer i = collection.keySet().stream().filter(a -> a.equals(key)).findFirst().orElse(-1);
        return collection.values().stream().filter(a -> a.getId().equals(i)).findFirst().orElse(null);
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
        if (collection.values().stream().filter(a -> a.getId().equals(key)).findFirst().orElse(null).compareTo(lw) > 0){
            updateById(lw, key);
        }

    }

    /**
     * Replaces the element if new element is lower.
     * @param lw
     * @param key
     */
    public void replaceIfLower(LabWork lw, Integer key){
        if (collection.values().stream().filter(a -> a.getId().equals(key)).findFirst().orElse(null).compareTo(lw) < 0){
            updateById(lw, key);
        }
    }

    /**
     * Returns all elements in String ordered by standard order.
     * @return
     */
    public String printDescending(){
        /**String answer = "";
        TreeMap<LabWork, Integer> temp = new TreeMap<>();
        Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            int key= (int) it.next();
            temp.put(collection.get(key), key);
        }
        it = temp.keySet().iterator();
        while (it.hasNext()){
            LabWork k = (LabWork) it.next();
            answer = k + "\n" + answer;
        }**/
        return collection.values().stream().sorted().reduce("", (a, b) -> a += b + "\n", (a1, b1) -> b1 + a1);
        //return answer;
    }

    /**
     * Removes all elements greater than argument.
     * @param key
     **/
    public void removeGreater(Integer key){
        /**Iterator it = collection.keySet().iterator();
        while (it.hasNext()){
            int k = (int) it.next();
            if (k > key){
                removeFromCollectionByKey(k);
            }
        }*/
        collection.keySet().stream().filter(a -> a > key).forEach(a -> removeFromCollectionByKey(a));
    }

    /**
     * Removes first elements with personal qualities equals to argument.
     * @param maximum
     */
    public byte removeAnyByPersonalQualitiesMaximum(long maximum){
        /**Iterator it = collection.keySet().iterator();
        boolean stop = true;
        while (it.hasNext() && stop){
            int k = (int) it.next();
            if (collection.get(k).getPersonalQualitiesMaximum() ==  maximum){
                ClientOutputBuilder.println("Удалено одного LabWork произведено (id = " + collection.get(k).getId() + ")");
                removeFromCollectionByKey(k);
                stop = false;
                return 0;
            }
        }**/
        if (collection.values().stream().filter(a -> a.getPersonalQualitiesMaximum() == maximum).count() >= 1) {
            collection.values().stream().filter(a -> a.getPersonalQualitiesMaximum() == maximum).limit(1).peek(a -> removeFromCollectionByKey(a.getId())).peek(a -> ClientOutputBuilder.println("Удалено одного LabWork произведено (id = " + a.getId() + ")"));
            return 0;
        }
        else{
            ClientOutputBuilder.printerr("LabWork с таким id не найден");
            return 1;
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
        collection.keySet().stream().peek(a -> temp.put(collection.get(a), a));
        Iterator it = temp.keySet().iterator();
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
            Integer answer = (collection.keySet().stream().max(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }).get() + 1);
            return answer >= 1 ? answer + 1 : 1;
            /**Iterator it = collection.keySet().iterator();
            int max = 1;
            while (it.hasNext()){
                LabWork k = collection.get(it.next());
                if (k.getId() > max){
                    max = k.getId();
                }
            }
            return (max + 1);**/

        }
    }
}
