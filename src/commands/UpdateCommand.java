package commands;

import core.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import messages.MessageLabWork;
import util.CollectionManager;
import util.ClientOutputBuilder;

import java.time.ZonedDateTime;

/**
 * Command which updates the element in the collection.
 */
public class UpdateCommand extends AbstractCommand {
    /**
     * Each command should be determined only once.
     */
    private CollectionManager cmanager;
    //private LabWorkAsker asker;
    public UpdateCommand(CollectionManager c) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        cmanager = c;
    }

    /**
     * Executes the command.
     *
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
            cmanager.removeFromCollectionByKey(id);
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
            cmanager.addToCollection(newWork, id);
            ClientOutputBuilder.println("LabWork изменен.");
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
