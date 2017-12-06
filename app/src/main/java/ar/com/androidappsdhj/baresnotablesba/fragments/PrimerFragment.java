package ar.com.androidappsdhj.baresnotablesba.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ar.com.androidappsdhj.baresnotablesba.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrimerFragment extends Fragment {

    private Query mQuery;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter mAdapterFire;
    private View view;
    private TextView txtNombre;
    private TextView txtDireccion;


    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.prueba_firebase, container, false);

        txtNombre = view.findViewById(R.id.txtNombre);
        txtDireccion = view.findViewById(R.id.txtDireccion);

        txtNombre.setText("Aca va el nombre");
        txtDireccion.setText("Aca va la direccion");

        /*recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());*/

        DatabaseReference dbBares =
                FirebaseDatabase.getInstance().getReference()
                        .child("baresnotables-b3497");

        dbBares.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String clave = (String) dataSnapshot.getKey();
                if (clave.equals("2")) {
                    txtNombre.setText(dataSnapshot.child("Nombre").getValue().toString());
                    txtDireccion.setText(dataSnapshot.child("Direccion").getValue().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*FirebaseRecyclerOptions<BarNotable> options =
                new FirebaseRecyclerOptions.Builder<BarNotable>()
                        .setQuery(dbBares, BarNotable.class)
                        .build();

        FirebaseRecyclerAdapter mAdapter =
                new FirebaseRecyclerAdapter<BarNotable, BarHolder>(
                        options) {
                    @Override
                    public BarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        // Create a new instance of the ViewHolder, in this case we are using a custom
                        // layout called R.layout.message for each item
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.list_item_recycler, parent, false);

                        return new BarHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(BarHolder holder, int position, BarNotable model) {
                        holder.setNombre(model.getNombre());
                        holder.setDireccion(model.getDireccion());
                    }
                };*/

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
      /*  recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(mAdapter);*/
        return view;
        }
}
