package com.example.dqueuebookingappsnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;

public class UserPayment extends AppCompatActivity {
    ImageView receipt;
    TextView price,bookingnum;
    String FullName,PhoneNumber,Date,Time,Guest,sprice;
    Button pay;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                receipt.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment);

        receipt = findViewById(R.id.ivReceipt);
        bookingnum = findViewById(R.id.tvBookingNumber);
        price = findViewById(R.id.tvPrice);
        pay = findViewById(R.id.btnPay);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"), PICK_IMAGE);
            }
        });


        FullName =getIntent().getStringExtra("keyfullname");
        PhoneNumber =getIntent().getStringExtra("keyphonenumber");
        Date =getIntent().getStringExtra("keyuserdate");
        Time =getIntent().getStringExtra("keyusertime");
        Guest =getIntent().getStringExtra("keyuserguest");
        sprice = getIntent().getStringExtra("keyuserprice");

        price.setText(sprice);


        Random r = new Random();
        int minNumber = 100000;
        int maxNumber = 999999;
        int random = r.nextInt((maxNumber-minNumber)+1)+minNumber;
        String bookingnumber = String.valueOf(random);
        bookingnum.setText(bookingnumber);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = firebaseDatabase.getReference("BOOKING DETAIL").child(firebaseAuth.getUid());
                Booking booking = new Booking(FullName,PhoneNumber,Date,Time,Guest,sprice,bookingnumber);


                StorageReference ir = storageReference.child("RECEIPT").child(firebaseAuth.getUid()).child("Images").child("Profile Pic");
                UploadTask uploadTask = ir.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserPayment.this, "Upload failed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UserPayment.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    }
                });
                databaseReference.setValue(booking);
                addNotification();
                startActivity(new Intent(UserPayment.this, SecondActivity.class));
            }
        });

    }


    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_lock_power_off)             //utk display icon kecil
                .setContentTitle("Booking successful")                           //utk title notification
                .setContentText("You have reserved a spot");                     //utk text mesej notification

        //bila dia click notification, dia akan pergi mana
        Intent notificationIntent = new Intent(this, MainActivity.class);

        //utk call notification keluar
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0 , notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }



}