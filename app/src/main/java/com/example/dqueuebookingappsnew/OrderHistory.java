package com.example.dqueuebookingappsnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class OrderHistory extends AppCompatActivity {
    TextView displayFullName,displayPhoneNumber,displayDate,displayTime,displayGuest,displayPrice,displayBookingNumber;
    String FullName,PhoneNumber,Date,Time,Guest,sprice;
    Button menu;
    ImageView displayReceipt;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        displayFullName=findViewById(R.id.tvFullName);
        displayPhoneNumber=findViewById(R.id.tvPhoneNumber);
        displayDate=findViewById(R.id.tvDate);
        displayTime=findViewById(R.id.tvTime);
        displayGuest=findViewById(R.id.tvGuest);
        displayPrice=findViewById(R.id.tvRefer);
        menu = findViewById(R.id.btnMenu);
        displayBookingNumber = findViewById(R.id.tvDisplayBookingNumber);
        displayReceipt = findViewById(R.id.ivDisplayReceipt);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase.getReference("BOOKING DETAIL").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Booking booking = snapshot.getValue(Booking.class);
                displayBookingNumber.setText("BOOKING NUMBER: "+booking.getBookingnumber());
                displayFullName.setText("FULL NAME: "+booking.getFullname());
                displayPhoneNumber.setText("PHONE NUMBER: "+booking.getPhonenumber());
                displayDate.setText("DATE: "+booking.getDate());
                displayTime.setText("TIME: "+booking.getNumberofguest());
                displayGuest.setText("GUESTS: "+booking.getNumberofguest());
                displayPrice.setText("PRICE:RM "+booking.getPrice());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("RECEIPT").child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(displayReceipt);
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistory.this,SecondActivity.class));
            }
        });
    }
}