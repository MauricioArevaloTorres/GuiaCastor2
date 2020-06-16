package com.example.guiacastor2.ui.find;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guiacastor2.Comentarios;
import com.example.guiacastor2.ProfesorModel;
import com.example.guiacastor2.R;
import com.example.guiacastor2.ui.find.FindViewModel;
import com.example.guiacastor2.ui.home.HomeFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class FindFragment extends Fragment {

    private FindViewModel findViewModel;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fstList;
    private FirestoreRecyclerAdapter adapter;
    private EditText busq;
    private String find;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        findViewModel =
                ViewModelProviders.of(this).get(FindViewModel.class);
        View root = inflater.inflate(R.layout.fragment_find, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        fstList = root.findViewById(R.id.rcv_list);
        busq = root.findViewById(R.id.txt_find);

        //Query
        find = "";

        //Recycler


        ImageButton imageButton = (ImageButton) root.findViewById(R.id.btn_find);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find = busq.getText().toString();
                Query query = firebaseFirestore.collection("Profesores").whereEqualTo("nombre",find);
                FirestoreRecyclerOptions<ProfesorModel> options = new FirestoreRecyclerOptions.Builder<ProfesorModel>()
                        .setQuery(query, ProfesorModel.class)
                        .build();

                adapter = new FirestoreRecyclerAdapter<ProfesorModel , FindFragment.ProfesorViewHolder>(options) {

                    public FindFragment.ProfesorViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                        return new FindFragment.ProfesorViewHolder(view);
                    }

                    public void onBindViewHolder(FindFragment.ProfesorViewHolder holder, int position, final ProfesorModel model) {
                        holder.name.setText(model.getNombre());
                        holder.last.setText(model.getApellido());
                        holder.id.setText(model.getIdP());

                        holder.name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity().getApplication() , Comentarios.class);
                                intent.putExtra("Profesor", model.getIdP());
                                intent.putExtra("Nombre",model.getNombre());
                                intent.putExtra("Apellido",model.getApellido());
                                intent.putExtra("Area",model.getArea());
                                startActivity(intent);
                            }
                        });

                    }


                };
                fstList.setHasFixedSize(true);
                fstList.setLayoutManager(new LinearLayoutManager(getActivity()));
                fstList.setAdapter(adapter);
                adapter.startListening();
            }
        });








        //ViewHolder


        return root;
    }


    private class ProfesorViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView last;
        private TextView id;


        public ProfesorViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_name);
            last = itemView.findViewById(R.id.txt_last);
            id = itemView.findViewById(R.id.lbl_id);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        //adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //adapter.startListening();
    }



}