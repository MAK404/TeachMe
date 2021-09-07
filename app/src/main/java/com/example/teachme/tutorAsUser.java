package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class tutorAsUser extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser fuser;
    StorageReference storageReference;
    int change;
    String tutorID;
    ImageView img,img2;
    ListenerRegistration xyz;
    TextView tvx;
    String tell="";
    TextView name,age,gender,mode,skills,city,state,timing;
    Button changeid,changeprofile,logout;

    Uri profileuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_as_user);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        img=findViewById(R.id.imageView);
        img2=findViewById(R.id.imageView2);
        changeid=findViewById(R.id.changeid);
        changeprofile=findViewById(R.id.changeprofile);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        mode=findViewById(R.id.mode);
        skills=findViewById(R.id.skills);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        timing=findViewById(R.id.timing);
        logout=findViewById(R.id.logoutsbtn);
        tutorID=fauth.getCurrentUser().getUid();


        StorageReference fileref=storageReference.child("tutorsandstudents/"+tutorID+"/profile");
        StorageReference fileref2 =storageReference.child("tutorsandstudents/"+tutorID+"/idproof");
        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
            }
        });

        fileref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img2);
            }
        });

        DocumentReference documentReference = fstore.collection("users").document(tutorID);
        xyz=documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(name.getText().toString()+" "+value.getString("tname"));
                age.setText(age.getText().toString()+" "+value.getString("tage"));
                gender.setText(gender.getText().toString()+" "+value.getString("tgender"));
                city.setText(city.getText().toString()+" "+value.getString("tcity"));
                state.setText(state.getText().toString()+" "+value.getString("tstate"));
                skills.setText(skills.getText().toString()+" "+value.getString("tskills"));
                mode.setText(mode.getText().toString()+" "+value.getString("tmode"));
                timing.setText(timing.getText().toString()+" "+value.getString("timing"));



            }
        });


logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        xyz.remove();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(tutorAsUser.this, MainActivity.class));

    }
});


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}