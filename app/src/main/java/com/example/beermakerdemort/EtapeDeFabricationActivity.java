package com.example.beermakerdemort;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EtapeDeFabricationActivity extends AppCompatActivity {
    private int index = 0;
    private ImageButton previous,next;
    private TextView contentText;
    private String[] textes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etape_fabrication);
        initialization();
    }

    private void initialization(){
        textes = new String[]{getString(R.string.text_1),getString(R.string.text_2),getString(R.string.text_3),
                getString(R.string.text_4),getString(R.string.text_5),getString(R.string.text_6)};
        contentText = findViewById(R.id.content_etape);
        contentText.setText(textes[0]);
        previous = findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>0){
                    index--;
                    changeText();
                }
            }
        });
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<textes.length-1){
                    index++;
                    changeText();
                }
            }
        });
        checkImageButtons();
    }

    private void changeText(){
        contentText.setText(textes[index]);
        checkImageButtons();
    }

    private void checkImageButtons(){
        if(index==0){
            previous.setVisibility(View.INVISIBLE);
        }else {
            previous.setVisibility(View.VISIBLE);
        }
        if(index==textes.length-1){
            next.setVisibility(View.INVISIBLE);
        }else {
            next.setVisibility(View.VISIBLE);
        }
    }
}
