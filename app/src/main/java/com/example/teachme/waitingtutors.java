package com.example.teachme;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class waitingtutors extends AppCompatActivity {
    TextView tv0,tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    Button btn,btnid;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    Button btn2;
    FirebaseUser fuser;
    StorageReference storageReference;
    String sid;
    String tmode;
    Uri iduri;
    StorageReference refr1,refr2;
    ImageView img;
    DocumentReference ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingtutors);
        tv0=findViewById(R.id.tv0);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        btn=findViewById(R.id.btnapprove);
        btn2=findViewById(R.id.btndelete);
        btnid=findViewById(R.id.btnid);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        img=findViewById(R.id.mainprofile);

        sid=getIntent().getStringExtra("tid");
        storageReference= FirebaseStorage.getInstance().getReference();
        ref1=fstore.collection("users").document(sid);

        tv0.setText(tv0.getText()+" "+getIntent().getStringExtra("tname"));
        tv1.setText(tv1.getText()+" "+String.format("%s/%s",getIntent().getStringExtra("tage"),getIntent().getStringExtra("tgender")));
        String citystatepin=String.format("%s/%s/%s",getIntent().getStringExtra("tpin"),getIntent().getStringExtra("tcity")
                ,getIntent().getStringExtra("tstate"));
        tv2.setText(tv2.getText()+" "+citystatepin);
        tv3.setText(tv3.getText()+" "+String.format("%s/%s",getIntent().getStringExtra("tmail"),getIntent().getStringExtra("tmobile")));
        tv4.setText(tv4.getText()+" "+getIntent().getStringExtra("tmode"));
        tv5.setText(tv5.getText()+" "+getIntent().getStringExtra("tskills"));
        tv6.setText(tv6.getText()+" "+getIntent().getStringExtra("timing"));
        tv7.setText(tv7.getText()+" "+getIntent().getStringExtra("taddress"));

        refr1=storageReference.child("tutorsandstudents/"+sid+"/profile");
        refr2=storageReference.child("tutorsandstudents/"+sid+"/idproof");

        refr1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);

            }
        });
  btnid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(waitingtutors.this, tutoridproof.class);
        intent.putExtra("tid",sid);
        startActivity(intent);
    }
});

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveuser();
                Intent i = new Intent(waitingtutors.this, showstudents.class);
// set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteuser();
                Intent i = new Intent(waitingtutors.this, showstudents.class);
// set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    private void approveuser() {

        Map<String,Object> studentap= new HashMap<>();
        studentap.put("tutorapproved","1");
        studentap.put("tutortype","1");
        ref1.update(studentap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(waitingtutors.this, "User Approved Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void deleteuser(){

        Map<String,Object> studentagn= new HashMap<>();
        studentagn.put("accountdeleted","1");
        ref1.set(studentagn).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(waitingtutors.this, "User has been Deleted!!", Toast.LENGTH_LONG).show();
            }
        });
        deletesomestuff();

    }

    private void deletesomestuff() {
        refr1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        refr2.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        })    ;
    }
}