package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
  TabLayout tabLayout;
  ViewPager2 pager2;
  FragmentAdaptor adaptor;
  Button btnTutor;
    ProgressDialog dialog;

   // ProgressDialog progressdialog = new ProgressDialog(getApplicationContext());

  //ProgressBar pbar;
  TextView txt;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    FirebaseUser ifuser = FirebaseAuth.getInstance().getCurrentUser();
    private int[] tabIcons ={
            R.drawable.lock,
            R.drawable.register
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // pbar=findViewById(R.id.progressBar);
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);
        btnTutor = findViewById(R.id.btnTutor);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please wait...");
      //  progressdialog.setMessage("Please Wait....");
      //  txt = findViewById(R.id.textofsignup);


        FragmentManager fm = getSupportFragmentManager();
        adaptor = new FragmentAdaptor(fm, getLifecycle());
        pager2.setAdapter(adaptor);

        tabLayout.addTab(tabLayout.newTab().setText("LOGIN"));
        tabLayout.addTab(tabLayout.newTab().setText("SIGNUP"));
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(ifuser!=null)
        {

            String fuser=ifuser.getUid();
            dialog.show();
         //   progressdialog.show();

            DocumentReference df =fstore.collection("users").document(fuser);
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("tutortype")!=null)
                    {
                        Intent i = new Intent(MainActivity.this, tutorafterlogin.class);
                        //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        dialog.dismiss();

                    }
                    if(documentSnapshot.getString("studenttype")!=null)
                    {
                        Intent i = new Intent(MainActivity.this, studentafterlogin.class);
                        //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        dialog.dismiss();
                    }
                    if(documentSnapshot.getString("admin")!=null)
                    {

                        startActivity(new Intent(MainActivity.this,adminwindow.class));
                        dialog.dismiss();
                    }
                    if(documentSnapshot.getString("tutortype")==null ||documentSnapshot.getString("studenttype")==null )
                    {
                        dialog.dismiss();


                    }
                }
            });

        }



    }



}