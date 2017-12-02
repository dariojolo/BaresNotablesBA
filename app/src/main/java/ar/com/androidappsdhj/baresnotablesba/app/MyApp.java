package ar.com.androidappsdhj.baresnotablesba.app;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Dario on 1/12/2017.
 */

public class MyApp extends Application {

    private DatabaseReference db;
    private ValueEventListener evenListener;

    private MyApp(){
         db = FirebaseDatabase.getInstance().getReference()
                .child("baresnotables-b3497");
         DatabaseReference bar1 = FirebaseDatabase.getInstance().getReference("1");
         bar1.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 String bar1 = (String) dataSnapshot.getValue();//Cambiar a tipo BAR
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {
                 Log.w("TAGLOG","ERROR:" + databaseError.toException());
                 Toast.makeText(getApplicationContext(),"Fallo al conectar",Toast.LENGTH_SHORT).show();
             }
         });

         evenListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recupero el ID del objeto que cambio
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAGLOG","ERROR:" + databaseError.toException());
            }
        };
        db.addValueEventListener(evenListener);
    }



}
