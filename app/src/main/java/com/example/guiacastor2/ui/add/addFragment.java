package com.example.guiacastor2.ui.add;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.guiacastor2.ProfesorModel;
import com.example.guiacastor2.R;
import com.example.guiacastor2.ui.add.addViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class addFragment extends Fragment {

    private addViewModel addViewModel;
    private EditText name;
    private EditText last;
    private EditText idP;
    private EditText area;

    private FirebaseFirestore firebaseFirestore;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(addViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();

        name    = root.findViewById(R.id.txt_name);
        last    = root.findViewById(R.id.txt_last);
        idP     = root.findViewById(R.id.txt_id);
        area    = root.findViewById(R.id.txt_area);
        Button button = (Button) root.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom  = name.getText().toString();
                String ape  = last.getText().toString();
                String id   = idP.getText().toString();
                String ar   = area.getText().toString();

                CollectionReference profesores = firebaseFirestore.collection("Profesores");

                ProfesorModel profesorModel = new ProfesorModel(nom, ape, id, ar);

                profesores.document(id).set(profesorModel);

            }
        });

        return root;
    }



}