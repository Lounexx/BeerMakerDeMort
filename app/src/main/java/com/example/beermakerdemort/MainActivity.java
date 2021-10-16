package com.example.beermakerdemort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beermakerdemort.utils.AccesLocal;

public class MainActivity extends AppCompatActivity {
    private Button btn_fabrication,btn_outils,btn_recette;
    private AccesLocal accesLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization(){
        btn_fabrication = findViewById(R.id.btn_fabrication);
        btn_fabrication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EtapeDeFabricationActivity.class);
                startActivity(intent);
            }
        });

        btn_outils = findViewById(R.id.btn_outils);
        btn_outils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OutilsActivity.class);
                startActivity(intent);
            }
        });
        btn_recette = findViewById(R.id.btn_recette);
        btn_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecetteActivity.class);
                startActivity(intent);
            }
        });


    }
}