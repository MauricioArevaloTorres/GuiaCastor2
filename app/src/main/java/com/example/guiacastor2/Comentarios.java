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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.guiacastor2.ui.home.HomeFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Comentarios extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fstList;
    private FirestoreRecyclerAdapter adapter;
    private TextView Tid, Tnombre, Tapellido, Tarea;
    String id, Pnombre, Papellido, Parea;

    private EditText Comentario;
    private RatingBar Calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        getSupportActionBar().hide();



        setContentView(R.layout.activity_comentarios);

        Tid = findViewById(R.id.txt_id);
        Tnombre = findViewById(R.id.txt_name);
        Tapellido = findViewById(R.id.txt_ape);
        Tarea = findViewById(R.id.txt_area);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fstList = findViewById(R.id.rcv_lista);

        Comentario = findViewById(R.id.txt_come);
        Calificacion = findViewById(R.id.rtn_cali);

        id = intent.getStringExtra("Profesor");
        Pnombre = intent.getStringExtra("Nombre");
        Papellido = intent.getStringExtra("Apellido");
        Parea = intent.getStringExtra("Area");

        Tid.setText(id);
        Tapellido.setText(Papellido);
        Tnombre.setText(Pnombre);
        Tarea.setText(Parea);
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

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_add);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference coment = firebaseFirestore.collection("Profesores").document(id).collection("Comentarios");
                String com = Comentario.getText().toString();
                Float cal = Calificacion.getRating();

                ComentariosModel comentariosModel = new ComentariosModel(com,cal);

                coment.add(comentariosModel);
            }
        });

        ImageButton gohome = (ImageButton) findViewById(R.id.btn_home);

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplication(), Principal.class);
                startActivity(intent1);
            }
        });



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