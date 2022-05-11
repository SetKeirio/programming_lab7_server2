package core;

/**
 * Source class color. Enum with all Color constants
 */
public enum Color {
    GREEN,
    RED,
    BLACK,
    BLUE,
    WHITE;

    /**
     * Get all variants of color names.
     * @return answer
     */
    public static String getNames(){
        String answer = "";
        for (Color c: Color.values()){
            answer += c.name() + ", ";
        }
        return answer.substring(0, answer.length() - 2);
    }
}
