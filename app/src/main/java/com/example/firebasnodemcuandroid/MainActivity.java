package com.example.firebasnodemcuandroid;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mydb, led;
    TextView suhu, kelembapan;

    Button btnhidup, btnmati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        suhu = (TextView) findViewById(R.id.suhu);
        kelembapan = (TextView) findViewById(R.id.kelembapan);
        mydb = FirebaseDatabase.getInstance().getReference().child("esiot-db");
        btnhidup = findViewById(R.id.btnhidup);
        btnmati = findViewById(R.id.btnmati);

        try {
            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String tempData = dataSnapshot.child("suhu").getValue().toString();
                    String humData = dataSnapshot.child("kelembapan").getValue().toString();
//                    String isOn = dataSnapshot.child("led").getValue().toString();
                    suhu.setText(tempData);
                    kelembapan.setText(humData);
                    btnhidup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mydb.child("led").setValue(1);

//                            if (isOn.equals('0')) {
//                                mydb.child("led").setValue(1);
//                                ledButton.setText("Turn LED On");
//                            }
//                            else {
////                                led.setValue(0);
//                                ledButton.setText("Turn LED Off");
//                            }
                        }
                    });
                    btnmati.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mydb.child("led").setValue(0);

//                            if (isOn.equals('0')) {
//                                mydb.child("led").setValue(1);
//                                ledButton.setText("Turn LED On");
//                            }
//                            else {
////                                led.setValue(0);
//                                ledButton.setText("Turn LED Off");
//                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
//                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } catch (Exception e) {
        }
    }
}
