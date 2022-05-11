package core;

/**
 * Source Person class
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private int weight; //Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 7, Поле не может быть null
    private Color hairColor; //Поле может быть null

    public Person(String n, java.util.Date b, int w, String p, Color c){
        name = n;
        birthday = b;
        weight = w;
        passportID = p;
        hairColor = c;
    }
}
