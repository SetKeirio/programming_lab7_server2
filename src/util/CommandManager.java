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
    private Command exitServerCommand;

    public CommandManager(Command clearCommand, Command executeScriptCommand, Command exitCommand, Command groupCountingByPersonalQualitiesMaximumCommand, Command helpCommand, Command infoCommand, Command insertCommand, Command printDescendingCommand, Command removeAnyByPersonalQualitiesMaximumCommand, Command removeGreaterKeyCommand, Command removeKeyCommand, Command replaceIfGreaterCommand, Command replaceIfLowerCommand, Command saveCommand, Command showCommand, Command updateCommand, Command exitServerCommand) {
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
        this.exitServerCommand = exitServerCommand;
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
        commands.add(exitServerCommand);

    }

    /**
     * Executes clear command.
     * @param arg
     * @return
     */
    public byte clear(String arg, Object object){
        return clearCommand.exec(arg, object);
    }
    /**
     * Executes ececute_script command.
     * @param arg
     * @return
     */
    public byte executeScript(String arg, Object object){
        return executeScriptCommand.exec(arg, object);
    }
    /**
     * Executes exit command.
     * @param arg
     * @return
     */
    public byte exit(String arg, Object object){
        return exitCommand.exec(arg, object);
    }

    public byte exitServer(String arg, Object object){
        return exitServerCommand.exec(arg, object);
    }
    /**
     * Executes groupCountingByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public byte groupCountingByPersonalQualitiesMaximum(String arg, Object object){
        return groupCountingByPersonalQualitiesMaximumCommand.exec(arg, object);
    }
    /**
     * Executes help command.
     * @param arg
     * @return
     */
    public byte help(String arg, Object object){
        byte code = helpCommand.exec(arg, object);
        if (code == 0){
            ClientOutputBuilder.println("Список команд:");
            for (Command c: commands){
                ClientOutputBuilder.println("\t" + c.getName() + "\t" + c.getExplanation());
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
    public byte info(String arg, Object object){
        return infoCommand.exec(arg, object);
    }
    /**
     * Executes insert command.
     * @param arg
     * @return
     */
    public byte insert(String arg, Object object){
        return insertCommand.exec(arg, object);
    }
    /**
     * Executes printDescending command.
     * @param arg
     * @return
     */
    public byte printDescending(String arg, Object object){
        return printDescendingCommand.exec(arg, object);
    }
    /**
     * Executes removeAnyByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public byte removeAnyByPersonalQualitiesMaximum(String arg, Object object){
        return removeAnyByPersonalQualitiesMaximumCommand.exec(arg, object);
    }
    /**
     * Executes removeGreaterKey command.
     * @param arg
     * @return
     */
    public byte removeGreaterKey(String arg, Object object){
        return removeGreaterKeyCommand.exec(arg, object);
    }
    /**
     * Executes removeKey command.
     * @param arg
     * @return
     */
    public byte removeKey(String arg, Object object){
        return removeKeyCommand.exec(arg, object);
    }
    /**
     * Executes replaceIfLower command.
     * @param arg
     * @return
     */
    public byte replaceIfLower(String arg, Object object){
        return replaceIfLowerCommand.exec(arg, object);
    }
    /**
     * Executes replaceIfGreater command.
     * @param arg
     * @return
     */
    public byte replaceIfGreater(String arg, Object object){
        return replaceIfGreaterCommand.exec(arg, object);
    }
    /**
     * Executes save command.
     * @param arg
     * @return
     */
    public byte save(String arg, Object object){
        return saveCommand.exec(arg, object);
    }
    /**
     * Executes show command.
     * @param arg
     * @return
     */
    public byte show(String arg, Object object){
        return showCommand.exec(arg, object);
    }
    /**
     * Executes update command.
     * @param arg
     * @return
     */
    public byte update(String arg, Object object){
        return updateCommand.exec(arg, object);
    }

}
