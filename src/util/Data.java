package util;

public class Data {
    //tables
    public static final String COORDINATES_TABLE = "coordinates";
    public static final String USER_TABLE = "users";
    public static final String PERSON_TABLE = "persons";
    public static final String LABWORK_TABLE = "labworks";
    //coordinates columns
    public static final String COORDINATES_ID = "id";
    public static final String COORDINATES_X = "x";
    public static final String COORDINATES_Y = "y";
    public static final String COORDINATES_LABWORK_ID = "labwork_id";
    //labworks columns
    public static final String LABWORK_ID = "id";
    public static final String LABWORK_NAME = "name";
    public static final String LABWORK_COORDINATES_ID = "coordinates_id";
    public static final String LABWORK_CREATION_DATE = "creation_date";
    public static final String LABWORK_MINIMAL_POINT = "minimal_point";
    public static final String LABWORK_PERSONAL_QUALITIES_MAXIMUM = "personal_qualities_maximum";
    public static final String LABWORK_DIFFICULTY = "difficulty";
    public static final String LABWORK_AUTHOR_ID = "author_id";
    public static final String LABWORK_USER_ID = "user_id";
    //persons columns
    public static final String PERSON_ID = "id";
    public static final String PERSON_NAME = "name";
    public static final String PERSON_BIRTHDAY = "birthday";
    public static final String PERSON_WEIGHT = "weight";
    public static final String PERSON_PASSPORT_ID = "passport_id";
    public static final String PERSON_LABWORK_ID = "labwork_id";
    public static final String PERSON_COLOR= "color";
    //users table
    public static final String USERS_ID = "id";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";
    //user algorithms
    public static final String ADD_USER = "INSERT INTO " + USER_TABLE + " (" + USERS_USERNAME + ", " + USERS_PASSWORD + ") " + "VALUES (?, ?)";
    public static final String USER_BY_ID = "SELECT * FROM" + USER_TABLE + " WHERE " + USERS_ID + " = ?";
    public static final String USER_BY_LOGIN = "SELECT * FROM" + USER_TABLE + " WHERE " + USERS_USERNAME + " = ?";
    public static final String USER_BY_DATA = USER_BY_LOGIN + " AND " + USERS_PASSWORD + " = ?";
    //coordinates algorithms
    public static final String COORDINATES_BY_LABWORK_ID = "SELECT * FROM" + COORDINATES_TABLE + " WHERE " + COORDINATES_LABWORK_ID + " = ?";
    public static final String COORDINATES_BY_ID = "SELECT * FROM" + COORDINATES_TABLE + " WHERE " + COORDINATES_ID + " = ?";
    public static final String UPDATE_COORDINATES_BY_LABWORK_ID = "UPDATE " + COORDINATES_TABLE + " SET " + COORDINATES_X + " = ?, " + COORDINATES_Y + " = ?" + " WHERE " + COORDINATES_LABWORK_ID + " = ?";
    public static final String ADD_COORDINATES = "INSERT INTO " + COORDINATES_TABLE + " (" + COORDINATES_X + ", " + COORDINATES_Y + ", " + COORDINATES_LABWORK_ID + ") " + "VALUES (?, ?, ?)";
    //persons algorithms
    public static final String PERSON_BY_LABWORK_ID = "SELECT * FROM" + PERSON_TABLE + " WHERE " + PERSON_LABWORK_ID + " = ?";
    public static final String PERSON_BY_ID = "SELECT * FROM" + PERSON_TABLE + " WHERE " + PERSON_ID + " = ?";
    public static final String UPDATE_PERSON_BY_LABWORK_ID = "UPDATE " + PERSON_TABLE + " SET " + PERSON_BIRTHDAY + " = ?, " + PERSON_WEIGHT + " = ?, " + PERSON_PASSPORT_ID + " = ?" + " WHERE " + COORDINATES_LABWORK_ID + " = ?";
    public static final String ADD_PERSON = "INSERT INTO " + PERSON_TABLE + " (" + PERSON_BIRTHDAY + ", " + PERSON_WEIGHT + ", " + PERSON_PASSPORT_ID + ", " + PERSON_BY_LABWORK_ID + ") " + "VALUES (?, ?, ?, ?)";
    //labwork algorithms
    public static final String LABWORK_BY_ID = "SELECT * FROM " + LABWORK_TABLE + " WHERE " + LABWORK_ID + " = ?";
    public static final String LABWORK_BY_USER_AND_ID = "SELECT * FROM " + LABWORK_TABLE + " WHERE " + LABWORK_ID + " = ?" + " AND " + LABWORK_USER_ID + " = ?";
    public static final String ADD_LABWORK = "INSERT INTO " + LABWORK_TABLE + " (" + LABWORK_NAME + ", " + LABWORK_COORDINATES_ID + ", " + LABWORK_CREATION_DATE +
            ", " + LABWORK_MINIMAL_POINT + ", " + LABWORK_PERSONAL_QUALITIES_MAXIMUM + ", " + LABWORK_DIFFICULTY + ", " + LABWORK_AUTHOR_ID + ", "
            + LABWORK_USER_ID + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_LABWORK_BY_ID = "DELETE FROM " + LABWORK_TABLE + " WHERE " + LABWORK_ID + " = ?";
    public static final String UPDATE_LABWORK_NAME_BY_ID = "UPDATE  " + LABWORK_TABLE + " SET " + LABWORK_NAME + " = ?" + " WHERE " + LABWORK_ID + " = ?";
    public static final String UPDATE_LABWORK_CREATION_DATE_BY_ID = "UPDATE " + LABWORK_TABLE + " SET " + LABWORK_CREATION_DATE + " = ?" + " WHERE " + LABWORK_ID + " = ?";
    public static final String UPDATE_LABWORK_MINIMAL_POINT_BY_ID = "UPDATE " + LABWORK_TABLE + " SET " + LABWORK_MINIMAL_POINT + " = ?" + " WHERE " + LABWORK_ID + " = ?";
    public static final String UPDATE_LABWORK_PERSONAL_QUALITIES_MAXIMUM = "UPDATE " + LABWORK_TABLE + " SET " + LABWORK_PERSONAL_QUALITIES_MAXIMUM + " = ?" + " WHERE " + LABWORK_ID + " = ?";
    public static final String UPDATE_LABWORK_DIFFICULTY_BY_ID = "UPDATE " + LABWORK_TABLE + " SET " + LABWORK_DIFFICULTY + " = ?" + " WHERE " + LABWORK_ID + " = ?";
    public static final String COLLECT_LABWORKS = "SELECT * FROM " + LABWORK_TABLE;

}
