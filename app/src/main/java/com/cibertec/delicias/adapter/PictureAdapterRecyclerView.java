package com.cibertec.delicias.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.delicias.ActualizarActivity;
import com.cibertec.delicias.R;
import com.cibertec.delicias.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<ViewHolder> {

    private final List<Producto> productos;

    public PictureAdapterRecyclerView(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_picture, parent, false);
        return new ViewHolderChild(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if(productos != null && productos.size() > 0){
            return productos.size();
        }

        return 0;
    }

    public void deleteItem(int position) {
        if (productos != null & productos.size() > 0) {
            productos.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolderChild extends ViewHolder{

        ImageView img;
        TextView title;
        TextView cost;

        public ViewHolderChild(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgCard);
            title = itemView.findViewById(R.id.titleCard);
            cost = itemView.findViewById(R.id.costCard);

        }

        @Override
        protected void clear() {
            img.setImageDrawable(null);
            title.setText("");
            cost.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            Producto producto = productos.get(position);
            title.setText(producto.getTitle());
            cost.setText(producto.getCost());
            Picasso.get().load(producto.getUrl()).into(img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ActualizarActivity.class);
                    intent.putExtra("id", producto.getId());
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }

}
