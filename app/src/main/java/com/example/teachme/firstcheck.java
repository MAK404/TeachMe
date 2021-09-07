package com.example.teachme;

import android.app.Application;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class firstcheck extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth fauth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        FirebaseUser ifuser = FirebaseAuth.getInstance().getCurrentUser();


        if(ifuser!=null)
        {
              String fuser=ifuser.getUid();

            DocumentReference df =fstore.collection("users").document(fuser);
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("tutortype")!=null)
                    {
                        Intent i = new Intent(firstcheck.this, tutorAsUser.class);
                      //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }
                    if(documentSnapshot.getString("studenttype")!=null)
                    {
                        Intent i = new Intent(firstcheck.this, studentasuser.class);
                      //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }
            });
        }
        else
        {
            Intent i = new Intent(firstcheck.this, MainActivity.class);
            startActivity(i);

        }

    }
}

