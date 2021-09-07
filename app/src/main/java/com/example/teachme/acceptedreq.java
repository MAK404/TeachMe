package com.example.teachme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class acceptedreq extends AppCompatActivity {
    TextView txt;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String studentID;
    RecyclerView recview;
    ArrayList<model3> datalist;
    myadaptor3 adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptedreq);
        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        studentID=firebaseAuth.getCurrentUser().getUid();
        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adaptor=new myadaptor3(datalist);
        recview.setAdapter(adaptor);
        firestore.collection("users").document(studentID).collection("accepted").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    model3 obj2=d.toObject(model3.class);
                    datalist.add(obj2);
                }
                adaptor.notifyDataSetChanged();
            }
        });
    }
}