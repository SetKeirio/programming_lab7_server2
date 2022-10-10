package util;

import core.*;
import messages.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class DatabaseChanger {

    private DatabaseManager databaseManager;
    private UserManager userManager;

    public DatabaseChanger(DatabaseManager dbManager, UserManager userManager){
        databaseManager = dbManager;
        this.userManager = userManager;
    }

    private LabWork createLabWork(ResultSet data) throws SQLException {
        int id = data.getInt(Data.LABWORK_ID);
        String name = data.getString(Data.LABWORK_NAME);
        Coordinates coordinates = getCoordinatesById(data.getInt(Data.LABWORK_COORDINATES_ID));
        ZonedDateTime creationDate = data.getTimestamp(Data.LABWORK_CREATION_DATE).toLocalDateTime().atZone(ZoneId.systemDefault());
        double minimalPoint = data.getDouble(Data.LABWORK_MINIMAL_POINT);
        long personalQualitiesMaximum = data.getLong(Data.LABWORK_PERSONAL_QUALITIES_MAXIMUM);
        Difficulty difficulty = Difficulty.valueOf(data.getString(Data.LABWORK_DIFFICULTY));
        Person author = getAuthorById(data.getInt(Data.LABWORK_AUTHOR_ID));
        User creator = userManager.getUserById(data.getInt(Data.LABWORK_USER_ID));
        return new LabWork(id, name, coordinates, creationDate, minimalPoint, personalQualitiesMaximum, difficulty, author, creator);
    }

    public Coordinates getCoordinatesById(int id){
        PreparedStatement findCoordinates = null;
        Coordinates answer = null;
        try{
            findCoordinates = databaseManager.getStatement(Data.COORDINATES_BY_ID, false);
            findCoordinates.setInt(1, id);
            ResultSet resultSet = findCoordinates.executeQuery();
            if (resultSet.next()){
                answer = new Coordinates(resultSet.getInt(Data.COORDINATES_X), resultSet.getInt(Data.COORDINATES_Y));
                databaseManager.closeStatement(findCoordinates);
            }
            else{
                databaseManager.closeStatement(findCoordinates);
                throw new SQLException();
            }
        } catch (SQLException e) {
            Console.printerr("Не удалось найти координаты для LabWork с id=" + id);
        }
        return answer;

    }

    public Person getAuthorById(int id){
        PreparedStatement findPerson = null;
        Person answer = null;
        try{
            findPerson = databaseManager.getStatement(Data.PERSON_BY_ID, false);
            findPerson.setInt(1, id);
            ResultSet resultSet = findPerson.executeQuery();
            if (resultSet.next()){
                answer = new Person(resultSet.getString(Data.PERSON_NAME), resultSet.getDate(Data.PERSON_BIRTHDAY), resultSet.getInt(Data.PERSON_WEIGHT), resultSet.getString(Data.PERSON_PASSPORT_ID), Color.valueOf(resultSet.getString(Data.PERSON_COLOR)));
                databaseManager.closeStatement(findPerson);
            }
            else{
                databaseManager.closeStatement(findPerson);
                throw new SQLException();
            }
        } catch (SQLException e) {
            Console.printerr("Не удалось найти автора для LabWork с id=" + id);
        }
        return answer;
    }

    public Map<Integer, LabWork> getCollection() {
        PreparedStatement findAllLabWorks = null;
        Map<Integer, LabWork> answer = new HashMap<Integer, LabWork>();
        try{
            findAllLabWorks = databaseManager.getStatement(Data.COLLECT_LABWORKS, false);
            ResultSet resultSet = findAllLabWorks.executeQuery();
            while (resultSet.next()){
                answer.put(resultSet.getInt(Data.LABWORK_ID), createLabWork(resultSet));
            }
            databaseManager.closeStatement(findAllLabWorks);
        }
        catch (SQLException e){
            Console.printerr("Не удалось получить коллекцию!");
            databaseManager.closeStatement(findAllLabWorks);
        }
        return answer;
    }

    public void clearCollection(){
        Map<Integer, LabWork> data = new HashMap<Integer, LabWork>();
        data = getCollection();
        for (LabWork l: data.values()){
            deleteLabWorkById(l.getId());
        }
    }


    public void deleteLabWorkById(int id){
        /**PreparedStatement deleteLabWork = null;
        try{
            deleteLabWork = databaseManager.getStatement(Data.COLLECT_LABWORKS, false);
            ResultSet resultSet = findAllLabWorks.executeQuery();
            while (resultSet.next()){
                answer.put(resultSet.getInt(Data.LABWORK_ID), createLabWork(resultSet));
            }
            databaseManager.closeStatement(findAllLabWorks);
        }
        catch (SQLException e){
            Console.printerr("Не удалось получить коллекцию!");
            databaseManager.closeStatement(findAllLabWorks);
        }**/
    }




}
