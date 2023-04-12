package com.cibertec.delicias.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ViewHolder extends RecyclerView.ViewHolder {

    private int position;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        this.position = position;
        clear();
    }

    public int getCurrentPosition() {
        return position;
    }

}
