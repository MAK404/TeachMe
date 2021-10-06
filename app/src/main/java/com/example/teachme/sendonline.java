package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class sendonline extends AppCompatActivity {
TextView wno,em,mid,mno,address,land,schedule;
String sid,tid,tmode,tname,tmail;
Button send;
FirebaseAuth fauth;
FirebaseFirestore fstore;
boolean flag1=false,flag2=false,flag3=false;
Map<String,Object> map1,map2,map3;
DocumentReference ref123;
String sname,smail,smobile,scity;
//ProgressDialog pd = new ProgressDialog(sendonline.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendonline);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        wno=findViewById(R.id.wn);
        em=findViewById(R.id.em);
        mid=findViewById(R.id.mid);
        mno=findViewById(R.id.mno);
        address=findViewById(R.id.add);
        land=findViewById(R.id.land);
        schedule=findViewById(R.id.schedule);

        send=findViewById(R.id.send);
       // pd.setMessage("loading");

       // tmode=getIntent().getStringExtra("tmode");
        sid=getIntent().getStringExtra("sid");
        tid=fauth.getCurrentUser().getUid();
        tname=getIntent().getStringExtra("tname");
        tmail=getIntent().getStringExtra("tmail");

        DocumentReference refst = fstore.collection("users").document(sid);
        refst.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sname=value.getString("stname");
                smail=value.getString("stmail");
                smobile=value.getString("stmobile");
                scity=value.getString("stcity");
            }
        });

        DocumentReference documentReference2 = fstore.collection("users").document(tid);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("tmode").contentEquals("Online")) {
                    address.setEnabled(false);
                    land.setEnabled(false);
                    mno.setEnabled(false);
                    flag1=true;
                    flag2=false;
                    flag3=false;
                }
                else if(value.getString("tmode").contentEquals("Offline"))
                    {
                        wno.setEnabled(false);
                        mid.setEnabled(false);
                        em.setEnabled(false);
                       flag2=true;
                       flag1=false;
                       flag3=false;
                    }
                else if(value.getString("tmode").contentEquals("Both"))
                {

                    flag3=true;
                    flag1=false;
                    flag2=false;
                }


            }
        });

        ref123=fstore.collection("users").document(tid).collection("yesbyme").document(sid);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1)
                   update();
                else if(flag2)
                    update2();
                else if(flag3)
                    update3();
//                pd.show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        finish();
//                    }
//                }, 5000);   //5 seconds
               // Toast.makeText(sendonline.this, "Details Send Successfully Either Go Back or Change to Overwrite", Toast.LENGTH_LONG).show();

            }
        });



    }

    public void updatetofirebase1()
    {
        DocumentReference documentReference = fstore.collection("users").document(sid).collection("accepted").document(tid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                map1 =new HashMap<>();
                map1.put("tname",tname);
                map1.put("tmail",tmail);
                map1.put("twno",wno.getText().toString());
                map1.put("tem",em.getText().toString());
                map1.put("tmid",mid.getText().toString());
                map1.put("tschedule",schedule.getText().toString());
                map1.put("tmno",null);
                map1.put("taddress",null);
                map1.put("tland",null);
                map1.put("tid",tid);
                map1.put("tmode","Online");
                map1.put("sid",sid);
                map1.put("smail",smail);
                map1.put("sname",sname);
                map1.put("smobile",smobile);
                map1.put("scity",scity);
                map1.put("israted","0");

                documentReference.set(map1);
                Toast.makeText(sendonline.this, "Details Send Successfully Either Go Back or Change to Overwrite", Toast.LENGTH_SHORT).show();

            }
        });

        deletefromtutor();

        ref123.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               ref123.set(map1);
            }
        });
    }

    public void updatetofirebase2()
    {
        DocumentReference documentReference = fstore.collection("users").document(sid).collection("accepted").document(tid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                map2 =new HashMap<>();
                map2.put("tname",tname);
                map2.put("tmail",tmail);
                map2.put("twno",null);
                map2.put("tem",null);
                map2.put("tmid",null);
                map2.put("tschedule",schedule.getText().toString());
                map2.put("mno",mno.getText().toString());
                map2.put("taddress",address.getText().toString());
                map2.put("tland",land.getText().toString());
                map2.put("tid",tid);
                map2.put("tmode","Offline");
                map2.put("sid",sid);
                map2.put("smail",smail);
                map2.put("sname",sname);
                map2.put("smobile",smobile);
                map2.put("scity",scity);
                map2.put("israted","0");


                documentReference.set(map2);
                Toast.makeText(sendonline.this, "Details Send Successfully Either Go Back or Change to Overwrite", Toast.LENGTH_SHORT).show();

            }
        });

        deletefromtutor();
        ref123.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ref123.set(map2);
            }
        });
    }
    public void updatetofirebase3()
    {
        DocumentReference documentReference = fstore.collection("users").document(sid).collection("accepted").document(tid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 map3 =new HashMap<>();
                map3.put("tname",tname);
                map3.put("tmail",tmail);
                map3.put("twno",wno.getText().toString());
                map3.put("tem",em.getText().toString());
                map3.put("tmid",mid.getText().toString());
                map3.put("tschedule",schedule.getText().toString());
                map3.put("mno",mno.getText().toString());
                map3.put("taddress",address.getText().toString());
                map3.put("tland",land.getText().toString());
                map3.put("tid",tid);
                map3.put("tmode","Both");
                map3.put("sid",sid);
                map3.put("smail",smail);
                map3.put("sname",sname);
                map3.put("smobile",smobile);
                map3.put("scity",scity);
                map3.put("israted","0");

                documentReference.set(map3);
                Toast.makeText(sendonline.this, "Details Send Successfully Either Go Back or Change to Overwrite", Toast.LENGTH_SHORT).show();

            }
        });
        deletefromtutor();
        ref123.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ref123.set(map3);
            }
        });
    }

    public void update()
    {
        if (OKOnline()) {
            updatetofirebase1();
        }
    }
    public void update2()
    {
        if (OKOffline()) {
            updatetofirebase2();
        }
    }
    public void update3()
    {
        if (OKBoth()) {
            updatetofirebase3();
        }
    }

    private boolean OKBoth( ) {
        if (TextUtils.isEmpty(mno.getText().toString()) || TextUtils.isEmpty(address.getText().toString())
                || TextUtils.isEmpty(land.getText().toString()) || TextUtils.isEmpty(schedule.getText().toString())||
                TextUtils.isEmpty(wno.getText().toString()) || TextUtils.isEmpty(em.getText().toString()) ||
                        TextUtils.isEmpty(mid.getText().toString()))
        {
            Toast.makeText(this, "Please Fill Complete Details", Toast.LENGTH_SHORT).show();
            return false;
        }

         else
             return true;
    }


    private boolean OKOnline() {
        if(TextUtils.isEmpty(wno.getText().toString()) ||TextUtils.isEmpty(em.getText().toString()) || TextUtils.isEmpty(mid.getText().toString()) || TextUtils.isEmpty(schedule.getText().toString())  )
        {
            Toast.makeText(this, "Please Fill Complete Online Details with Schedule", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
    public boolean OKOffline()
    {
        if(TextUtils.isEmpty(mno.getText().toString()) ||TextUtils.isEmpty(address.getText().toString()) || TextUtils.isEmpty(land.getText().toString()) || TextUtils.isEmpty(schedule.getText().toString())  )
        {
            Toast.makeText(this, "Please Fill Complete Offline Details with Schedule", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public void deletefromtutor()
    {
        fstore.collection("users").document(tid).collection("tutorreq").document(sid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        flag1=false;
        flag2=false;
        flag3=false;

    }
}