package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class accepteddetails extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String taddress;
    String tem;
    String tmno2;
    String tid;
    String tland;
    String tmail;
    String tmid;
    String tname;
    String tschedule;
    String twno;
    String tmode="";
    String nan="Tutor Not Avalaible";
    String sid;
    TextView wno,mid,em1,address,land,mno2,schedule;
    Button btnrating;
    DocumentReference documentReference2, documentReference3;
    RatingBar ratingBar;
    double finalrating,initialrating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepteddetails);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        wno=findViewById(R.id.wn);
        em1=findViewById(R.id.em);
        mid=findViewById(R.id.mid);
        address=findViewById(R.id.add);
        land=findViewById(R.id.land);
        mno2=findViewById(R.id.mno2);
        schedule=findViewById(R.id.schedule);
        taddress=getIntent().getStringExtra("taddress");
        tem=getIntent().getStringExtra("tem");
         tid=getIntent().getStringExtra("tid");
       // tid="https://www.google.com/";
        tland=getIntent().getStringExtra("tland");
        tmid=getIntent().getStringExtra("tmid");

        //tmid="https://www.google.com/";
        tname=getIntent().getStringExtra("tname");
        tmno2=getIntent().getStringExtra("tmno");
        tschedule=getIntent().getStringExtra("tschedule");
        twno=getIntent().getStringExtra("twno");
        tmode=getIntent().getStringExtra("tmode");
        btnrating=findViewById(R.id.btnrating);
        ratingBar=findViewById(R.id.ratingBar);
        sid=fauth.getCurrentUser().getUid();
        documentReference3 =fstore.collection("users").document(sid).collection("accepted").document(tid);
        documentReference3.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.getString("israted").contentEquals("1"))
                {
                    ratingBar.setVisibility(View.INVISIBLE);
                    btnrating.setVisibility(View.INVISIBLE);
                }
            }
        });
        documentReference2 = fstore.collection("users").document(tid);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("tmode").contentEquals("Online")) {

                    if(tmid.charAt(0)!='h')
                    {
                        tmid="https://"+tmid;
                    }

                    wno.setText(wno.getText()+twno);
                    mid.setText(mid.getText()+tmid);
                    em1.setText(em1.getText()+tem);

                    schedule.setText(schedule.getText()+tschedule);
                    mno2.setText(nan);
                    land.setText(nan);
                    address.setText(nan);

                }
                else if(value.getString("tmode").contentEquals("Offline"))
                {
                    schedule.setText(schedule.getText()+tschedule);
                    mno2.setText(mno2.getText()+tmno2);
                    land.setText(land.getText()+tland);
                    address.setText(address.getText()+taddress);

                    wno.setText(nan);
                    mid.setText(nan);
                    em1.setText(nan);
                    mid.setEnabled(false);
                }
                else if(value.getString("tmode").contentEquals("Both"))
                {
                    if(tmid.charAt(0)!='h')
                    {
                        tmid="https://"+tmid;
                    }

                    wno.setText(wno.getText()+twno);
                    mid.setText(mid.getText()+tmid);
                    em1.setText(em1.getText()+tem);
                    schedule.setText(schedule.getText()+tschedule);
                    mno2.setText(mno2.getText()+tmno2);
                    land.setText(land.getText()+tland);
                    address.setText(address.getText()+taddress);
                }

            }
        });
btnrating.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finalrating=ratingBar.getRating();
        findaandupdate();
        documentReference3.update("israted","1").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(accepteddetails.this, "Tutor Rated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        Intent i = new Intent(accepteddetails.this, acceptedreq.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }
});
mid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gotoUrl(tmid);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
});
    }

    private void findaandupdate() {
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                initialrating=value.getDouble("trating");
                finalrating=(initialrating+finalrating)/2;
                finalrating=Math.round(finalrating*100.0)/100.0;
                documentReference2.update("trating",finalrating);
            }
        });
    }
}