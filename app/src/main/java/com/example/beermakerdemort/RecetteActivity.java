package com.example.beermakerdemort;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beermakerdemort.Adapters.MyRecyclerViewAdapter;
import com.example.beermakerdemort.Objects.Recette;
import com.example.beermakerdemort.utils.AccesLocal;

import java.util.ArrayList;

public class RecetteActivity extends AppCompatActivity implements MyRecyclerViewAdapter.Listener {
    private RecyclerView recyclerView;
    private ArrayList<Recette> recettes = new ArrayList<>();
    private AccesLocal accesLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette_activity);
        initialize();
    }

    private void initialize(){
        accesLocal = new AccesLocal(this);
        recettes = accesLocal.getAllRecettes();
        recyclerView = findViewById(R.id.recette_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(recettes, this));
    }

    @Override
    public void onClickButton(int position) {
        Recette recette = ((MyRecyclerViewAdapter)recyclerView.getAdapter()).getRecette(position);
        Intent intent = new Intent(RecetteActivity.this,OutilsActivity.class);
        intent.putExtra("recette",recette);
        startActivity(intent);
        finish();
    }
}
