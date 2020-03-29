package com.example.jeu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonOk;
    private EditText editText;
    private TextView textViewIndication;
    private int nombreSecret;
    private int devinez;
    private ProgressBar progressBarTentative;
    private TextView textViewTenetative;
    private int counter = 1;
    private int score;
    private ListView listViewHistorique;
    private List<String> listHistorique = new ArrayList<String>();
    private int maxtentative = 6;
    private TextView textViewScore;
    ArrayAdapter<String> model;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOk = findViewById(R.id.buttonOk);
        editText = findViewById(R.id.editTextNumber);
        textViewTenetative = findViewById(R.id.textViewTentative);
        progressBarTentative = findViewById(R.id.progressBar);
        textViewIndication = findViewById(R.id.textViewIndication);
        textViewScore = findViewById(R.id.textViewScore);
        listViewHistorique = findViewById(R.id.listViewHistorique);
        model = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listHistorique);
        listViewHistorique.setAdapter(model);
        initialisation();

        buttonOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(editText.getText().toString());
                textViewIndication.setText(String.valueOf(nombreSecret));
                if (number > nombreSecret) {
                     textViewIndication.setText(R.string.val_sup);
                }
                else if (number < nombreSecret) {
                    textViewIndication.setText(R.string.val_inf);
                } else {
                    score+=5;
                    textViewScore.setText(String.valueOf(score));
                    rejouer();

                }
                listHistorique.add(counter + "-->"+number);
                model.notifyDataSetChanged();
                ++counter;
                textViewTenetative.setText(String.valueOf(counter));
                progressBarTentative.setProgress(counter);

                if (counter >= maxtentative ) {
                    rejouer();
                }

            }
        });

    }

    private void rejouer() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.nvl_essai));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initialisation();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.quit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }


    void initialisation() {
        this.nombreSecret =1 + (int)(Math.random()*100);
        Log.i("Mytag",String.valueOf(nombreSecret));
        textViewScore.setText(String.valueOf(score));
        this.counter = 1;
        textViewTenetative.setText(String.valueOf(counter));
        progressBarTentative.setProgress(counter);
        progressBarTentative.setMax(maxtentative);
        textViewIndication.setText("");
        model.clear();

    }


   public void finish(){

    }

}
