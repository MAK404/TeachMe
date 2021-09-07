package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class adminwindow extends AppCompatActivity {
Button btn1,btn2,logout;
FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminwindow);
        fauth=FirebaseAuth.getInstance();
        btn1=findViewById(R.id.stdbtn);
        btn2=findViewById(R.id.tbtn);
        logout=findViewById(R.id.logout);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminwindow.this, showstudents.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminwindow.this, showtutors.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fauth.signOut();
                startActivity(new Intent(adminwindow.this,MainActivity.class));
                finish();
            }
        });
    }
}