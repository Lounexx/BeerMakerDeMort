package com.example.beermakerdemort.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.example.beermakerdemort.Objects.Recette;
import com.example.beermakerdemort.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecetteViewHolder> {
    public interface Listener{
        void onClickButton(int position);
    }

    private final Listener callback;
    private ArrayList<Recette> recettes;

    public MyRecyclerViewAdapter(ArrayList<Recette> recettes,Listener callback){
        this.recettes = recettes;
        this.callback = callback;
    }

    @Override
    public RecetteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recette_cell,parent,false);
        return new RecetteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecetteViewHolder viewHolder, int position) {
        Recette recette = recettes.get(position);
        viewHolder.bind(recette,position,callback);
    }

    @Override
    public int getItemCount() {
        return recettes.size();
    }
    public Recette getRecette(int position){
        return recettes.get(position);
    }
}
