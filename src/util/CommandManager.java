package util;

import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which works with commands and invokes them.
 */
public class CommandManager {

    private List<Command> commands = new ArrayList<>();
    private Command clearCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command groupCountingByPersonalQualitiesMaximumCommand;
    private Command helpCommand;
    private Command infoCommand;
    private Command insertCommand;
    private Command printDescendingCommand;
    private Command removeAnyByPersonalQualitiesMaximumCommand;
    private Command removeGreaterKeyCommand;
    private Command removeKeyCommand;
    private Command replaceIfGreaterCommand;
    private Command replaceIfLowerCommand;
    private Command saveCommand;
    private Command showCommand;
    private Command updateCommand;

    public CommandManager(Command clearCommand, Command executeScriptCommand, Command exitCommand, Command groupCountingByPersonalQualitiesMaximumCommand, Command helpCommand, Command infoCommand, Command insertCommand, Command printDescendingCommand, Command removeAnyByPersonalQualitiesMaximumCommand, Command removeGreaterKeyCommand, Command removeKeyCommand, Command replaceIfGreaterCommand, Command replaceIfLowerCommand, Command saveCommand, Command showCommand, Command updateCommand) {
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.groupCountingByPersonalQualitiesMaximumCommand = groupCountingByPersonalQualitiesMaximumCommand;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.insertCommand = insertCommand;
        this.printDescendingCommand = printDescendingCommand;
        this.removeAnyByPersonalQualitiesMaximumCommand = removeAnyByPersonalQualitiesMaximumCommand;
        this.removeGreaterKeyCommand = removeGreaterKeyCommand;
        this.removeKeyCommand = removeKeyCommand;
        this.replaceIfGreaterCommand = replaceIfGreaterCommand;
        this.replaceIfLowerCommand = replaceIfLowerCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;
        commands.add(clearCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(insertCommand);
        commands.add(printDescendingCommand);
        commands.add(removeAnyByPersonalQualitiesMaximumCommand);
        commands.add(removeGreaterKeyCommand);
        commands.add(removeKeyCommand);
        commands.add(replaceIfGreaterCommand);
        commands.add(saveCommand);
        commands.add(showCommand);
        commands.add(updateCommand);

    }

    /**
     * Executes clear command.
     * @param arg
     * @return
     */
    public byte clear(String arg){
        return clearCommand.exec(arg);
    }
    /**
     * Executes ececute_script command.
     * @param arg
     * @return
     */
    public byte executeScript(String arg){
        return executeScriptCommand.exec(arg);
    }
    /**
     * Executes exit command.
     * @param arg
     * @return
     */
    public byte exit(String arg){
        return exitCommand.exec(arg);
    }
    /**
     * Executes groupCountingByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public byte groupCountingByPersonalQualitiesMaximum(String arg){
        return groupCountingByPersonalQualitiesMaximumCommand.exec(arg);
    }
    /**
     * Executes help command.
     * @param arg
     * @return
     */
    public byte help(String arg){
        byte code = helpCommand.exec(arg);
        if (code == 0){
            Console.println("Список команд:");
            for (Command c: commands){
                Console.println("\t" + c.getName() + "\t" + c.getExplanation());
            }
            return 0;
        }
        else{
            return code;
        }
    }
    /**
     * Executes info command.
     * @param arg
     * @return
     */
    public byte info(String arg){
        return infoCommand.exec(arg);
    }
    /**
     * Executes insert command.
     * @param arg
     * @return
     */
    public byte insert(String arg){
        return insertCommand.exec(arg);
    }
    /**
     * Executes printDescending command.
     * @param arg
     * @return
     */
    public byte printDescending(String arg){
        return printDescendingCommand.exec(arg);
    }
    /**
     * Executes removeAnyByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public byte removeAnyByPersonalQualitiesMaximum(String arg){
        return removeAnyByPersonalQualitiesMaximumCommand.exec(arg);
    }
    /**
     * Executes removeGreaterKey command.
     * @param arg
     * @return
     */
    public byte removeGreaterKey(String arg){
        return removeGreaterKeyCommand.exec(arg);
    }
    /**
     * Executes removeKey command.
     * @param arg
     * @return
     */
    public byte removeKey(String arg){
        return removeKeyCommand.exec(arg);
    }
    /**
     * Executes replaceIfLower command.
     * @param arg
     * @return
     */
    public byte replaceIfLower(String arg){
        return replaceIfLowerCommand.exec(arg);
    }
    /**
     * Executes replaceIfGreater command.
     * @param arg
     * @return
     */
    public byte replaceIfGreater(String arg){
        return replaceIfGreaterCommand.exec(arg);
    }
    /**
     * Executes save command.
     * @param arg
     * @return
     */
    public byte save(String arg){
        return saveCommand.exec(arg);
    }
    /**
     * Executes show command.
     * @param arg
     * @return
     */
    public byte show(String arg){
        return showCommand.exec(arg);
    }
    /**
     * Executes update command.
     * @param arg
     * @return
     */
    public byte update(String arg){
        return updateCommand.exec(arg);
    }

}
