package ar.com.androidappsdhj.baresnotablesba.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.concurrent.atomic.AtomicInteger;

import ar.com.androidappsdhj.baresnotablesba.models.BarNotable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Dario on 10/12/2017.
 */

public class MyApp extends Application {
    private Realm realm;
    public static AtomicInteger barID = new AtomicInteger();
    private SharedPreferences prefs;
    public static int contadorPantallas = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        initRealm();
    }

    private boolean validarFirstTime() {
        return prefs.getBoolean("firstTime", false);
    }

    private void saveOnPreferences(String clave){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(clave, true);
        editor.apply();
    }
    private void initRealm(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("baresnotables.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        barID = setAtomicId(realm,BarNotable.class);

        Query query =
                FirebaseDatabase.getInstance().getReference()
                        .child("Bares");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(), "En onChildAdded", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "En onChildAdded 2: " + dataSnapshot.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "En onChildAdded 3: " + dataSnapshot.child("Nombre").getValue().toString(), Toast.LENGTH_LONG).show();
           //      if (!validarFirstTime()) {
                String ID = dataSnapshot.child("ID").getValue().toString();
                String nombre = dataSnapshot.child("Nombre").getValue().toString();
                String direccion = dataSnapshot.child("Direccion").getValue().toString();
                String barrio = dataSnapshot.child("Barrio").getValue().toString();
                String telefono = dataSnapshot.child("Telefono").getValue().toString();
                BarNotable bar = new BarNotable(Integer.parseInt(ID),nombre,direccion,barrio,telefono);
                insertarBarRealm(bar);
                }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(), "En onChildChanged", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "En onChildRemoved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(), "En onChildMoved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);

        realm.close();
    }

    private void iniciarListaBares() {
    }

    private void insertarBarRealm(BarNotable bar) {
        //Inserta Bar pasado por parametro
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bar);
        realm.commitTransaction();
    }

    private <T extends RealmObject>  AtomicInteger setAtomicId(Realm realm, Class<T>anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size()>0)? new AtomicInteger(results.max("ID").intValue()): new AtomicInteger();
    }
    private void removeAll(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
