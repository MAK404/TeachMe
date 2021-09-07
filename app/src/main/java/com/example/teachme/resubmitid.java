package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class resubmitid extends AppCompatActivity {
    ImageView tidproof;
    Button profilebtn,exitbtn,upldbtn,intentbtn;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    StorageReference storageReference;
    String ID;
    ProgressDialog dialog;
    Uri iduri;
    ProgressBar idbar;
    TextView idtxtpr;
    boolean flag1=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resubmitid);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        tidproof=findViewById(R.id.imageView3);
        dialog = new ProgressDialog(resubmitid.this);
        dialog.setMessage("Uploading...");
        profilebtn=findViewById(R.id.profilebtn);
        exitbtn=findViewById(R.id.exitbtn);
        upldbtn=findViewById(R.id.upldbtn);
        intentbtn=findViewById(R.id.intentbtn);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(resubmitid.this)
                        .galleryOnly()
                        .crop()
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)
                        .start(10);

            }
        });
        intentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1)
                {   fauth.signOut();
                    Intent intent123 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent123);
                }
            }
        });
        upldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(OK())
                    {  dialog.show();
                        StorageReference fileref = storageReference.child("tutorsandstudents/"+fauth.getCurrentUser().getUid()+"/idproof");
                        fileref.putFile(iduri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                prbar.setProgress(0);
//                                prtxtpr.setText("Uploaded 100%");
                                Toast.makeText(resubmitid.this, "Uploaded Successfully change or Login", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progress = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                                if (progress == 100.0) {
                                    {
                                        flag1 = true;
                                        dialog.dismiss();
                                        //Toast.makeText(resubmitid.this, "Uploaded Successfully Will be Approved by Admin", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                        //intentbtn.setVisibility(View.VISIBLE);
                    }
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fauth.signOut();
                Intent intent123 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent123);
            }
        });

    }

    private boolean OK() {
        if (tidproof.getDrawable() != null) {
            return true;
        } else {
            Toast.makeText(this, "Please Upload the Image", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        {iduri=data.getData();
            tidproof.setImageURI(iduri);}
       }
}