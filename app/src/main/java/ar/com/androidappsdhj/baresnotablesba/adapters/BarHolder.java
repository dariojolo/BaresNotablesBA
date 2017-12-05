package ar.com.androidappsdhj.baresnotablesba.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.com.androidappsdhj.baresnotablesba.R;

/**
 * Created by rodrigrl on 05/12/2017.
 */

public class BarHolder extends RecyclerView.ViewHolder {
    private View mView;

    public BarHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setNombre(String nombre) {
        TextView field = (TextView) mView.findViewById(R.id.textViewNombre);
        field.setText(nombre);
    }
    public void setDireccion(String direccion) {
        TextView field = (TextView) mView.findViewById(R.id.textViewDireccion);
        field.setText(direccion);
    }
}
