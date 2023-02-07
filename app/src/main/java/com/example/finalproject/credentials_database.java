package com.example.finalproject;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {person_credentials.class}, version = 1)
public abstract class credentials_database extends RoomDatabase {
    public abstract person_caredential_Dao person_caredential_dao();
}
