package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import messages.User;
import util.CollectionManager;
import util.ClientOutputBuilder;
import util.DatabaseChanger;

/**
 * Command which removes all elements with key more than argument.
 */
public class RemoveGreaterKeyCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    private CollectionManager cmanager;
    private DatabaseChanger changer;
    public RemoveGreaterKeyCommand(CollectionManager c, DatabaseChanger changer) {
        super("remove_greater_key null", "удалить из коллекции все элементы, ключ которых превышает заданный");
        cmanager = c;
        this.changer = changer;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (param.isEmpty() || object != null) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            Integer id = Integer.parseInt(param);
            cmanager.removeGreater(id);
            return 0;
        } catch (LabWorkSearchException e) {
            ClientOutputBuilder.printerr("LabWork с таким id не найден.");
        } catch (EmptyCollectionException e) {
            ClientOutputBuilder.printerr("Коллекция пуста.");
        } catch (WrongElementsCountException e) {
            ClientOutputBuilder.printerr("Нужно использовать команду так: " + getName());
        } catch (NumberFormatException e){
            ClientOutputBuilder.printerr("Было введено не то число!");
        }
        return 1;
    }
}
