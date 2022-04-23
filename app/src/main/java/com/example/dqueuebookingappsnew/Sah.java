package com.example.dqueuebookingappsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Sah extends AppCompatActivity {

    TextView displayFullName,displayPhoneNumber,displayDate,displayTime,displayGuest,displayPrice;
    Button book;
    String FullName,PhoneNumber,Date,Time,Guest,sprice;
    int dtime,dguest;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sah);

        displayFullName=findViewById(R.id.tvFullName);
        displayPhoneNumber=findViewById(R.id.tvPhoneNumber);
        displayDate=findViewById(R.id.tvDate);
        displayTime=findViewById(R.id.tvTime);
        displayGuest=findViewById(R.id.tvGuest);
        displayPrice=findViewById(R.id.tvRefer);
        book=findViewById(R.id.btnProceed);


         FullName =getIntent().getStringExtra("keyfullname");
         PhoneNumber =getIntent().getStringExtra("keyphonenumber");
         Date =getIntent().getStringExtra("keyuserdate");
         Time =getIntent().getStringExtra("keyusertime");
         Guest =getIntent().getStringExtra("keyuserguest");

        displayFullName.setText(FullName);
        displayPhoneNumber.setText(PhoneNumber);
        displayDate.setText(Date);
        displayTime.setText(Time);
        displayGuest.setText(Guest);

        dtime = Integer.parseInt(Time);
        dguest = Integer.parseInt(Guest);
        price = (dguest*(10.00/60))*dtime;
        sprice =  String.format("%.2f",price);
        displayPrice.setText(sprice);

        Intent intent = new Intent(Sah.this,UserPayment.class);
        intent.putExtra("keyfullname",FullName);
        intent.putExtra("keyphonenumber",PhoneNumber);
        intent.putExtra("keyuserdate",Date);
        intent.putExtra("keyusertime",Time);
        intent.putExtra("keyuserguest",Guest);
        intent.putExtra("keyuserprice",sprice);
        startActivity(intent);

    }
}