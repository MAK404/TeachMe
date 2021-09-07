package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class approval extends AppCompatActivity {
FirebaseAuth fauth;
Button btnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        fauth=FirebaseAuth.getInstance();
        btnext=findViewById(R.id.btnext);
btnext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fauth.signOut();
        Intent i = new Intent(approval.this, MainActivity.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
});
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
            fauth.signOut();
        Intent i = new Intent(approval.this, MainActivity.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}