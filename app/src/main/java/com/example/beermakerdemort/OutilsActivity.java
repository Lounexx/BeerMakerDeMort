package com.example.beermakerdemort;

import android.app.Activity;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.beermakerdemort.Objects.Recette;
import com.example.beermakerdemort.utils.AccesLocal;
import com.example.beermakerdemort.utils.MySQLiteOpenHelper;

import java.util.ArrayList;

public class OutilsActivity extends AppCompatActivity {
    private EditText edit_volumeBiere,edit_degreAlcool,edit_ebc;
    private Button calculer, saveRecette;
    private Recette recette;
    private double quantiteMalt,volumeRincage,volumeBrassage,volumeLevure, mcu, volumeAmerisant, volumeAromatique, srm, volumeEBC;
    private ArrayList<EditText> edits;
    private TextView resultMalt,resultBrassage,resultRincage,resultHoublonAmerisant,resultHoublonaromatique, resultLevure, resultMCU, resultEBC, resultSRM, color_tv;
    private AccesLocal accesLocal;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outils_activity);
        initialize();
    }

    private void initialize(){
        accesLocal = new AccesLocal(this);
        accesLocal.initDB();
        recette = new Recette();
        edit_volumeBiere = findViewById(R.id.edit_volumeBiere);
        edit_degreAlcool = findViewById(R.id.edit_degreAlcool);
        edit_ebc = findViewById(R.id.edit_ebc);
        initList();
        initViews();
        calculer = findViewById(R.id.btn_calculer);
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidate()){
                    try {
                        calculate();
                        hideKeyboard(OutilsActivity.this);
                    }catch (Exception e){
                        Toast.makeText(OutilsActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        saveRecette = findViewById(R.id.save_button);
        saveRecette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accesLocal.saveRecette(recette);
            }
        });

        try{
            Bundle extras = getIntent().getExtras();
            Recette recette = (Recette) extras.getSerializable("recette");
            this.recette = recette;
            ((TextView)findViewById(R.id.recette_title)).setText("Recette n°" + recette.getId()+1);
            setupLoadedRecette();
            calculate(recette);
            findViewById(R.id.recette_name_to_show).setVisibility(View.VISIBLE);
        }catch (Exception e){}
    }

    private void calculate(){
       recette.setVolumeBiere(Double.parseDouble(edit_volumeBiere.getText().toString()));
       recette.setDegreAlcool(Double.parseDouble(edit_degreAlcool.getText().toString()));
       recette.setEbc(Double.parseDouble(edit_ebc.getText().toString()));

       quantiteMalt = Recette.calculateMalt(recette.getVolumeBiere(),recette.getDegreAlcool());

       resultMalt.setText(String.valueOf(quantiteMalt));

       volumeBrassage = arrondir(Recette.calculateEauBrassage(quantiteMalt),2);

       resultBrassage.setText(String.valueOf(volumeBrassage));

       volumeRincage = arrondir(Recette.calculateEauRincage(recette.getVolumeBiere(),volumeBrassage),2);

       resultRincage.setText(String.valueOf(volumeRincage));

       volumeLevure = Recette.calculateLevure(recette.getVolumeBiere());

       resultLevure.setText(String.valueOf(volumeLevure));

       mcu = arrondir(Recette.calculateMCU(recette.getEbc(),quantiteMalt,recette.getVolumeBiere()),3);

       resultMCU.setText(String.valueOf(mcu));

       volumeEBC = arrondir(2.9396 * (Math.pow(mcu,0.6859)),2);

       resultEBC.setText(String.valueOf(volumeEBC));

       srm = arrondir(0.508 * volumeEBC,3);

       resultSRM.setText(String.valueOf(srm));

       volumeAmerisant = Recette.calculateAmerisant(recette.getVolumeBiere());

       resultHoublonAmerisant.setText(String.valueOf(volumeAmerisant));

       volumeAromatique = Recette.calculateAromatique(recette.getVolumeBiere());

       resultHoublonaromatique.setText(String.valueOf(volumeAromatique));

       color_tv.setText(Recette.srmToRGB(srm));

       color_tv.setBackgroundColor(Color.parseColor(Recette.srmToRGB(srm)));

       findViewById(R.id.scrollLayout).setVisibility(View.VISIBLE);


    }

    private void calculate(Recette recette){

        quantiteMalt = Recette.calculateMalt(recette.getVolumeBiere(),recette.getDegreAlcool());

        resultMalt.setText(String.valueOf(quantiteMalt));

        volumeBrassage = arrondir(Recette.calculateEauBrassage(quantiteMalt),2);

        resultBrassage.setText(String.valueOf(volumeBrassage));

        volumeRincage = arrondir(Recette.calculateEauRincage(recette.getVolumeBiere(),volumeBrassage),2);

        resultRincage.setText(String.valueOf(volumeRincage));

        volumeLevure = Recette.calculateLevure(recette.getVolumeBiere());

        resultLevure.setText(String.valueOf(volumeLevure));

        mcu = arrondir(Recette.calculateMCU(recette.getEbc(),quantiteMalt,recette.getVolumeBiere()),3);

        resultMCU.setText(String.valueOf(mcu));

        volumeEBC = arrondir(2.9396 * (Math.pow(mcu,0.6859)),2);

        resultEBC.setText(String.valueOf(volumeEBC));

        srm = arrondir(0.508 * volumeEBC,3);

        resultSRM.setText(String.valueOf(srm));

        volumeAmerisant = Recette.calculateAmerisant(recette.getVolumeBiere());

        resultHoublonAmerisant.setText(String.valueOf(volumeAmerisant));

        volumeAromatique = Recette.calculateAromatique(recette.getVolumeBiere());

        resultHoublonaromatique.setText(String.valueOf(volumeAromatique));

        color_tv.setText(Recette.srmToRGB(srm));

        color_tv.setBackgroundColor(Color.parseColor(Recette.srmToRGB(srm)));

        findViewById(R.id.scrollLayout).setVisibility(View.VISIBLE);

    }

    private void initList(){
        edits = new ArrayList<>();
        edits.add(edit_volumeBiere);
        edits.add(edit_degreAlcool);
        edits.add(edit_ebc);
    }

    private boolean checkValidate(){
        boolean verif = true;
        for(EditText editText: edits){
            if (editText.getText() == null){
                verif = false;
                break;
            }
        }
        return verif;
    }

    private void initViews(){
        resultMalt = findViewById(R.id.tv_result_malt);
        resultBrassage = findViewById(R.id.tv_result_brassage);
        resultRincage = findViewById(R.id.tv_result_rincage);
        resultHoublonAmerisant = findViewById(R.id.tv_houblon_amerisant);
        resultHoublonaromatique = findViewById(R.id.tv_result_aromatique);
        resultLevure = findViewById(R.id.tv_result_levure);
        resultMCU = findViewById(R.id.resultMCU);
        resultEBC = findViewById(R.id.resultEBC);
        resultSRM = findViewById(R.id.resultSMR);
        color_tv = findViewById(R.id.color_tv);
    }

    private void setupLoadedRecette(){
        edit_volumeBiere.setText(String.valueOf(recette.getVolumeBiere()));
        edit_degreAlcool.setText(String.valueOf(recette.getDegreAlcool()));
        edit_ebc.setText(String.valueOf(recette.getEbc()));
        edit_volumeBiere.setEnabled(false);
        edit_degreAlcool.setEnabled(false);
        edit_ebc.setEnabled(false);
        saveRecette.setVisibility(View.GONE);
        calculer.setVisibility(View.GONE);
    }

    private static double arrondir(double chiffre, int nbChiffreApresVirgule){
        double chiffreArrond;
        String nbC = "1";
        for (int i = 0; i < nbChiffreApresVirgule; i++) {
            nbC += "0";
        }
        Integer arrond = Integer.parseInt(nbC);
        chiffreArrond = (double)Math.round(chiffre*arrond) / arrond;
        return chiffreArrond;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
