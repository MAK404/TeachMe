package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class tutoridproof extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser fuser;
    StorageReference storageReference;
    ProgressDialog dialog;

String tid;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoridproof);
        tid=getIntent().getStringExtra("tid");
        img=findViewById(R.id.img);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        dialog = new ProgressDialog(tutoridproof.this);
        dialog.setMessage("Loading Image..,");
        dialog.show();
        StorageReference fileref=storageReference.child("tutorsandstudents/"+tid+"/idproof");
        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(tutoridproof.this, "Retreival Failed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
}