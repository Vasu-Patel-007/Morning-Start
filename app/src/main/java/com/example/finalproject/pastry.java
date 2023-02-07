package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class pastry extends AppCompatActivity {
    private ArrayList<button_name> pastry_menu;
    private RecyclerView recyclerView;
    private recycler_adapter.RecyclerViewClickListener listener;
    Button add_to_cart;

    Button boston_creme_pie;
    Button raspberry_pie;
    Button danish;
    Button studel;
    Button cannoli;
    Button bear_claw;

    Button minus;
    Button plus;

    TextView item_name;
    TextView quantity;
    TextView price;
    ImageView image;
    double [] pastries_price = {0.00,1.99,2.49,2.99,4.49,5.99,6.99}; // array for the pastries prices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button

        pastry_menu = new ArrayList<>();
        recyclerView = findViewById(R.id.pastry_recycle);

        minus = findViewById(R.id.pastry_minus);
        plus = findViewById(R.id.pastry_add);
        quantity = findViewById(R.id.pastry_quantity);
        price = findViewById(R.id.pastry_price);
        item_name = findViewById(R.id.pastry_item_name);
        image = findViewById(R.id.pastry_image);

        add_to_cart = findViewById(R.id.add_to_cart_3);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pastry.this, add_to_cart.class);
                startActivity(i);
            }
        });

        boston_creme_pie = findViewById(R.id.creme_pie);
        boston_creme_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Boston Creme Pie");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[1]));
                image.setImageResource(R.drawable.boston_creme_pie);
                add_or_minus(minus,quantity,plus,1,price);
            }
        });

        raspberry_pie = findViewById(R.id.rapberry_pie);
        raspberry_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Raspberry Pie");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[2]));
                image.setImageResource(R.drawable.raspberrypie);
                add_or_minus(minus,quantity,plus,2,price);
            }
        });

        danish = findViewById(R.id.danish);
        danish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Danish");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[3]));
                image.setImageResource(R.drawable.danish);
                add_or_minus(minus,quantity,plus,3,price);
            }
        });

        studel = findViewById(R.id.strudel);
        studel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Strudel");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[4]));
                image.setImageResource(R.drawable.strudel);
                add_or_minus(minus,quantity,plus,4,price);
            }
        });

        cannoli = findViewById(R.id.cannoli);
        cannoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Cannoli");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[5]));
                image.setImageResource(R.drawable.cannoli);
                add_or_minus(minus,quantity,plus,5,price);
            }
        });

        bear_claw = findViewById(R.id.bear_claw);
        bear_claw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Bear Claw");
                quantity.setText(""+1);
                price.setText(Double.toString(pastries_price[6]));
                image.setImageResource(R.drawable.bearclaw);
                add_or_minus(minus,quantity,plus,6,price);
            }
        });

        setButtonName();
        setAdapter();
    }
    private void setAdapter(){
        setonClickListner();
        recycler_adapter adapter = new recycler_adapter(pastry_menu,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void setonClickListner() {
        listener = new recycler_adapter.RecyclerViewClickListener() {
            @Override
            public void onclick(View v, int position) {
                System.out.println("hello");
            }
        };
    }

    private void setButtonName(){
        pastry_menu.add(new button_name("Cream Pie"));
        pastry_menu.add(new button_name("Raspberry Pie"));
        pastry_menu.add(new button_name("Danish"));
        pastry_menu.add(new button_name("Strudel"));
        pastry_menu.add(new button_name("Cannoli"));
        pastry_menu.add(new button_name("Bear Claw"));
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
                    double temp1 = pastries_price[pos] * temp;
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
                double temp1 = pastries_price[pos] * temp;
                price.setText(Double.toString(temp1));
                temp = temp + 1;
                quantity.setText(Integer.toString(temp));
                return;
            }

        });
    }
}