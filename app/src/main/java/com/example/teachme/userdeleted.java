package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userdeleted extends AppCompatActivity {
    FirebaseAuth fauth;
    Button btnext;
    FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdeleted);

    fauth=FirebaseAuth.getInstance();
    fuser=fauth.getCurrentUser();
    btnext=findViewById(R.id.btnext);
btnext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fuser.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });
            fauth.signOut();
            Intent i = new Intent(userdeleted.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    });
}


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fuser.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        fauth.signOut();
        Intent i = new Intent(userdeleted.this, MainActivity.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}