package commands;

import exceptions.EmptyCollectionException;
import exceptions.LabWorkSearchException;
import exceptions.WrongElementsCountException;
import util.CollectionManager;
import util.Console;

/**
 * Command which prints all elements in collection ordered by standard ordering.
 */
public class PrintDescendingCommand extends AbstractCommand{
    /**
     * Each command should be determined only once.
     */
    CollectionManager cmanager;
    public PrintDescendingCommand(CollectionManager c) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
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
            if (!(param.isEmpty())) {
                throw new WrongElementsCountException();
            }
            if (cmanager.getSize() == 0) {
                throw new EmptyCollectionException();
            }
            Long personal = Long.parseLong(param);
            Console.println(cmanager.printDescending());
            return 0;
        } catch (LabWorkSearchException e) {
            Console.printerr("LabWork с таким id не найден.");
        } catch (EmptyCollectionException e) {
            Console.printerr("Коллекция пуста.");
        } catch (WrongElementsCountException e) {
            Console.printerr("Нужно использовать команду так: " + getName());
        }
        catch (NumberFormatException e){
            Console.printerr("Было введено не то число!");
        }

        return 1;
    }
}
