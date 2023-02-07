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



public class coffee extends AppCompatActivity {
    private ArrayList<button_name> coffee_menu;
    private RecyclerView recyclerView;
    private recycler_adapter.RecyclerViewClickListener listener;
    Button add_to_cart;
    Button frappe;
    Button cappucino;
    Button latte;
    Button espresso;
    Button dopio;
    Button arabica;
    Button minus;
    Button plus;
    TextView quantity;
    TextView price;
    TextView item_name;
    ImageView image;
    double [] coffee_price = {0.00,2.99,3.49,2.99,3.49,1.99,4.99}; // array for the coffee's prices


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button

        coffee_menu = new ArrayList<>();
        recyclerView = findViewById(R.id.coffee_recycle);

        minus = findViewById(R.id.coffee_minus);
        plus = findViewById(R.id.coffee_add);
        quantity = findViewById(R.id.coffee_quantity);
        price = findViewById(R.id.coffee_price);
        item_name = findViewById(R.id.coffee_item_name);
        image = findViewById(R.id.coffee_image);
        add_to_cart = findViewById(R.id.add_to_cart_2);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(coffee.this, add_to_cart.class);
                startActivity(i);
            }
        });

        frappe = findViewById(R.id.frappe);
        frappe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Frappe");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[1]));
                image.setImageResource(R.drawable.frappe);
                add_or_minus(minus,quantity,plus,1,price);
            }
        });

        cappucino = findViewById(R.id.cappucino);
        cappucino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Cappucino");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[2]));
                image.setImageResource(R.drawable.cappuccino);
                add_or_minus(minus,quantity,plus,2,price);
            }
        });

        latte = findViewById(R.id.latte);
        latte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Latte");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[3]));
                image.setImageResource(R.drawable.latte);
                add_or_minus(minus,quantity,plus,3,price);
            }
        });

        espresso = findViewById(R.id.espresso);
        espresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Espresso");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[4]));
                image.setImageResource(R.drawable.espresso);
                add_or_minus(minus,quantity,plus,4,price);
            }
        });

        dopio = findViewById(R.id.dopio);
        dopio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Dopio");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[5]));
                image.setImageResource(R.drawable.doppio);
                add_or_minus(minus,quantity,plus,5,price);
            }
        });

        arabica = findViewById(R.id.arabica);
        arabica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("Arabica");
                quantity.setText(""+1);
                price.setText(Double.toString(coffee_price[6]));
                image.setImageResource(R.drawable.arabica);
                add_or_minus(minus,quantity,plus,6,price);
            }
        });

        setButtonName();
        setAdapter();
    }
    private void setAdapter(){
        setonClickListner();
        recycler_adapter adapter = new recycler_adapter(coffee_menu,listener);
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
        coffee_menu.add(new button_name("Frappe"));
        coffee_menu.add(new button_name("Cappuccino"));
        coffee_menu.add(new button_name("Latte"));
        coffee_menu.add(new button_name("Espresso"));
        coffee_menu.add(new button_name("Doppio"));
        coffee_menu.add(new button_name("Arabica"));
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
                    double temp1 = coffee_price[pos] * temp;
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
                double temp1 = coffee_price[pos] * temp;
                price.setText(Double.toString(temp1));
                temp = temp + 1;
                quantity.setText(Integer.toString(temp));
                return;
            }

        });
    }
}