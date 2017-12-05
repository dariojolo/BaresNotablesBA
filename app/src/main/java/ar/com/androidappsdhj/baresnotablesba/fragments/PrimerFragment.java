package ar.com.androidappsdhj.baresnotablesba.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import ar.com.androidappsdhj.baresnotablesba.R;
import ar.com.androidappsdhj.baresnotablesba.adapters.BarHolder;
import ar.com.androidappsdhj.baresnotablesba.models.BarNotable;

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


    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_primer, container, false);

        recycler =  view.findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        DatabaseReference dbBares =
                FirebaseDatabase.getInstance().getReference()
                        .child("baresnotables-b3497");

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("baresnotables-b3497")
                .limitToLast(50);


        FirebaseRecyclerOptions<BarNotable> options =
                new FirebaseRecyclerOptions.Builder<BarNotable>()
                        .setQuery(query, BarNotable.class)
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
                };
        recycler.setAdapter(mAdapter);
        return view;
        }
}
