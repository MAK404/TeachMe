package com.example.teachme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;


public class tutorUpload extends AppCompatActivity {
    ImageView tprofile, tidproof;
    Button tprofilebtn, tidbtn, uploadbtn,intluploadbutton;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser fuser;
    StorageReference storageReference;
    String tutorID;
    Uri profileuri, iduri;
    ProgressBar idbar,prbar;
    TextView prtxtpr,idtxtpr;
    boolean flag1=false,flag2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentupload);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        tprofilebtn = findViewById(R.id.profilebutton);
        tidbtn = findViewById(R.id.idbutton);
        intluploadbutton=findViewById(R.id.intluploadbtn);
        tprofile = findViewById(R.id.profile);
        tidproof = findViewById(R.id.idproof);
        uploadbtn = findViewById(R.id.uploadbtn);
   tutorID = getIntent().getStringExtra("tutorID");
        idbar=findViewById(R.id.idbar);
        prbar=findViewById(R.id.prbar);
        idtxtpr=findViewById(R.id.idtxtpr);
        prtxtpr=findViewById(R.id.prtxtpr);

        tprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(tutorUpload.this)
                        .galleryOnly()
                        .cropSquare()
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)
                        .start(10);

            }
        });

        tidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(tutorUpload.this)
                        .galleryOnly()
                        .crop()
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)
                        .start(20);

            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag1==true && flag2==true) {
                    Intent intent123 = new Intent(getApplicationContext(), pleaseLogin.class);
                    startActivity(intent123);
                }

            }


        });

        intluploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OK()) {
                    idbar.setVisibility(View.VISIBLE);
                    prbar.setVisibility(View.VISIBLE);
                    idtxtpr.setVisibility(View.VISIBLE);
                    prtxtpr.setVisibility(View.VISIBLE);


                    StorageReference fileref = storageReference.child("tutorsandstudents/"+tutorID+"/profile");
                    StorageReference fileref2 = storageReference.child("tutorsandstudents/"+tutorID+"/idproof");

                    fileref.putFile(profileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            prbar.setProgress(0);
                            prtxtpr.setText("Uploaded 100%");
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                            prbar.setProgress((int) progress);
                            prtxtpr.setText(progress + "%");
                            if (progress == 100.0) {
                                flag1 = true;
                            }
                        }
                    });
                    fileref2.putFile(iduri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            idbar.setProgress(0);
                            idtxtpr.setText("Uploaded 100%");
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                            idbar.setProgress((int) progress);
                            idtxtpr.setText(progress + "%");
                            if (progress == 100.0) {
                                flag2 = true;
                            }
                        }
                    });


                    uploadbtn.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        {profileuri=data.getData();
            tprofile.setImageURI(profileuri);}
        if(requestCode==20)
        {iduri=data.getData();
            tidproof.setImageURI(iduri);}

    }
    public void onBackPressed() {
        moveTaskToBack(false);
    }



    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    public boolean OK() {
        if (tprofile.getDrawable() != null && tidproof.getDrawable() != null) {
            return true;
        } else {
            Toast.makeText(this, "Please Upload the Images", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}