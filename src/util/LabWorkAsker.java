package util;

import core.Color;
import core.Coordinates;
import core.Difficulty;
import core.Person;
import exceptions.CantBeNullException;
import exceptions.LimitIgnoreException;
import exceptions.ScriptWrongInputException;

import java.util.*;

/**
 * Asks a user to input LabWork.
 */
public class LabWorkAsker {
    private final long MIN_PERSONAL_QUALITIES = 1;
    private final double MIN_MINIMAL_POINT = 0.0;
    private final long MIN_ID = 1;
    private final int MIN_X = -696;
    private final int MIN_WEIGHT = 1;
    private final int MIN_PASSPORTID_LENGTH = 7;

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean isFromFile() {
        return fromFile;
    }

    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }

    private Scanner scanner;
    private boolean fromFile;

    public LabWorkAsker(Scanner scanner) {
        this.scanner = scanner;
        fromFile = false;
    }

    public void setFileInput(boolean input) {
        fromFile = input;
    }

    /**
     * Asks to input name.
     * @return
     * @throws ScriptWrongInputException
     */
    public String askName() throws ScriptWrongInputException {
        String answer;
        while (1 == 1) {
            try {
                Console.println("Введите имя:");
                answer = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(answer);
                }
                if (answer.equals("") || answer.equals("null")) {
                    throw new CantBeNullException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Имя введено неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Имя не должно быть пустым.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            }
        }
        return answer;
    }

    /**
     * Asks to input x.
     * @return
     * @throws ScriptWrongInputException
     */
    public int askX() throws ScriptWrongInputException {
        String convert;
        int answer;
        while (1 == 1) {
            try {
                Console.println("Введите целую координату по x (x > -697):");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                answer = Integer.parseInt(convert);
                if (answer < MIN_X) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Координата по x введена неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }  catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            }  catch (CantBeNullException e) {
                Console.printerr("Координата по x не должна быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (LimitIgnoreException e) {
                Console.printerr("Координата по x должна быть не меньше " + MIN_X);
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            }

        }
        return answer;
    }

    /**
     * Asks to input y.
     * @return
     * @throws ScriptWrongInputException
     */
    public Integer askY() throws ScriptWrongInputException {
        String convert;
        Integer answer;
        while (1 == 1) {
            try {
                Console.println("Введите целую координату по y:");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                answer = Integer.parseInt(convert);
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Координата по y введена неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Координата по y не должна быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            }
        }
        return answer;
    }

    /**
     * Asks to input coordinates.
     * @return
     * @throws ScriptWrongInputException
     */
    public Coordinates askCoordinates() throws ScriptWrongInputException {
        int x;
        Integer y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks to input minimal point.
     * @return
     * @throws ScriptWrongInputException
     */
    public double askMinimalPoint() throws ScriptWrongInputException {
        String convert;
        double answer;
        while (1 == 1) {
            try {
                Console.println("Введите вещественную пороговую оценку:");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                answer = Double.parseDouble(convert);
                if (answer <= MIN_MINIMAL_POINT) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            }catch (NoSuchElementException e) {
                Console.printerr("Пороговая оценка введена неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Пороговая оценка не должна быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Пороговая оценка должна быть больше " + MIN_MINIMAL_POINT);
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
        return answer;
    }

    /**
     * Asks to input personalQualitiesMaximum.
     * @return
     * @throws ScriptWrongInputException
     */
    public long askPersonalQualitiesMaximum() throws ScriptWrongInputException {
        String convert;
        long answer;
        while (1 == 1) {
            try {
                Console.println("Введите целый максимум за личные качества:");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                answer = Long.parseLong(convert);
                if (answer < MIN_PERSONAL_QUALITIES) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            } catch (NoSuchElementException e) {
                Console.printerr("Максимум введен неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Максимум не должен быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Максимум должен быть не меньше " + MIN_PERSONAL_QUALITIES);
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
        return answer;

    }

    /**
     * Asks to input color.
     * @return
     * @throws ScriptWrongInputException
     */
    public Color askColor() throws ScriptWrongInputException {
        String convert;
        Color answer;
        while (1 == 1) {
            try {
                Console.println("Список цветов: " + Color.getNames());
                Console.println("Введите цвет: ");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    return null;
                }
                convert = convert.toUpperCase();
                answer = Color.valueOf(convert);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerr("Цвет не распознан.");
                if (fromFile) throw new ScriptWrongInputException();
            } catch (IllegalArgumentException exception) {
                Console.printerr("Цвета нет в списке.");
                if (fromFile) throw new ScriptWrongInputException();
            } catch (IllegalStateException exception) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            }
        }
        return answer;
    }

    /**
     * Asks to input weight.
     * @return
     * @throws ScriptWrongInputException
     */
    public int askWeight() throws ScriptWrongInputException {
        String convert;
        int answer;
        while (1 == 1) {
            try {
                Console.println("Введите целый вес (больше 0):");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                answer = Integer.parseInt(convert);
                if (answer < MIN_WEIGHT) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Вес введен неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Вес не должен быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Вес должен быть не меньше " + MIN_WEIGHT);
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
            catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            }
        }
        return answer;

    }

    /**
     * Asks the binary question.
     * @return
     * @throws ScriptWrongInputException
     */
    public boolean askQuestion(String question) throws ScriptWrongInputException{
        String format = question + " (д/н)?";
        String answer;
        while (1==1){
            try{
                Console.println(format);
                answer = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(answer);
                }
                if ((!answer.equals("д")) && !(answer.equals("н"))){
                    throw new LimitIgnoreException();
                }
                break;
            }
            catch (NoSuchElementException e){
                Console.printerr("Ответ введен неверно.");
                if (fromFile){
                    throw new ScriptWrongInputException();
                }
            }
            catch (LimitIgnoreException e){
                Console.printerr("Ответ должен быть буквой д или н.");
            }
            catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            }

        }
        return (answer.equals("д")) ? true : false;
    }

    /**
     * Asks to input difficulty.
     * @return
     * @throws ScriptWrongInputException
     */
    public Difficulty askDifficulty() throws ScriptWrongInputException{
        String answer;
        Difficulty ret = null;
        while (1 == 1) {
            try {
                Console.println("Введите сложность (одну из VERY_EASY, NORMAL, HOPELESS, TERRIBLE):");
                answer = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(answer);
                }
                if (!(answer.equals("VERY_EASY") || answer.equals("NORMAL") || answer.equals("HOPELESS") || answer.equals("TERRIBLE"))) {
                    throw new LimitIgnoreException();
                }
                if (answer.equals("VERY_EASY")){
                    ret = Difficulty.VERY_EASY;
                }
                else if (answer.equals("NORMAL")){
                    ret = Difficulty.NORMAL;
                }
                else if (answer.equals("HOPELESS")){
                    ret = Difficulty.HOPELESS;
                }
                else if (answer.equals("TERRIBLE")){
                    ret = Difficulty.TERRIBLE;
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Имя введено неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Имя не должно быть пустым.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Сложность должна быть одной из (VERY_EASY;NORMAL;HOPELESS;TERRIBLE)");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
        return ret;
    }

    /**
     * Asks to input author (with all fields of it).
     * @return
     * @throws ScriptWrongInputException
     */
    public Person askAuthor() throws ScriptWrongInputException{
        Console.println("Теперь ввод данных для автора:");
        String name = askName();
        int weight = askWeight();
        String passportID = askPassportID();
        Color hairColor = askColor();
        Date birthday = askBirthday();
        Person answer = new Person(name, birthday, weight, passportID, hairColor);
        return answer;
    }

    /**
     * Asks to input birthday (ms from 1 January 1970).
     * @return
     * @throws ScriptWrongInputException
     */
    private Date askBirthday() throws ScriptWrongInputException {
        String convert;
        Date answer = null;
        Long ms;
        while (1 == 1) {
            try {
                Console.println("Введите количество миллисекунд, прошедших с 1 января 1970 года до момента рождения");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                ms = Long.parseLong(convert);
                if (ms < 0) {
                    throw new LimitIgnoreException();
                }
                answer = new Date(ms);
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Время введено неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Время не должно быть пустым.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Время должно быть неотрицательным");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
            catch (NumberFormatException e){
                Console.printerr("Было введено не то число!");
            }
        }
        return answer;
    }
        /*String convert;
        int year, month, date;
        Calendar temp;
        while (1 == 1) {
            try {
                Console.println("Введите месяц (от 0 до 11, 0 - январь, 11 - декабрь)");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                month = Integer.parseInt(convert);
                if (month > 11 || month < 0) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Месяц введен неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Месяц не должен быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Месяц должен быть в пределах [0;11] ");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
        while (1 == 1) {
            try {
                Console.println("Введите месяц (от 0 до 11, 0 - январь, 11 - декабрь)");
                convert = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(convert);
                }
                if (convert.equals("")) {
                    throw new CantBeNullException();
                }
                month = Integer.parseInt(convert);
                if (month > 11 || month < 0) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Месяц введен неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Месяц не должен быть пустой.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }

            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Месяц должен быть в пределах [0;11] ");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
         */

    /**
     * Asks to input passportID.
     * @return
     * @throws ScriptWrongInputException
     */
    public String askPassportID() throws ScriptWrongInputException {
        String answer = "";
        while (1 == 1) {
            try {
                Console.println("Введите id паспорта:");
                answer = scanner.nextLine().trim();
                if (fromFile) {
                    Console.println(answer);
                }
                if (answer.equals("") || answer.equals("null")) {
                    throw new CantBeNullException();
                }
                if (answer.length() < MIN_PASSPORTID_LENGTH) {
                    throw new LimitIgnoreException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printerr("Id паспорта введено неверно.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (CantBeNullException e) {
                Console.printerr("Id паспорта не должно быть пустым.");
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            } catch (IllegalStateException e) {
                Console.printerr("Неисправимая ошибка.");
                System.exit(1);
            } catch (LimitIgnoreException e) {
                Console.printerr("Длина паспорта должна быть не меньше " + MIN_PASSPORTID_LENGTH);
                if (fromFile) {
                    throw new ScriptWrongInputException();
                }
            }
        }
        return answer;
    }


}
