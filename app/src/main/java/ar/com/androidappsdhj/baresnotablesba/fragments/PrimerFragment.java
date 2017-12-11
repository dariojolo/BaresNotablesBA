package ar.com.androidappsdhj.baresnotablesba.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ar.com.androidappsdhj.baresnotablesba.R;
import ar.com.androidappsdhj.baresnotablesba.adapters.BarHolder;
import ar.com.androidappsdhj.baresnotablesba.adapters.MyAdapter;
import ar.com.androidappsdhj.baresnotablesba.models.BarNotable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrimerFragment extends Fragment implements RealmChangeListener<RealmResults<BarNotable>> {

    private Query mQuery;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adapter;
    private View view;

    private Realm realm;
    private RealmResults<BarNotable> bares;


    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_primer, container, false);

        SharedPreferences prefs;
        prefs = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fragment", 1);
        editor.apply();

        //AdView mAdView = (AdView) view.findViewById(R.id.adView);
        //MobileAds.initialize(getActivity().getApplicationContext(), getResources().getString(R.string.app_id));
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

        realm = Realm.getDefaultInstance();

        bares = getAllBares();
        bares.addChangeListener(this);
        // programas = getAllProgramas();
        recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        adapter = new MyAdapter(bares, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BarNotable bar, int position) {
                //deletePrograma(position);
             //   Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
             //   intent.putExtra("Programa", programa.getId());
             //   intent.putExtra("Fragment", 1);
             //   startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(),"Seleccionado: " + bar.getNombre(),Toast.LENGTH_LONG).show();
            }
        });

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

/*
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getContext(), "En onChildAdded", Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "En onChildAdded 2: " + dataSnapshot.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "En onChildAdded 3: " + dataSnapshot.child("Nombre").getValue().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getContext(), "En onChildChanged", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(getContext(), "En onChildRemoved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getContext(), "En onChildMoved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);
*/
    /*    FirebaseRecyclerOptions<BarNotable> options =
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
                        Toast.makeText(getContext(), "En onCreateViewHolder", Toast.LENGTH_LONG).show();
                        return new BarHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(BarHolder holder,int position, BarNotable model) {
                        Toast.makeText(getContext(), "En onBindViewHolder", Toast.LENGTH_LONG).show();
                        holder.setNombre(model.getNombre());
                        holder.setDireccion(model.getDireccion());
                        super.onBindViewHolder(holder, position);
                    }

                    protected void populateViewHolder(final BarHolder holder, BarNotable model, int position){
                        Toast.makeText(getContext(), "En populateViewHolder", Toast.LENGTH_LONG).show();
                    }
                };*/
        return view;
        }

    private RealmResults<BarNotable> getAllBares() {
        //return realm.where(Programa.class).findAll();
        return realm.where(BarNotable.class).findAllSorted("nombre", Sort.ASCENDING);
    }

    @Override
    public void onChange(RealmResults<BarNotable> element) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }




}
