package com.example.appprova;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracoesActivity extends AppCompatActivity {

    private LinearLayout btnConfigRed;
    private LinearLayout btnConfigBlue;
    private LinearLayout btnConfigYellow;
    private LinearLayout btnConfigPurple;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        btnConfigPurple = findViewById(R.id.btnConfigPurple);
        btnConfigPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cor = ((ColorDrawable) btnConfigPurple.getBackground()).getColor();
                Intent it = new Intent();
                it.putExtra(Shared.KEY_COR_LIVRE, cor);
                it.putExtra(Shared.KEY_TIPO_RETORNO, 1);
                setResult(RESULT_FIRST_USER, it);
                finish();
            }
        });

        btnConfigYellow = findViewById(R.id.btnConfigYellow);
        btnConfigYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cor = ((ColorDrawable) btnConfigYellow.getBackground()).getColor();
                Intent it = new Intent();
                it.putExtra(Shared.KEY_COR_LIVRE, cor);
                it.putExtra(Shared.KEY_TIPO_RETORNO, 1);
                setResult(RESULT_FIRST_USER, it);
                finish();
            }
        });

        btnConfigRed = findViewById(R.id.btnConfigRed);
        btnConfigRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cor = ((ColorDrawable) btnConfigRed.getBackground()).getColor();
                Intent it = new Intent();
                it.putExtra(Shared.KEY_COR_RESERVA, cor);
                it.putExtra(Shared.KEY_TIPO_RETORNO, 0);
                setResult(RESULT_OK, it);
                finish();
            }
        });

        btnConfigBlue = findViewById(R.id.btnConfigBlue);
        btnConfigBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cor = ((ColorDrawable) btnConfigBlue.getBackground()).getColor();
                Intent it = new Intent();
                it.putExtra(Shared.KEY_COR_RESERVA, cor);
                it.putExtra(Shared.KEY_TIPO_RETORNO, 0);
                setResult(RESULT_OK, it);
                finish();
            }
        });

    }
}

