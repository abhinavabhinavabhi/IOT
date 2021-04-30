package com.example.iothome;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


TextView t1,t2,t3;
Button b1;
String motorc;
ProgressBar pb1,pb2,pb3;
int water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        t3=findViewById(R.id.lightval);
        t2=findViewById(R.id.moistvalu);
        t1=findViewById(R.id.distval);
        b1=findViewById(R.id.motorbut);
        pb1=findViewById(R.id.progreslight);
        pb2=findViewById(R.id.progresmois);
        pb3=findViewById(R.id.progresdist);



           DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Distance");
           final DatabaseReference mois = FirebaseDatabase.getInstance().getReference("Moisture");
           DatabaseReference light = FirebaseDatabase.getInstance().getReference("Light");
           final DatabaseReference motor = FirebaseDatabase.getInstance().getReference("motor");

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{ String dist = dataSnapshot.getValue().toString();

                t1.setText(dist+"%");
                water = Integer.parseInt(dist);
                pb3.setProgress(water);}catch (Exception E){
               Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
           }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mois.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{ String mois = dataSnapshot.getValue().toString();
               t2.setText(mois+"%");
                int moisture=Integer.parseInt(mois);
               pb2.setProgress(moisture);}catch (Exception E){ Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       light.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    String light=dataSnapshot.getValue().toString();

                    t3.setText(light+"V");

                    int lightval=(int)Math.round(Float.parseFloat(light));
               pb1.setProgress(lightval);
                 }catch (Exception E){
               Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
           }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

           motor.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               try{ motorc=dataSnapshot.getValue().toString();
               b1.setText(motorc);}catch (Exception E){
                   Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            try {
                if (motorc == "OFF") {
                    motor.setValue("ON");

                    b1.setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "Motor is turned ON", Toast.LENGTH_LONG).show();
                } else if (motorc == "ON") {
                    motor.setValue("OFF");

                    b1.setBackgroundColor(Color.GREEN);
                    Toast.makeText(getApplicationContext(), "Motor is turned OFF", Toast.LENGTH_LONG).show();
                } else {

                    motor.setValue("OFF");
                    motorc = "OFF";
                }
            }catch (Exception E){
                Toast.makeText(getApplicationContext(),E.getMessage(),Toast.LENGTH_LONG).show();
            }
           }
       });








    }

}
