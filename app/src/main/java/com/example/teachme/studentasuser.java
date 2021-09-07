package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
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

public class studentasuser extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser fuser;
    StorageReference storageReference;
    DocumentReference documentReference;
    int change;
    MainActivity mainActivity;
    String tutorID;
    ImageView img,img2;
    TextView tvx;
    ListenerRegistration abc;
    String tell="";
    TextView name,age,gender,mode,classedu,city,state,timing;
    Button changeid,changeprofile,logout;

    Uri profileuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentasuser);
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            fauth = FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();
            tutorID = fauth.getCurrentUser().getUid();
            storageReference = FirebaseStorage.getInstance().getReference();
            documentReference = fstore.collection("users").document(tutorID);
        }
        img=findViewById(R.id.imageView);
        img2=findViewById(R.id.imageView2);
        changeid=findViewById(R.id.changeid);
        changeprofile=findViewById(R.id.changeprofile);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        mode=findViewById(R.id.mode);
        classedu=findViewById(R.id.skills);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        timing=findViewById(R.id.timing);
        logout=findViewById(R.id.logoutsbtn);
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {


            StorageReference fileref = storageReference.child("tutorsandstudents/" + tutorID + "/profile");
            StorageReference fileref2 = storageReference.child("tutorsandstudents/" + tutorID + "/idproof");
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

              showdetails();

        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abc.remove();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(studentasuser.this, MainActivity.class));

            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
    public void showdetails()
    {


              abc = documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    name.setText(name.getText().toString() + " " + value.getString("stname"));
                    age.setText(age.getText().toString() + " " + value.getString("stage"));
                    gender.setText(gender.getText().toString() + " " + value.getString("stgender"));
                    city.setText(city.getText().toString() + " " + value.getString("stcity"));
                    state.setText(state.getText().toString() + " " + value.getString("ststate"));
                    classedu.setText(classedu.getText().toString() + " " + value.getString("stclass"));
                    mode.setText(mode.getText().toString() + " " + value.getString("stmode"));
                    timing.setVisibility(View.INVISIBLE);


                }
            });

    }
}