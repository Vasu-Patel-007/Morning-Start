package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class add_to_cart extends AppCompatActivity {
    //Global variables
    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayofMonth;
    Calendar calendar;
    Button submite;

    EditText first_name;
    EditText last_name;
    EditText credit_card_number;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        //Get date from activity
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.selected_date);
        submite = findViewById(R.id.btnSend);

        first_name = findViewById(R.id.txt_first_name);
        last_name = findViewById(R.id.txt_last_name);
        credit_card_number = findViewById(R.id.txt_credit_card_number);

        selectDate.setOnClickListener((view) -> {
            //Set calender details
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(add_to_cart.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            //Set date text based on chosen date
                            date.setText((month +1) + "/" + year);
                        }
                    }, year, month, dayofMonth);
            //Display the date
            datePickerDialog.show();
        });

        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(TextUtils.isEmpty(first_name.getText().toString())){
//                    first_name.requestFocus();
//                    show_toast("Enter First Name");
//                }
//                else if(TextUtils.isEmpty(last_name.getText().toString())){
//
//                }
//                else if(TextUtils.isEmpty(credit_card_number.getText().toString())){
//
//                }
//                else{
//
//                }
                String textfield_first_name = first_name.getText().toString();
                String textfield_last_name = last_name.getText().toString();
                String textfield_credit_card_number = credit_card_number.getText().toString();
                Boolean flag = check_if_field_empty(textfield_first_name,textfield_last_name,textfield_credit_card_number);

                if(!flag){
                    show_toast("Thank you! Order Placed");
                }
            }
        });
    }

    private boolean check_if_field_empty(String temp1,String temp2, String temp3){
        // this function basically check if the field is empty or not
        if(TextUtils.isEmpty(temp1)){
            // if email is empty return true and show error toast
            first_name.requestFocus();
            show_toast("Enter First Name ");
            return true;
        }
        else if(TextUtils.isEmpty(temp2)) {
            // if password is empty then return true and show error
            last_name.requestFocus();
            show_toast("Enter Last Name");
            return true;
        }
        else if(temp3.length() <16){
            // if password length is less than 6 return true and show toast
            credit_card_number.requestFocus();
            show_toast("Credit card number needs to be 16 digit");
            return true;
        }
        else{
            // if everything is filled and all the conditions are met return false indicating that field is not empty.
            // password length is met
            return false;
        }
    }

    private void show_toast(CharSequence text){
        // this function basically prints the toast
        context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }
}