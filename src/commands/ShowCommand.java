package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

/**
 * Command which prints all elements of the collection.
 */
public class ShowCommand extends AbstractCommand{
    private CollectionManager cmanager;
    /**
     * Each command should be determined only once.
     */
    public ShowCommand(CollectionManager c) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param) {
        try{
            if (!param.isEmpty()){
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0){
                throw new EmptyCollectionException();
            }
            Console.println(cmanager.showAll());
            return 0;
        }
        catch (WrongElementsCountException e){
            Console.println("Верное использование команды: " + getName() + " " + getExplanation());
            return 1;
        }
        catch (EmptyCollectionException e){
            Console.printerr("Коллекция пуста.");
            return 1;
        }
    }
}
