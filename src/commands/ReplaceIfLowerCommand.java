package commands;

import core.Coordinates;
import core.Difficulty;
import core.LabWork;
import core.Person;
import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import messages.MessageLabWork;
import util.CollectionManager;
import util.ClientOutputBuilder;

import java.time.ZonedDateTime;

/**
 * Command which replaces the element if he is lower than past.
 */
public class ReplaceIfLowerCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    private CollectionManager cmanager;
    //private LabWorkAsker asker;
    public ReplaceIfLowerCommand(CollectionManager c) {
        super("replace_if_lower null {element}", "заменить значение по ключу, если новое значение меньше старого");
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
            if (param.isEmpty() || object == null) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            Integer id = Integer.parseInt(param);
            LabWork l = cmanager.getById(id);
            if (l == null) {
                throw new LabWorkSearchException();
            }
            String name = l.getName();
            Coordinates coordinates = l.getCoordinates();
            ZonedDateTime creationDate = l.getCreationDate();
            double minimalPoint = l.getMinimalPoint();
            long personalMaximum = l.getPersonalQualitiesMaximum();
            Difficulty difficulty = l.getDifficulty();
            Person author = l.getAuthor();
            MessageLabWork lw = (MessageLabWork) object;
            LabWork newWork = new LabWork(
                    id,
                    lw.getName(),
                    lw.getCoordinates(),
                    ZonedDateTime.now(),
                    lw.getMinimalPoint(),
                    lw.getPersonalQualitiesMaximum(),
                    lw.getDifficulty(),
                    lw.getAuthor());
            if (newWork.compareTo(l) <= 0) {
                cmanager.removeFromCollectionByKey(id);
                cmanager.addToCollection(newWork, id);
                ClientOutputBuilder.println("LabWork изменен.");
            }
            else {
                ClientOutputBuilder.println("LabWork не изменен.");
            }
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
