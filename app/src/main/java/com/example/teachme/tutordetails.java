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
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class tutordetails extends AppCompatActivity {
TextView tv,tv2,tv3,tv4,tv5;
Button btn;
FirebaseAuth fauth;
FirebaseFirestore fstore;
    StorageReference storageReference;
String currentuser;
String stname,stage,stcity,stpin,stclass,stmode,stmobile,stmail,ststate;
String tid;
String agegen;
    Uri iduri;
    StorageReference refr1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutordetails);
        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        btn=findViewById(R.id.senreq);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        currentuser=fauth.getCurrentUser().getUid();
        img=findViewById(R.id.mainprofile);
        storageReference= FirebaseStorage.getInstance().getReference();



        String citypinstate=String.format("%s/%s/%s", getIntent().getStringExtra("tcity"),getIntent().getStringExtra("tpin"),
                getIntent().getStringExtra("tstate"));
        tv.setText(tv.getText()+" "+getIntent().getStringExtra("tname"));
        tv2.setText(tv2.getText()+" "+citypinstate);
        tv3.setText(tv3.getText()+" "+getIntent().getStringExtra("tskills"));
        tv4.setText(tv4.getText()+" "+getIntent().getStringExtra("timing"));
        tid=getIntent().getStringExtra("tid");
        tv5.setText(tv5.getText()+" "+getIntent().getStringExtra("tmode"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senreq();
                Toast.makeText(tutordetails.this, "Request Sent", Toast.LENGTH_SHORT).show();
              //  startActivity(new Intent(tutordetails.this,findtutors.class));
                //finish();


    }
});
        refr1=storageReference.child("tutorsandstudents/"+tid+"/profile");
        refr1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);

            }
        });



        DocumentReference documentReference1=fstore.collection("users").document(currentuser);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                stname=value.getString("stname");
                stage=value.getString("stage");
                stcity=value.getString("stcity");
                stpin=value.getString("stpin");
                stclass=value.getString("stclass");
                stmobile=value.getString("stmobile");
                stmode=value.getString("stmode");
                stmail=value.getString("stmail");
                ststate=value.getString("ststate");

            }
        });



    }
    public void senreq()
    {
        DocumentReference documentReference2=fstore.collection("users").document(tid).collection("tutorreq").document(currentuser);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Map<String,Object> map =new HashMap<>();
                map.put("stname",stname);
                map.put("stage",stage);
                map.put("stpin",stpin);
                map.put("stclass",stclass);
                map.put("stmode",stmode);
                map.put("stmobile",stmobile);
                map.put("stmail",stmail);
                map.put("stcity",stcity);
                map.put("ststate",ststate);
                map.put("sid",currentuser);
                documentReference2.set(map);

            }
        });
    }
}