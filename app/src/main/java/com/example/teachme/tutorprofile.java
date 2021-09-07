package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class tutorprofile extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    DocumentReference documentReference;
    ListenerRegistration abc;
    String sID;
    ImageView refresh;
    TextView sname,sagegender,smode,skills,scity,sstate,timing,pin;
    Button changeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorprofile);

        sname=findViewById(R.id.sname);
        sagegender=findViewById(R.id.sagegender);
        smode=findViewById(R.id.smode);
        pin=findViewById(R.id.sclass);
        scity=findViewById(R.id.scity);
        sstate=findViewById(R.id.sstate);
        skills=findViewById(R.id.skills);
        timing=findViewById(R.id.timing);
        changeit=findViewById(R.id.editdetails);
        refresh=findViewById(R.id.imageView4);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        sID = fauth.getCurrentUser().getUid();
        documentReference = fstore.collection("users").document(sID);
        refreshit();

        changeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tutorprofile.this,edittutor.class));
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshit();
            }
        });
    }
    private void refreshit() {
        sname.setText("");
        sagegender.setText("");
        pin.setText("");
        smode.setText("");
        skills.setText("");
        timing.setText("");

        abc = documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sname.setText("Name: " + " " + value.getString("tname"));
                sagegender.setText("Age/Gender: " + " " + value.getString("tage")+"/"+value.getString("tgender"));
                pin.setText("Pin/City/State: "+ " " + value.getString("tpin")+"/"+value.getString("tcity")+"/"+value.getString("tstate"));
                smode.setText("Mode: " + " " + value.getString("tmode"));
                skills.setText("Skills: " + " " + value.getString("tskills"));
                timing.setText("Timing: "+" "+value.getString("timing"));

            }
        });
    }
}