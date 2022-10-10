package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import messages.User;
import util.ClientOutputBuilder;
import util.CollectionManager;

/**
 * Command which prints the collection sorted by personalQualitiesMaximum
 */
public class GroupCountingByPersonalQualitiesMaximumCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    CollectionManager cmanager;
    public GroupCountingByPersonalQualitiesMaximumCommand(CollectionManager c) {
        super("group_counting_by_personal_qualities_maximum", "сгруппировать элементы коллекции по значению поля personalQualitiesMaximum, вывести количество элементов в каждой группе");
        cmanager = c;
    }

    /**
     * Executes the command.
     * @param param
     * @return error code, 0 - ok, 1 - standard error (byte)
     */
    @Override
    public byte exec(String param, Object object, User user) {
        try {
            if (!(param.isEmpty()) || object != null) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            ClientOutputBuilder.println(cmanager.groupCountingByPersonalQualitiesMaximum());
            return 0;
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
