package commands;

import java.util.Objects;

/**
 * Abstract class for commands.
 * Filled with methods from Object and getters for explanation and name.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String explanation;

    /**
     * Each command should be determined only once.
     * @param name
     * @param explanation
     */
    public AbstractCommand(String name, String explanation){
        this.name = name;
        this.explanation = explanation;
    }
    /**
     *
     * @return Command name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Explanation how command works.
     */
    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return name + "\n###" + explanation + "\n###";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
        return name.equals(that.name) && explanation.equals(that.explanation);
    }
    @Override
    public int hashCode() {
        return name.hashCode() + explanation.hashCode();
    }
}
