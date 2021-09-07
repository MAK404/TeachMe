package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registeris extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btn;
    String stgender="",stmode="",steducation12="";
    EditText stname12,stmobile12,stage12,stmail12,sthome12,ststreet122,stcity12,ststate12,stpin12,stpass;
    Spinner  stspinner1,stspinner2,stspinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeris);


        btn=(Button)findViewById(R.id.btnRegisterStudent);
        stpass=findViewById(R.id.spwd);
        stname12=findViewById(R.id.stname);
        stmobile12=findViewById(R.id.stmobile);
        stage12=findViewById(R.id.stage);
        stmail12=findViewById(R.id.stmail);
        sthome12=findViewById(R.id.sthome);
        ststreet122=findViewById(R.id.ststreete);
        stcity12=findViewById(R.id.stcity);
        ststate12=findViewById(R.id.ststate);
        stpin12=findViewById(R.id.stpin);

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
    public void onClickst(View view)
    {
        if( OK()) {
            Intent i = new Intent(getApplicationContext(), registerstudent.class);
            Bundle bundle = new Bundle();
            bundle.putString("tname3", stname12.getText().toString());
            bundle.putString("tmobile3", stmobile12.getText().toString());
            bundle.putString("tage3", stage12.getText().toString());
            bundle.putString("tmail3", stmail12.getText().toString());
            bundle.putString("pwd3",stpass.getText().toString());
            bundle.putString("thome3", sthome12.getText().toString());
            bundle.putString("street33", ststreet122.getText().toString());
            bundle.putString("tcity3", stcity12.getText().toString());
            bundle.putString("tpin3", stpin12.getText().toString());
            bundle.putString("tstate3", ststate12.getText().toString());
            bundle.putString("tgender3", stgender);
            bundle.putString("tmode3", stmode);
            bundle.putString("tedu33", steducation12);

            i.putExtras(bundle);
            startActivity(i);
        }
    }
    public boolean OK()
    {
        if (TextUtils.isEmpty(stpin12.getText().toString()) || TextUtils.isEmpty(ststate12.getText().toString())|| TextUtils.isEmpty(ststate12.getText().toString())||TextUtils.isEmpty(stcity12.getText().toString())||TextUtils.isEmpty(sthome12.getText().toString())||TextUtils.isEmpty(ststreet122.getText().toString())||TextUtils.isEmpty(stname12.getText().toString())||TextUtils.isEmpty(stmobile12.getText().toString())||TextUtils.isEmpty(stage12.getText().toString())||TextUtils.isEmpty(stmail12.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please Fill the form Completely", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(stpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Please Check Your Password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(stpass.getText().length()<6)
        {
            Toast.makeText(getApplicationContext(),"Please Check Your Password Length > 6",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

}