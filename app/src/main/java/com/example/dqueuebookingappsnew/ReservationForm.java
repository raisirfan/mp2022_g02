package com.example.dqueuebookingappsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReservationForm extends AppCompatActivity {
    EditText fullname,phonenumber,date,time,guest;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        fullname = findViewById(R.id.etFullName);
        phonenumber= findViewById(R.id.etPhoneNumber);
        date = findViewById(R.id.etDate);
        time= findViewById(R.id.etTime);
        guest=findViewById(R.id.etGuest);
        book=findViewById(R.id.btnBook);


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userFullName = fullname.getText().toString();
                String userPhoneNumber = phonenumber.getText().toString();
                String userDate= date.getText().toString();
                String userTime = time.getText().toString();
                String userGuest = guest.getText().toString();


               Intent intent = new Intent(ReservationForm.this, Sah.class);
                intent.putExtra("keyfullname",userFullName);
                intent.putExtra("keyphonenumber",userPhoneNumber);
                intent.putExtra("keyuserdate",userDate);
                intent.putExtra("keyusertime",userTime);
                intent.putExtra("keyuserguest",userGuest);
                Toast.makeText(ReservationForm.this, "Successfully Booked, Upload complete!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}