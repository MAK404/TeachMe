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

import java.util.HashMap;
import java.util.Map;

public class registerstudent extends AppCompatActivity {
    String tutorname1,tutorage1,tutormobile1,tutormail1,tutorgender1,tutormode1;
    String thome1,tstreet1,tcity1,tstate1,tpin1,pwd1;
    String finaladdress,personaldetails,gm,classtudent,type1="0",type2=null;

    TextView tv,tv2,tv3,tv4,tv5;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String studentID;
    Button Register,goback;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerstudent);
        fauth= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        tv = findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        goback=findViewById(R.id.btnback);
        progressBar=findViewById(R.id.progressBar);
        Register=findViewById(R.id.btnRegisterst);

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
        classtudent=getIntent().getStringExtra("tedu33");

        finaladdress=String.format("%s, %s, %s, %s, %s",thome1,tstreet1,tcity1,tpin1,tstate1);
        personaldetails = String.format("%s, %s, %s, %s, %s, %s",tutorname1,tutormobile1,tutorgender1,tutorage1,tutormail1,pwd1);
        gm=String.format("%s",tutormode1);
        tv.setText(personaldetails);
        tv2.setText(finaladdress);
        tv3.setText(classtudent);
        tv4.setText(gm);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(tutormail1,pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            studentID=fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(studentID);
                                Map<String,Object> student= new HashMap<>();
                            student.put("studenttype",type2);
                            student.put("studentapproved",type1);
                            student.put("stname",tutorname1);
                            student.put("stmobile",tutormobile1);
                            student.put("stmail",tutormail1);
                            student.put("stgender",tutorgender1);
                            student.put("stage",tutorage1);
                            student.put("stcity",tcity1);
                            student.put("stpin",tpin1);
                            student.put("ststate",tstate1);
                            student.put("staddress",finaladdress);
                            student.put("stclass",classtudent);
                            student.put("stmode",tutormode1);
                            student.put("sid",studentID);

                            documentReference.set(student);
                            Intent i =new Intent(getApplicationContext(),studentupload.class);
                            Bundle bundle =new Bundle();
                            bundle.putString("stid3",studentID);
                            Toast.makeText(getApplicationContext(),"Student Registered Successfully", Toast.LENGTH_LONG).show();
                            i.putExtras(bundle);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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