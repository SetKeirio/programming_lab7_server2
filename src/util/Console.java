package util;

import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class which works with input.
 */
public class Console {

    private CommandManager commanager;
    private Scanner scanner;
    private LabWorkAsker asker;
    private ArrayList<String> scriptStack = new ArrayList<>();

    public Console(Scanner sc, CommandManager cm, LabWorkAsker la){
        scanner = sc;
        commanager = cm;
        asker = la;
    }

    /**
     * Prints one line.
     * @param o
     */
    public static void println(Object o){
        System.out.println(o);
    }

    /**
     * Prints some text.
     * @param o
     */
    public static void print(Object o){
        System.out.print(o);
    }

    /**
     * Prints the error.
     * @param o
     */
    public static void printerr(Object o){
        System.err.println(o);
    }

    /**
     * Works with input in user mode.
     */
    public void userMode(){
        String[] command = new String[2];
        byte commandCode = 0;
        try{
            while (true) {
                command = (scanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                //commandCode = parseAndExecCommand(command);
            }
        }
        catch (NoSuchElementException exception) {
            Console.printerr("Ввод не обнаружен.");
        } catch (IllegalStateException exception) {
            Console.printerr("Неисправимая ошибка.");
        }
    }

    /**
     * Finds the execution command and refers to command manager to execute it.
     * @param command
     * @return
     */
    /**
    private byte parseAndExecCommand(String[] command){
        switch (command[0]){
            case "clear":
                return commanager.clear(command[1]);
            case "execute_script":
                byte temp = commanager.executeScript(command[1]);
                if (temp != 0){
                    return temp;
                }
                return scriptMode(command[1]);
            case "exit":
                return commanager.exit(command[1]);
            case "group_counting_by_personal_qualities_maximum":
                return commanager.groupCountingByPersonalQualitiesMaximum(command[1]);
            case "help":
                return commanager.help(command[1]);
            case "info":
                return commanager.info(command[1]);
            case "insert":
                return commanager.insert(command[1]);
            case "print_descending":
                return commanager.printDescending(command[1]);
            case "remove_any_by_personal_qualities_maximum":
                return commanager.removeAnyByPersonalQualitiesMaximum(command[1]);
            case "remove_greater_key":
                return commanager.removeGreaterKey(command[1]);
            case "remove_key":
                return commanager.removeKey(command[1]);
            case "replace_if_lower":
                return commanager.replaceIfLower(command[1]);
            case "replace_if_greater":
                return commanager.replaceIfGreater(command[1]);
            case "save":
                return commanager.save(command[1]);
            case "show":
                return commanager.show(command[1]);
            case "update":
                return commanager.update(command[1]);
            default:
                Console.println("Такой команды нет");
                break;

        };
        return 0;
    }
     **/


    /**
     * Works with input in script mode.
     * @param param
     * @return
     */
    public byte scriptMode(String param){
        String[] command = new String[2];
        byte commandCode = 0;
        scriptStack.add(param);
        try (Scanner sc = new Scanner(new File(param))){
            if (!sc.hasNext()){
                throw new NoSuchElementException();
            }
            Scanner sc1 = asker.getScanner();
            asker.setScanner(sc);
            asker.setFileInput(true);
            do {
                command = (sc.nextLine().trim() + " ").split(" ",2);
                command[1] = command[1].trim();
                while (sc.hasNextLine() && command[0].isEmpty()){
                    command = (sc.nextLine().trim() + " ").split(" ", 2);
                    command[1] = command[1].trim();
                }
                Console.println(String.join(" ", command));
                if (command[0].equals("execute_script")) {
                    for (String script: scriptStack) {
                        if (command[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                //commandCode = parseAndExecCommand(command);
            } while (sc.hasNextLine() && commandCode == (byte) 0);
            asker.setScanner(sc1);
            asker.setFileInput(false);
            if (commandCode != 0 && !command[1].isEmpty() && !(command[0].equals("execute_script"))){
                Console.println("Введенные данные некорректны!");
            }
            return commandCode;

        } catch (FileNotFoundException exception) {
            Console.printerr("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerr("Файл является пустым!");
        } catch (ScriptRecursionException exception) {
            Console.printerr("Найдена ресурсия в скрипте!");
        } catch (IllegalStateException exception) {
            Console.printerr("Неисправимая ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return (byte) 1;
    }


}
