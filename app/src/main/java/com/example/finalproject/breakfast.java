package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class breakfast extends AppCompatActivity {

    private ArrayList<button_name> breakfast_menu;
    private RecyclerView recyclerView;
    private recycler_adapter.RecyclerViewClickListener listener;
    Button add_to_cart;
    ImageView image;
    Button waffle;
    Button toast;
    Button pancake;
    Button muffin;
    Button bagel;
    Button croissant;
    TextView item_name;
    TextView price;
    Button plus;
    Button minus;
    TextView quantity;
    double [] breakfast_price = {0.00,3.99,2.99,4.99,1.99,3.49,2.49}; // array for the breakfast's prices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button
        breakfast_menu = new ArrayList<>();
        recyclerView = findViewById(R.id.breakfast_recycle);
        image = (ImageView) findViewById(R.id.breakfast_image);
        item_name = findViewById(R.id.breakfast_item_name);
        add_to_cart = findViewById(R.id.add_to_cart1);
        price = findViewById(R.id.breakfast_price);
        plus = findViewById(R.id.breakfast_add);
        minus = findViewById(R.id.breakfast_minus);
        quantity = findViewById(R.id.breakfast_quantity);


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(breakfast.this, add_to_cart.class);
                startActivity(i);
            }
        });

        waffle = findViewById(R.id.waffle);
        waffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Waffle");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[1]));
                image.setImageResource(R.drawable.waffles);
                add_or_minus(minus,quantity,plus,1,price);
            }
        });

        toast = findViewById(R.id.toast);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Toast");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[2]));
                image.setImageResource(R.drawable.frenchtoast);
                add_or_minus(minus,quantity,plus,2,price);
            }
        });

        pancake = findViewById(R.id.pancake);
        pancake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Pancake");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[3]));
                image.setImageResource(R.drawable.pancake);
                add_or_minus(minus,quantity,plus,3,price);
            }
        });

        muffin = findViewById(R.id.muffin);
        muffin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Muffin");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[4]));
                image.setImageResource(R.drawable.muffin);
                add_or_minus(minus,quantity,plus,4,price);
            }
        });

        bagel = findViewById(R.id.bagel);
        bagel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Bagel");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[5]));
                image.setImageResource(R.drawable.bagel);
                add_or_minus(minus,quantity,plus,5,price);
            }
        });

        croissant = findViewById(R.id.croissant);
        croissant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Croissant");
                quantity.setText(""+1);
                price.setText(Double.toString(breakfast_price[6]));
                image.setImageResource(R.drawable.croissant);
                add_or_minus(minus,quantity,plus,6,price);
            }
        });


        setButtonName();
        setAdapter();
    }

    private void setAdapter(){
        recycler_adapter adapter = new recycler_adapter(breakfast_menu,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    private void setButtonName(){
        breakfast_menu.add(new button_name("Waffle"));
        breakfast_menu.add(new button_name("Toast"));
        breakfast_menu.add(new button_name("Pancake"));
        breakfast_menu.add(new button_name("Muffin"));
        breakfast_menu.add(new button_name("Bagel"));
        breakfast_menu.add(new button_name("Croissant"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_bar,menu);
        return true;

    }
    private void add_or_minus(Button minus, TextView quantity, Button plus, int pos, TextView price){
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt((String) quantity.getText());
                if (temp == 1){
                    return;
                }
                else{
                    temp = temp - 1;
                    double temp1 = breakfast_price[pos] * temp;
                    price.setText(Double.toString(temp1));
                    quantity.setText(Integer.toString(Integer.parseInt(""+Integer.toString(temp))));
                    return;
                }

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt((String) quantity.getText());
                double temp1 = breakfast_price[pos] * temp;
                price.setText(Double.toString(temp1));
                temp = temp + 1;
                quantity.setText(Integer.toString(temp));
                return;
            }

        });
    }
}