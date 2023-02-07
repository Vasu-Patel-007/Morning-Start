package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class sign_up extends AppCompatActivity {
    EditText email;
    EditText password;
    Button sign_up_button;
    Context context;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button
        email = findViewById(R.id.sign_up_email);
        password = findViewById(R.id.sign_up_password);
        sign_up_button = findViewById(R.id.sign_up_page_sign_up_button);
        System.out.println("hello");
        mAuth = FirebaseAuth.getInstance();
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            // on click call the function register_user() to register user.
            @Override
            public void onClick(View view) {
                register_user();
            }
        });
    }
    private void register_user(){
        String textfield_email = email.getText().toString();
        String textfield_password = password.getText().toString();

        Boolean flag = check_if_field_empty(textfield_email,textfield_password);
        //System.out.println(flag); // debugging purposes
        if(!flag){
            // if all the fields are not empty and password length is met then register user.
            mAuth.createUserWithEmailAndPassword(textfield_email,textfield_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                // creates the user entry in firebase database
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // checking if the task is complete
                    if(task.isSuccessful()) {
                        // if task is success, then open log in page
                        // adds user to the database.
                        credentials_database db = Room.databaseBuilder(getApplicationContext(),
                                credentials_database.class, "user-info").allowMainThreadQueries().build();

                        person_credentials user = new person_credentials(textfield_email,textfield_password);

                        db.person_caredential_dao().insertAll(user);

                        List<person_credentials> user_list = db.person_caredential_dao().getAllPearsons();

                        //to show the databse data into the logcat terminal.
//                        for (int i =0; i < user_list.size(); i++){
//                            Log.d("persons" ,user_list.get(i).email + " " + user_list.get(i).password);
//                        }


                        show_toast("User registered successfully");
                        startActivity(new Intent(sign_up.this, log_in.class));
                    }
                    else{
                        // if email is already exist then, show the error toast
                        show_toast("Email already exist");
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