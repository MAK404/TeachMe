package com.example.teachme;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_login, container, false);

    }
    String mail,pwd;
    EditText ed1,ed2;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    ProgressDialog dialog;
    FirebaseFirestore fstore;
    ViewPager2 pager2;
    TabLayout tabLayout;
    StorageReference storagerefrence;

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // Inflate the layout for this fragment

        ed1 =(EditText) view.findViewById(R.id.inputEmail);
        ed2 = (EditText) view.findViewById(R.id.inputPassword);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
        Button btn1 = (Button) view.findViewById(R.id.btnLogin);
        TextView forgottxt = (TextView) view.findViewById(R.id.forgotPassword);
        TextView gotoreg = (TextView) view.findViewById(R.id.gotoRegister);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Please wait...");
        fstore=FirebaseFirestore.getInstance();
        storagerefrence= FirebaseStorage.getInstance().getReference();

        fauth=FirebaseAuth.getInstance();
//        if(fauth.getCurrentUser()!=null)
//        {
//            dialog.show();
//        }






        btn1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                dialog.show();
                if (OK())
                {
                    mail=ed1.getText().toString();
                    pwd=ed2.getText().toString();

                    fauth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {


                                        checkuseracess(fauth.getCurrentUser().getUid());

//                                progressBar.setVisibility(View.INVISIBLE);


                                //startActivity(new Intent(getActivity(), tutorAsUser.class));
                            } else {
                                Toast.makeText(getActivity(), "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
//                                  progressBar.setVisibility(View.INVISIBLE);
                            }


                        }

                    });

            }


            }

        });

        forgottxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetmail = new EditText(view.getContext());
                final AlertDialog.Builder pwddialog = new AlertDialog.Builder(view.getContext());
                pwddialog.setTitle("Reset Passoword?");
                pwddialog.setMessage("Enter Mail Address to get Reset Link!");
                pwddialog.setView(resetmail);
                pwddialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetmail.getText().toString();
                        fauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Reset Link has been sent to your Mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Some Error Occured"+" "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                pwddialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                pwddialog.create().show();
            }
        });
//        gotoreg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
//                pager2.setCurrentItem();
//            }
//        });
 }
 private void checkuseracess(String uid)
 {
     DocumentReference df =fstore.collection("users").document(uid);
     df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
         @Override
         public void onSuccess(DocumentSnapshot documentSnapshot) {
        if(documentSnapshot.getString("tutortype")!=null)
        {
            dialog.dismiss();
            startActivity(new Intent(getActivity(),tutorafterlogin.class));


        }
        else if( documentSnapshot.getString("accountdeleted")!=null )
        {
            Intent i = new Intent(getActivity(), userdeleted.class);
            //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
       else if(documentSnapshot.getString("studenttype")!=null)
        {
            dialog.dismiss();
          startActivity(new Intent(getActivity(),studentafterlogin.class));
        }
         else if(documentSnapshot.getString("admin")!=null)
         {
             dialog.dismiss();
             startActivity(new Intent(getActivity(),adminwindow.class));
         }
        else if(documentSnapshot.getString("tutortype")==null )
         {
             Intent i = new Intent(getActivity(), approval.class);
             //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
             startActivity(i);

         }
        else if( documentSnapshot.getString("studenttype")==null )
        {
            Intent i = new Intent(getActivity(), approval.class);
            //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }

         }
     });


 }
    public boolean OK()
    {
        if(TextUtils.isEmpty((ed1.getText().toString())))
        {
            ed1.setError("Email is Required");
            return false;
        }

        if(TextUtils.isEmpty(ed2.getText().toString()))
        {
            ed2.setError("Password is Required");
            return false;
        }
        if (ed2.getText().length()<6)
        {
            ed2.setError("Password must be >6 chars");
             return false;
        }
     else {

            return true;
        }
    }

}