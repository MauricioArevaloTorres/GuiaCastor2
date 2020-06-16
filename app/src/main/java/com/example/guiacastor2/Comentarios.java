package com.example.guiacastor2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Comentarios extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore ;
    private RecyclerView fstList ;
    private FirestoreRecyclerAdapter adapter;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_comentarios);
        firebaseFirestore = FirebaseFirestore.getInstance();
        fstList = findViewById(R.id.rcv_lista);

        id = intent.getStringExtra("Profesor");

        //Query

        Query query = firebaseFirestore.collection("Profesores").document(id).collection("Comentarios");

        //Recycler

        FirestoreRecyclerOptions<ComentariosModel> options = new FirestoreRecyclerOptions.Builder<ComentariosModel>()
                .setQuery(query, ComentariosModel.class)
                .build();

    }
}