package com.example.teachme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class registertutor extends AppCompatActivity {
    String tutorname1,tutorage1,tutormobile1,tutormail1,tutorgender1,tutormode1;
    String thome1,tstreet1,tcity1,tstate1,tpin1,pwd1;
    String finaladdress,personaldetails,gm;
    String tutorID,type1="0",type2=null;
    double Rating=5;

    ArrayList<String> skills1 = new ArrayList<String>();
    ArrayList<String> timing1 = new ArrayList<String>();
    TextView tv,tv2,tv3,tv4,tv5;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    Button Register,goback;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registertutor);
   //  Bundle bundle= getIntent().getExtras();

        tutorname1=getIntent().getStringExtra("tname3");
        tutormobile1=getIntent().getStringExtra("tmobile3");
        tutorage1=getIntent().getStringExtra("tage3");
        tutormail1=getIntent().getStringExtra("tmail3");
        pwd1=getIntent().getStringExtra("pwd3");
        thome1=getIntent().getStringExtra("thome3");
        tstreet1=getIntent().getStringExtra("street33");
        tcity1=getIntent().getStringExtra("tcity3");
        tpin1=getIntent().getStringExtra("tpin3");
        tstate1=getIntent().getStringExtra("tstate3");
        tutorgender1=getIntent().getStringExtra("tgender3");
       tutormode1=getIntent().getStringExtra("tmode3");
       skills1=getIntent().getStringArrayListExtra("tskills3");
        timing1=getIntent().getStringArrayListExtra("timings3");
        finaladdress=String.format("%s, %s, %s, %s, %s",thome1,tstreet1,tcity1,tpin1,tstate1);
         personaldetails = String.format("%s, %s, %s, %s, %s, %s",tutorname1,tutormobile1,tutorgender1,tutorage1,tutormail1,pwd1);
        gm=String.format("%s",tutormode1);
        tv = findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        goback=findViewById(R.id.btnback);
        progressBar=findViewById(R.id.progressBar);
        Register=findViewById(R.id.btnrequest);
      tv3.setText(skills1.toString());
      tv4.setText(timing1.toString());
      tv2.setText(finaladdress);
      tv.setText(personaldetails);
      tv5.setText(gm);

        fauth= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
//        if(fauth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(),tutorAsUser.class));
//            finish();
//        }
//

//        tv.setText(tutorname1+" "+tutormobile1+" "+tutorage1+" "+tutormail1);
//        tv2.setText(finaladdress+" "+tutorgender1+tutormode1);
//        tv.setText(skills1.get(0)+" "+skills1.get(1)+" "+skills1.get(2));
//        tv2.setText(timing1.get(0)+" "+timing1.get(1)+" "+ timing1.get(2));

 Register.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         progressBar.setVisibility(View.VISIBLE);
         fauth.createUserWithEmailAndPassword(tutormail1,pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {
                     tutorID=fauth.getCurrentUser().getUid();
                     DocumentReference documentReference =  fstore.collection("users").document(tutorID);
                     Map<String,Object> tutor=new HashMap<>();
                     tutor.put("tutortype",type2);
                     tutor.put("tutorapproved",type1);
                     tutor.put("tname",tutorname1);
                     tutor.put("tmobile",tutormobile1);
                     tutor.put("tmail",tutormail1);
                     tutor.put("tgender",tutorgender1);
                     tutor.put("tage",tutorage1);
                     tutor.put("tcity",tcity1);
                     tutor.put("tpin",tpin1);
                     tutor.put("tstate",tstate1);
                     tutor.put("taddress",finaladdress);
                     tutor.put("tmode",tutormode1);
                     tutor.put("tskills",skills1.toString());
                     tutor.put("timing",timing1.toString());
                     tutor.put("tid",tutorID);
                     tutor.put("trating",Rating);
                     documentReference.set(tutor);
                     Intent i = new Intent(getApplicationContext(), tutorUpload.class);
                     Bundle bundle = new Bundle();
                     bundle.putString("tutorID",tutorID);
                     Toast.makeText(getApplicationContext(),"Tutor Registered Successfully", Toast.LENGTH_LONG).show();
                     progressBar.setVisibility(View.INVISIBLE);
                     i.putExtras(bundle);
                     startActivity(i);

                 }
                 else
                 {
                     Toast.makeText(registertutor.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     progressBar.setVisibility(View.INVISIBLE);
                 }
             }
         });
     }
 });

goback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});


    }

}