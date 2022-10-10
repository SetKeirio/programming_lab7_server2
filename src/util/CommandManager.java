package util;

import commands.Command;
import messages.User;

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
    private Command showCommand;
    private Command updateCommand;
    private Command exitServerCommand;
    private Command loginCommand;
    private Command registerCommand;

    public CommandManager(Command clearCommand, Command executeScriptCommand, Command exitCommand, Command groupCountingByPersonalQualitiesMaximumCommand, Command helpCommand, Command infoCommand, Command insertCommand, Command printDescendingCommand, Command removeAnyByPersonalQualitiesMaximumCommand, Command removeGreaterKeyCommand, Command removeKeyCommand, Command replaceIfGreaterCommand, Command replaceIfLowerCommand, Command showCommand, Command updateCommand, Command exitServerCommand, Command loginCommand, Command registerCommand) {
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
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;
        this.exitServerCommand = exitServerCommand;
        this.loginCommand = loginCommand;
        this.registerCommand = registerCommand;
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
        commands.add(showCommand);
        commands.add(updateCommand);
        commands.add(exitServerCommand);
        //commands.add(loginCommand);
        //commands.add(registerCommand);

    }

    /**
     * Executes clear command.
     * @param arg
     * @return
     */
    public byte clear(String arg, Object object, User user){
        return clearCommand.exec(arg, object, user);
    }
    /**
     * Executes ececute_script command.
     * @param arg
     * @return
     */
    public byte executeScript(String arg, Object object, User user){
        return executeScriptCommand.exec(arg, object, user);
    }
    /**
     * Executes exit command.
     * @param arg
     * @return
     */
    public byte exit(String arg, Object object, User user){
        return exitCommand.exec(arg, object, user);
    }

    public byte exitServer(String arg, Object object, User user){
        return exitServerCommand.exec(arg, object, user);
    }
    /**
     * Executes groupCountingByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public byte groupCountingByPersonalQualitiesMaximum(String arg, Object object, User user){
        return groupCountingByPersonalQualitiesMaximumCommand.exec(arg, object, user);
    }
    /**
     * Executes help command.
     * @param arg
     * @return
     */
    public byte help(String arg, Object object, User user){
        byte code = helpCommand.exec(arg, object, user);
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
    public synchronized byte info(String arg, Object object, User user){
        return infoCommand.exec(arg, object, user);
    }
    /**
     * Executes insert command.
     * @param arg
     * @return
     */
    public synchronized byte insert(String arg, Object object, User user){
        return insertCommand.exec(arg, object, user);
    }
    /**
     * Executes printDescending command.
     * @param arg
     * @return
     */
    public synchronized byte printDescending(String arg, Object object, User user){
        return printDescendingCommand.exec(arg, object, user);
    }
    /**
     * Executes removeAnyByPersonalQualitiesMaximum command.
     * @param arg
     * @return
     */
    public synchronized byte removeAnyByPersonalQualitiesMaximum(String arg, Object object, User user){
        return removeAnyByPersonalQualitiesMaximumCommand.exec(arg, object, user);
    }
    /**
     * Executes removeGreaterKey command.
     * @param arg
     * @return
     */
    public synchronized byte removeGreaterKey(String arg, Object object, User user){
        return removeGreaterKeyCommand.exec(arg, object, user);
    }
    /**
     * Executes removeKey command.
     * @param arg
     * @return
     */
    public synchronized byte removeKey(String arg, Object object, User user){
        return removeKeyCommand.exec(arg, object, user);
    }
    /**
     * Executes replaceIfLower command.
     * @param arg
     * @return
     */
    public synchronized byte replaceIfLower(String arg, Object object, User user){
        return replaceIfLowerCommand.exec(arg, object, user);
    }
    /**
     * Executes replaceIfGreater command.
     * @param arg
     * @return
     */
    public synchronized byte replaceIfGreater(String arg, Object object, User user){
        return replaceIfGreaterCommand.exec(arg, object, user);
    }
    /**
     * Executes show command.
     * @param arg
     * @return
     */
    public byte show(String arg, Object object, User user){
        return showCommand.exec(arg, object, user);
    }
    /**
     * Executes update command.
     * @param arg
     * @return
     */
    public byte update(String arg, Object object, User user){
        return updateCommand.exec(arg, object, user);
    }

    public byte login(String arg, Object object, User user){
        return loginCommand.exec(arg, object, user);
    }

    public byte register(String arg, Object object, User user){
        return registerCommand.exec(arg, object, user);
    }

}
