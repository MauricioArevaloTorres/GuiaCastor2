package com.example.guiacastor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.guiacastor2.ui.home.HomeFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Comentarios extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore ;
    private RecyclerView fstList ;
    private FirestoreRecyclerAdapter adapter;
    private TextView Tid, Tnombre, Tapellido;
    String id, Pnombre, Papellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Intent intent = getIntent();



        setContentView(R.layout.activity_comentarios);

        Tid = findViewById(R.id.txt_id);
        Tnombre = findViewById(R.id.txt_name);
        Tapellido = findViewById(R.id.txt_ape);
        
        firebaseFirestore = FirebaseFirestore.getInstance();
        fstList = findViewById(R.id.rcv_lista);



        id = intent.getStringExtra("Profesor");
        Pnombre = intent.getStringExtra("Nombre");
        Papellido = intent.getStringExtra("Apellido");

        Tid.setText(id);
        Tapellido.setText(Papellido);
        Tnombre.setText(Pnombre);
        //Query

        Query query = firebaseFirestore.collection("Profesores").document(id).collection("Comentarios");

        //Recycler

        FirestoreRecyclerOptions<ComentariosModel> options = new FirestoreRecyclerOptions.Builder<ComentariosModel>()
                .setQuery(query, ComentariosModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ComentariosModel , Comentarios.ComentarioViewHolder>(options) {

            public Comentarios.ComentarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_com_single, parent, false);
                return new Comentarios.ComentarioViewHolder(view);
            }

            public void onBindViewHolder(Comentarios.ComentarioViewHolder holder, int position, final ComentariosModel model) {
                holder.coment.setText(model.getComentario());
                holder.cali.setRating(model.getCalificacion());
            }


        };
        fstList.setHasFixedSize(true);
        fstList.setLayoutManager(new LinearLayoutManager(this));
        fstList.setAdapter(adapter);



    }


    private class ComentarioViewHolder extends RecyclerView.ViewHolder {

        private TextView coment;
        private RatingBar cali;



        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);

            coment = itemView.findViewById(R.id.txt_com);
            cali = itemView.findViewById(R.id.rtn_cali);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }
}