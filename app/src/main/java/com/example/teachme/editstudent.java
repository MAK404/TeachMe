package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class editstudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   FirebaseAuth fauth;
   FirebaseFirestore fstore;
    Button btn;
    DocumentReference ref1;
    String stgender="",stmode="",steducation12="";
    EditText stname12,stmobile12,stage12,staddress12,stcity12,ststate12,stpin12;
    Spinner stspinner1,stspinner2,stspinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editstudent);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        btn=(Button)findViewById(R.id.btnRegisterStudent);

        stname12=findViewById(R.id.stname);
        stmobile12=findViewById(R.id.stmobile);
        stage12=findViewById(R.id.stage);
        stcity12=findViewById(R.id.stcity);
        ststate12=findViewById(R.id.ststate);
        stpin12=findViewById(R.id.stpin);
        staddress12=findViewById(R.id.staddress);

        stspinner1=findViewById(R.id.student_spinner);
        stspinner2=findViewById(R.id.student_spinner2);
        stspinner3=findViewById(R.id.student_spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stspinner1.setAdapter(adapter);
        stspinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.method, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stspinner2.setAdapter(adapter2);
        stspinner2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.classedu, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stspinner3.setAdapter(adapter3);
        stspinner3.setOnItemSelectedListener(this);

       ref1= fstore.collection("users").document(fauth.getCurrentUser().getUid());
        ref1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                stname12.setText(value.getString("stname"));
                stmobile12.setText(value.getString("stmobile"));
                stage12.setText(value.getString("stage"));
                stcity12.setText(value.getString("stcity"));
                stpin12.setText(value.getString("stpin"));
                ststate12.setText(value.getString("ststate"));
                staddress12.setText(value.getString("staddress"));
            }
        });
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        updateit();
    }
});
    }

    private void updateit() {

        Map<String,Object> student= new HashMap<>();
        student.put("stname",stname12.getText().toString());
        student.put("stage",stage12.getText().toString());
        student.put("stmobile",stmobile12.getText().toString());
        student.put("stcity",stcity12.getText().toString());
        student.put("ststate",ststate12.getText().toString());
        student.put("staddress",staddress12.getText().toString());
        student.put("stpin",stpin12.getText().toString());
        student.put("stgender",stgender);
        student.put("stmode",stmode);
        student.put("stclass",steducation12);
        ref1.update(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(editstudent.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.student_spinner) {
            String finalgender = adapterView.getItemAtPosition(i).toString();
            stgender=finalgender;

            //Toast.makeText(getApplicationContext(), stgender, Toast.LENGTH_LONG).show();
        }
        else if(spinner.getId() == R.id.student_spinner2)
        {
            String finalmethod = adapterView.getItemAtPosition(i).toString();
            stmode=finalmethod;

            //Toast.makeText(getApplicationContext(), stmode, Toast.LENGTH_LONG).show();
        }
        else
        {
            String finalclass12 = adapterView.getItemAtPosition(i).toString();
            steducation12=finalclass12;
            //Toast.makeText(getApplicationContext(), steducation12, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean OK()
    {
        if (TextUtils.isEmpty(stpin12.getText().toString()) || TextUtils.isEmpty(ststate12.getText().toString())||TextUtils.isEmpty(stcity12.getText().toString())||TextUtils.isEmpty(stname12.getText().toString())||TextUtils.isEmpty(stmobile12.getText().toString())||TextUtils.isEmpty(stage12.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please Fill the form Completely", Toast.LENGTH_SHORT).show();
            return false;
        }

        else
            return true;
    }
}