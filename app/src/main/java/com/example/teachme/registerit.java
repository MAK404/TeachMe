package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class registerit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
 Button btn;
    String tutorgender="",tutormode="";
    EditText tname12,tmobile12,tage12,tmail12,thome12,tstreet122,tcity12,tstate12,tpin12,pass;
    ArrayList<String> skills = new ArrayList<String>();
    ArrayList<String> timing = new ArrayList<String>();

    CheckBox s1,s2,s3,s4,s5,s6,s7,s8,ti1,ti2,ti3;

    Spinner spinner1,spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        skills.clear();
        timing.clear();

        btn=(Button)findViewById(R.id.btnrequest);
        pass=findViewById(R.id.pwd);
        tname12=findViewById(R.id.tname);
        tmobile12=findViewById(R.id.tmobile);
        tage12=findViewById(R.id.tage);
        tmail12=findViewById(R.id.tmail);
        tmail12=findViewById(R.id.tmail);
        thome12=findViewById(R.id.thome);
        tstreet122=findViewById(R.id.tstreete);
        tcity12=findViewById(R.id.tcity);
        tstate12=findViewById(R.id.tstate);
        tpin12=findViewById(R.id.tpin);

        spinner1=findViewById(R.id.static_spinner);
        spinner2=findViewById(R.id.static_spinner2);

        s1=findViewById(R.id.sk1);
        s2=findViewById(R.id.sk2);
        s3=findViewById(R.id.sk3);
        s4=findViewById(R.id.sk4);
        s5=findViewById(R.id.sk5);
        s6=findViewById(R.id.sk6);
        s7=findViewById(R.id.sk7);
        s8=findViewById(R.id.sk8);
        ti1=findViewById(R.id.t1);
        ti2=findViewById(R.id.t2);
        ti3=findViewById(R.id.t3);





        //spinner1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.method, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


    }
    public void onClick(View v)
    {
       if( OK()) {
           Intent i = new Intent(getApplicationContext(), registertutor.class);
           Bundle bundle = new Bundle();
           bundle.putString("tname3", tname12.getText().toString());
           bundle.putString("tmobile3", tmobile12.getText().toString());
           bundle.putString("tage3", tage12.getText().toString());
           bundle.putString("tmail3", tmail12.getText().toString());
           bundle.putString("pwd3",pass.getText().toString());
           bundle.putString("thome3", thome12.getText().toString());
           bundle.putString("street33", tstreet122.getText().toString());
           bundle.putString("tcity3", tcity12.getText().toString());
           bundle.putString("tpin3", tpin12.getText().toString());
           bundle.putString("tstate3", tstate12.getText().toString());
           bundle.putStringArrayList("tskills3", skills);
           bundle.putStringArrayList("timings3", timing);
           bundle.putString("tgender3", tutorgender);
           bundle.putString("tmode3", tutormode);

           i.putExtras(bundle);
           startActivity(i);
       }

    }

    public void oncheckboxskillclicked(View view)
    {
      if(s1.isChecked()&& skills.contains("Maths")==false)
          skills.add("Maths");

        if(s2.isChecked()&& skills.contains("Science")==false)
            skills.add("Science");

        if(s3.isChecked()&&skills.contains("Computer Science")==false)
            skills.add("Computer Science");
        if(s4.isChecked()&&skills.contains("Humanities")==false)
            skills.add("Humanities");
        if(s5.isChecked()&&skills.contains("EVS")==false)
            skills.add("EVS");
        if(s6.isChecked()&&skills.contains("Music")==false)
            skills.add("Music");
        if(s7.isChecked()&&skills.contains("Guitar")==false)
            skills.add("Guitar");
        if(s8.isChecked()&&skills.contains("Singing")==false)
            skills.add("Singing");
        if(s1.isChecked()==false && skills.contains("Maths"))
            skills.remove("Maths");

        if(s2.isChecked()==false&& skills.contains("Science"))
            skills.remove("Science");

        if(s3.isChecked()==false&&skills.contains("Computer Science"))
            skills.remove("Computer Science");
        if(s4.isChecked()==false&&skills.contains("Humanities"))
            skills.remove("Humanities");
        if(s5.isChecked()==false&&skills.contains("EVS"))
            skills.remove("EVS");
        if(s6.isChecked()==false&&skills.contains("Music"))
            skills.remove("Music");
        if(s7.isChecked()==false&&skills.contains("Guitar"))
            skills.remove("Guitar");
        if(s8.isChecked()==false&&skills.contains("Singing"))
            skills.remove("Singing");


    }
    public void oncheckboxtimingsclicked(View view)
    {
        if(ti1.isChecked()&&timing.contains("Morning 6am-12pm")==false)
            timing.add("Morning 6am-12pm");
        if(ti2.isChecked()&&timing.contains("Noon 12pm-6pm")==false)
            timing.add("Noon 12pm-6pm");
        if(ti3.isChecked()&&timing.contains("Night 6pm-12am")==false)
            timing.add("Night 6pm-12am");

        if(ti1.isChecked()==false&&timing.contains("Morning 6am-12pm"))
            timing.remove("Morning 6am-12pm");
        if(ti2.isChecked()==false&&timing.contains("Noon 12pm-6pm"))
            timing.remove("Noon 12pm-6pm");
        if(ti3.isChecked()==false&&timing.contains("Night 6pm-12am"))
            timing.remove("Night 6pm-12am");

    }
    public boolean OK() {
        if (s1.isChecked() == false && s2.isChecked() == false && s3.isChecked() == false && s4.isChecked() == false && s5.isChecked() == false && s6.isChecked() == false && s7.isChecked() == false && s8.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Please Select Any of the Skills you have", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ti2.isChecked() == false && ti3.isChecked() == false && ti1.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Please Select Timing", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tpin12.getText().toString()) || TextUtils.isEmpty(tstate12.getText().toString())|| TextUtils.isEmpty(tstate12.getText().toString())||TextUtils.isEmpty(tcity12.getText().toString())||TextUtils.isEmpty(thome12.getText().toString())||TextUtils.isEmpty(tstreet122.getText().toString())||TextUtils.isEmpty(tname12.getText().toString())||TextUtils.isEmpty(tmobile12.getText().toString())||TextUtils.isEmpty(tage12.getText().toString())||TextUtils.isEmpty(tmail12.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please Fill the form Completely", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Please Check Your Password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().length()<6)
        {
            Toast.makeText(getApplicationContext(),"Please Check Your Password Length > 6",Toast.LENGTH_SHORT).show();
            return false;
        }
     else
         return true;
    }
     //||
//


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.static_spinner) {
            String finalgender = adapterView.getItemAtPosition(i).toString();
            tutorgender=finalgender;

            //Toast.makeText(getApplicationContext(), tutorgender, Toast.LENGTH_LONG).show();
        }
        else if(spinner.getId() == R.id.static_spinner2)
        {
            String finalmethod = adapterView.getItemAtPosition(i).toString();
            tutormode=finalmethod;

           // Toast.makeText(getApplicationContext(), tutormode, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}