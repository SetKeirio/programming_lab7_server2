package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.ClientOutputBuilder;

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
    public byte exec(String param, Object object) {
        try {
            if (param.isEmpty() || object != null) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            Long personal = Long.parseLong(param);
            return cmanager.removeAnyByPersonalQualitiesMaximum(personal);
        } catch (LabWorkSearchException e) {
            ClientOutputBuilder.printerr("LabWork с таким id не найден.");
        } catch (EmptyCollectionException e) {
            ClientOutputBuilder.printerr("Коллекция пуста.");
        } catch (WrongElementsCountException e) {
            ClientOutputBuilder.printerr("Нужно использовать команду так: " + getName());
        }
        return 1;
}
}
