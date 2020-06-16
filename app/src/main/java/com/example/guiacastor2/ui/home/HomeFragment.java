package com.example.guiacastor2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guiacastor2.Comentarios;
import com.example.guiacastor2.ProfesorModel;
import com.example.guiacastor2.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HomeFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private HomeViewModel homeViewModel;
    private RecyclerView fstList;
    private FirestoreRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        fstList = root.findViewById(R.id.rcv_lista);


        //Query

        Query query = firebaseFirestore.collection("Profesores");

        //Recycler

        FirestoreRecyclerOptions<ProfesorModel> options = new FirestoreRecyclerOptions.Builder<ProfesorModel>()
                .setQuery(query, ProfesorModel.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<ProfesorModel , ProfesorViewHolder>(options) {

           public ProfesorViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
               return new ProfesorViewHolder(view);
           }

           public void onBindViewHolder(ProfesorViewHolder holder, int position, final ProfesorModel model) {
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


        //ViewHolder


        return root;
    }

    @NotNull
    private HomeFragment getHomeFragment() {
        return this;
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
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }


}