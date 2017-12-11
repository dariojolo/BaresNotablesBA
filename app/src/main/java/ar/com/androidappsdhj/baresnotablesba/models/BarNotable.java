package ar.com.androidappsdhj.baresnotablesba.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rodrigrl on 04/12/2017.
 */

public class BarNotable extends RealmObject {

    @PrimaryKey
    private int ID;

    private String nombre;
    private String direccion;
    private String barrio;
    private String telefono;

    public BarNotable(){}
    public BarNotable(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public BarNotable(int ID, String nombre, String direccion, String barrio, String telefono) {
        this.ID = ID;
        this.nombre = nombre;
        this.direccion = direccion;
        this.barrio = barrio;
        this.telefono = telefono;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
