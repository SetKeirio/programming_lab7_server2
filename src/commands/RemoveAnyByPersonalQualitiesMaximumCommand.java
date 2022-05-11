package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

/**
 * Command which removes first element with personalMaximum equals to argument.
 */
public class RemoveAnyByPersonalQualitiesMaximumCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    CollectionManager cmanager;
    public RemoveAnyByPersonalQualitiesMaximumCommand(CollectionManager c) {
        super("remove_any_by_personal_qualities_maximum personalQualitiesMaximum", "удалить из коллекции один элемент, значение поля personalQualitiesMaximum которого эквивалентно заданному");
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
            Long personal = Long.parseLong(param);
            cmanager.removeAnyByPersonalQualitiesMaximum(personal);
            return 0;
        } catch (LabWorkSearchException e) {
            Console.printerr("LabWork с таким id не найден.");
        } catch (EmptyCollectionException e) {
            Console.printerr("Коллекция пуста.");
        } catch (WrongElementsCountException e) {
            Console.printerr("Нужно использовать команду так: " + getName());
        }
        return 1;
}
}
