package com.example.finalproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class person_credentials {
    @PrimaryKey(autoGenerate = true)
    public int userid;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    public person_credentials(String email, String password){
        this.email = email;
        this.password = password;
    }
}
