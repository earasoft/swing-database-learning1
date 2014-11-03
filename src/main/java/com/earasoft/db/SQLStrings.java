package com.earasoft.db;

public class SQLStrings {
    public static final String CREATE_PERSON_TABLE = "CREATE TABLE IF NOT EXISTS person (personId INTEGER PRIMARY KEY AUTOINCREMENT, firstName varchar(100), lastName varchar(255), phoneNumber varchar(32));";
    public static final String CREATE_PERSON_INDEX = "";
    public static final String DROP_PERSON_TABLE = "DROP TABLE IF exists person";
    
}
