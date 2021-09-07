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
import com.google.firebase.storage.FirebaseStorage;

public class studentprofile extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    DocumentReference documentReference;
    ListenerRegistration abc;
    String sID;
    ImageView refresh;
    TextView sname,sage,sgender,smode,sclassedu,scity,sstate,saddress;
    Button changeit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);
        sname=findViewById(R.id.sname);
        sage=findViewById(R.id.sage);
        sgender=findViewById(R.id.sgender);
        smode=findViewById(R.id.smode);
        sclassedu=findViewById(R.id.sclass);
        scity=findViewById(R.id.scity);
        sstate=findViewById(R.id.sstate);
        changeit=findViewById(R.id.editdetails);
        saddress=findViewById(R.id.saddress);
        refresh=findViewById(R.id.imageView4);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        sID = fauth.getCurrentUser().getUid();
        documentReference = fstore.collection("users").document(sID);

         refreshit();

changeit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(studentprofile.this,editstudent.class));
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
        sage.setText("");
        sgender.setText("");
        scity.setText("");
        sstate.setText("");
        sclassedu.setText("");
        smode.setText("");
        saddress.setText("");

        abc = documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sname.setText("Name: " + " " + value.getString("stname"));
                sage.setText("Age: " + " " + value.getString("stage"));
                sgender.setText("Gender: "+ " " + value.getString("stgender"));
                scity.setText("City: " + " " + value.getString("stcity"));
                sstate.setText("State: "+ " " + value.getString("ststate"));
                sclassedu.setText("Class: " + " " + value.getString("stclass"));
                smode.setText("Mode: " + " " + value.getString("stmode"));
                saddress.setText("Address: "+" "+value.getString("staddress"));

            }
        });
    }
}