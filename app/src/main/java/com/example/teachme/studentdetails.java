package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class studentdetails extends AppCompatActivity {
    TextView tv,tv2,tv3,tv4,tv5;
    Button btn;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    Button btn2;
    StorageReference storageReference;
    String tid;
    String sid;
    String tmode;
    Uri iduri;
    StorageReference refr1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);
        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        btn=findViewById(R.id.btnaccept);
        btn2=findViewById(R.id.btndeny);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        img=findViewById(R.id.mainprofile);

        tid=fauth.getCurrentUser().getUid();
        sid=getIntent().getStringExtra("sid");
        storageReference= FirebaseStorage.getInstance().getReference();


        tv.setText(tv.getText()+" "+getIntent().getStringExtra("stname"));
        String citystatepin=String.format("%s/%s/%s",getIntent().getStringExtra("stpin"),getIntent().getStringExtra("stcity")
        ,getIntent().getStringExtra("ststate"));
        tv2.setText(tv2.getText()+" "+citystatepin);
        tv3.setText(tv3.getText()+" "+getIntent().getStringExtra("stclass"));
        tv4.setText(tv4.getText()+" "+String.format("%s/%s",getIntent().getStringExtra("stmail"),getIntent().getStringExtra("stmobile")));
         tv5.setText(tv5.getText()+" "+getIntent().getStringExtra("stmode"));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept();



            }
        });
btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        deny();

    }
});
        refr1=storageReference.child("tutorsandstudents/"+sid+"/profile");
        refr1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);

            }
        });

    }
    public void accept() {

        DocumentReference documentReference = fstore.collection("users").document(tid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                tmode=value.getString("tmode");
                String tname=value.getString("tname");
                String tmail=value.getString("tmail");
                Intent intent=new Intent(studentdetails.this,sendonline.class);
                intent.putExtra("tmode",tmode);
                intent.putExtra("sid",sid);
                intent.putExtra("tname",tname);
                intent.putExtra("tmail",tmail);
                startActivity(intent);



            }
        });
    }
    public void deny()
    {
        fstore.collection("users").document(tid).collection("tutorreq").document(sid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(studentdetails.this, "Student Deleted Successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                deletecollection();
            }
        });
       // startActivity(new Intent(studentdetails.this,studentrequests.class));
        finish();
    }

    private void deletecollection() {
        fstore.collection("users").document(tid).collection("tutorreq").document().delete();
    }

}