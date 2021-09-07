package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class showtutors extends AppCompatActivity {
    TextView txt;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    RecyclerView recview;
    ArrayList<model5> datalist;
    myadaptor5 adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtutors);
        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
       adaptor=new myadaptor5(datalist);
        recview.setAdapter(adaptor);
        firestore.collection("users").whereEqualTo("tutorapproved","0").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            model5 obj=d.toObject(model5.class);
                            datalist.add(obj);
                        }
                        adaptor.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(showtutors.this, adminwindow.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}