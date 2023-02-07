package com.example.finalproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface person_caredential_Dao {
    @Insert
    void insertAll(person_credentials... person);

    @Query("SELECT * FROM person_credentials")
    List<person_credentials> getAllPearsons();


}
