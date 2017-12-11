package ar.com.androidappsdhj.baresnotablesba.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ar.com.androidappsdhj.baresnotablesba.R;
import ar.com.androidappsdhj.baresnotablesba.models.BarNotable;

/**
 * Created by Dario on 10/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<BarNotable> bares;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;


    public MyAdapter(List<BarNotable> programas, int layout,OnItemClickListener itemClickListener) {
        this.bares = programas;
        this.layout = layout;
        this.itemClickListener = itemClickListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.bind(bares.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return bares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;
        public TextView textViewDireccion;
        //public ImageView imageViewPoster;

        public ViewHolder(View v) {
            super(v);
            textViewNombre = (TextView) v.findViewById(R.id.textViewNombre);
            textViewDireccion= (TextView)v.findViewById(R.id.textViewDireccion);
            //imageViewPoster = (ImageView) v.findViewById(R.id.imagenPrograma);
        }

        public void bind(final BarNotable bar, final OnItemClickListener itemClickListener) {
            //Procesamos los datos a renderizar
            textViewNombre.setText(bar.getNombre());
            textViewDireccion.setText(bar.getDireccion());

            //Picasso
           // Picasso.with(context).load(bar.getImagen()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            //Definimos que por cada elemento de nuestro RecyclerView, tenemos un clickListener
            //Que se comporta de la siguiente manera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(bar, getAdapterPosition());
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(BarNotable bar, int position);
    }
}
