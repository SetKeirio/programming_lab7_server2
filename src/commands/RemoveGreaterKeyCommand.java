package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

/**
 * Command which removes all elements with key more than argument.
 */
public class RemoveGreaterKeyCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    private CollectionManager cmanager;
    public RemoveGreaterKeyCommand(CollectionManager c) {
        super("remove_greater_key null", "удалить из коллекции все элементы, ключ которых превышает заданный");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param) {
        try {
            if (param.isEmpty()) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            Integer id = Integer.parseInt(param);
            cmanager.removeGreater(id);
            return 0;
        } catch (LabWorkSearchException e) {
            Console.printerr("LabWork с таким id не найден.");
        } catch (EmptyCollectionException e) {
            Console.printerr("Коллекция пуста.");
        } catch (WrongElementsCountException e) {
            Console.printerr("Нужно использовать команду так: " + getName());
        } catch (NumberFormatException e){
            Console.printerr("Было введено не то число!");
        }
        return 1;
    }
}
