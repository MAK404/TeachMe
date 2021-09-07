package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class tutorafterlogin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    ListenerRegistration xy;
    StorageReference storageReference;
    String tutorID;
    TextView txt;
    Uri iduri;
    StorageReference refr1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorafterlogin2);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        tutorID=firebaseAuth.getCurrentUser().getUid();
        txt= findViewById(R.id.profiletxt);
        img=findViewById(R.id.mainprofile);
        storageReference= FirebaseStorage.getInstance().getReference();

        refr1=storageReference.child("tutorsandstudents/"+tutorID+"/profile");
        refr1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(tutorafterlogin.this)
                        .galleryOnly()
                        .cropSquare()
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)
                        .start(10);
                //
            }
        });


        DocumentReference documentReference = firestore.collection("users").document(tutorID);
        xy=documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txt.setText(txt.getText().toString()+value.getString("tname"));
            }
        });

    }
    public void profilebtn(View view)
    {
         startActivity(new Intent(tutorafterlogin.this, tutorprofile.class));
    }
    public void chat(View view)
    {
            startActivity(new Intent(tutorafterlogin.this,showyesbyme.class));
    }
    public void studentreq(View view)
    {
        startActivity(new Intent(tutorafterlogin.this, studentrequests.class));
    }
    public void logout(View view)
    {  xy.remove();
        firebaseAuth.signOut();
        startActivity(new Intent(tutorafterlogin.this,MainActivity.class));
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            iduri = data.getData();
            if (iduri == null) {
                return;
            } else {
                img.setImageURI(iduri);
                refr1.putFile(iduri);
            }

        }

    }
}