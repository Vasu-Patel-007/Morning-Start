package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class log_in extends AppCompatActivity {
    EditText email;
    EditText password;
    Button sign_in_button;
    Button sign_up_button;
    FirebaseAuth mAuth;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.sign_in_password);
        sign_in_button = findViewById(R.id.sign_in_button);
        sign_up_button = findViewById(R.id.sign_up_button);
        mAuth = FirebaseAuth.getInstance();
        System.out.println("hello");
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            // open sign up page on click
            @Override
            public void onClick(View view) {
                // moving to sign_up page
                Intent i = new Intent(log_in.this,sign_up.class);
                startActivity(i);
            }
        });

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            // on click call sign_in_user function
            @Override
            public void onClick(View view) {
                sign_in_user();
            }
        });



    }

    private void sign_in_user(){
        // get email and password and check if it is valid. if it is then open mainpage.
        String textfield_email = email.getText().toString();
        String textfield_password = password.getText().toString();
        Boolean flag = check_if_field_empty(textfield_email,textfield_password);

        if(!flag){
            // if field is not empty then do sign in process
            mAuth.signInWithEmailAndPassword(textfield_email,textfield_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        // on sign in complete
                       if(task.isSuccessful()){
                           // if task is successful then open mainpage
                           show_toast("User log in successful");
                           startActivity(new Intent(log_in.this,mainpage.class));
                       }
                       else{
                           // if task has failed. show the error toast
                           show_toast("Error logging in");
                       }
                }
            });
        }
    }

    private boolean check_if_field_empty(String textfield_email,String textfield_password){
        // this function basically check if the field is empty or not
        if(TextUtils.isEmpty(textfield_email)){
            // if email is empty return true and show error toast
            email.requestFocus();
            show_toast("Enter Email");
            return true;
        }
        else if(TextUtils.isEmpty(textfield_password)){
            // if password is empty then return true and show error
            password.requestFocus();
            show_toast("Enter Password");
            return true;
        }else if(textfield_password.length() <6){
            // if password length is less than 6 return true and show toast
            password.requestFocus();
            show_toast("Password length needs to be greater than 6");
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