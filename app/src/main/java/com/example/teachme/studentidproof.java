package com.example.teachme;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class studentidproof extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser fuser;
    FirebaseStorage str;

    StorageReference storageReference12,ref12;
    String sid;
    Button btndelete;
    ImageView img;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentidproof);
        sid=getIntent().getStringExtra("tid");
        img=findViewById(R.id.img);
        btndelete=findViewById(R.id.btndelete);
        Log.i("T","SID:"+sid);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        str=FirebaseStorage.getInstance();
        dialog = new ProgressDialog(studentidproof.this);
        dialog.setMessage("Loading Image..,");
        dialog.show();
        storageReference12= FirebaseStorage.getInstance().getReference();

        StorageReference fileref12=storageReference12.child("tutorsandstudents/"+sid+"/idproof");
        fileref12.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(studentidproof.this, "Retreival Failed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


     }
}