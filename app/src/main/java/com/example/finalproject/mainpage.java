package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;

public class mainpage extends AppCompatActivity {


    Button breakfast;
    Button coffee;
    Button pastry;
    Button add_to_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);



        breakfast = findViewById(R.id.main_breakfast);
        coffee = findViewById(R.id.main_coffee);
        pastry = findViewById(R.id.main_pastry);

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainpage.this, breakfast.class);
                startActivity(i);
            }
        });

        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainpage.this, coffee.class);
                startActivity(i);
            }
        });

        pastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainpage.this, pastry.class);
                startActivity(i);
            }
        });

//        add_to_cart = findViewById(R.id.main_page_add_to_cart);
//
//        add_to_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(mainpage.this, add_to_cart.class);
//                startActivity(i);
//            }
//        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_bar,menu);
        return true;

    }
}