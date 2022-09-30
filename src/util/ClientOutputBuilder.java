package util;

public class ClientOutputBuilder {
    private static StringBuilder builder = new StringBuilder();

    public static void append(Object output) {
        builder.append(output);
    }

    public static String getString() {
        return builder.toString();
    }

    public static void println() {
        builder.append("\n");
    }

    public static void println(Object output) {
        builder.append(output + "\n");
    }

    public static void printerr(Object output) {
        builder.append("Ошибка: " + output + "\n");
    }

    public static String refresh() {
        String toReturn = builder.toString();
        builder.delete(0, builder.length());
        return toReturn;
    }

    public static void clear() {
        builder.delete(0, builder.length());
    }
}
