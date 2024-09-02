package com.example.appprova;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReservasActivity extends AppCompatActivity {

    private Button btnConfiguracoes, btnLiberarMesa;
    private EditText editLiberarMesa;

    private LinearLayout mesaUm;
    private Button btnMesaUm;

    private LinearLayout mesaDois;
    private Button btnMesaDois;

    private LinearLayout mesaTres;
    private Button btnMesaTres;

    private LinearLayout mesaQuatro;
    private Button btnMesaQuatro;

    private int corLivre, corReservado;

    private boolean silent = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReservasActivity.this);
        corReservado = preferences.getInt(Shared.KEY_COR_RESERVA, getColor(R.color.red));
        corLivre = preferences.getInt(Shared.KEY_COR_LIVRE, getColor(R.color.yellow));

        btnConfiguracoes = findViewById(R.id.btnConfiguracoes);
        btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ReservasActivity.this, ConfiguracoesActivity.class);
                startActivityForResult(it, 1);
            }
        });

        editLiberarMesa = findViewById(R.id.editLiberarMesa);
        btnLiberarMesa = findViewById(R.id.btnLiberarMesa);
        btnLiberarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numMesa = String.valueOf(editLiberarMesa.getText());
                if (numMesa.isEmpty()) return;
                editLiberarMesa.setText("");
                switch (numMesa) {
                    case "1": {
                        if (((ColorDrawable) mesaUm.getBackground()).getColor() == corLivre) {
                            Toast.makeText(ReservasActivity.this, "Mesa "+numMesa+" livre!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mesaUm.setBackgroundColor(corLivre);
                        btnMesaUm.setEnabled(true);
                        AtualizarCorSharedPreferences(String.valueOf(mesaUm.getId()), corLivre);
                        break;
                    }
                    case "2": {
                        if (((ColorDrawable) mesaDois.getBackground()).getColor() == corLivre) {
                            Toast.makeText(ReservasActivity.this, "Mesa "+numMesa+" livre!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mesaDois.setBackgroundColor(corLivre);
                        btnMesaDois.setEnabled(true);
                        AtualizarCorSharedPreferences(String.valueOf(mesaDois.getId()), corLivre);
                        break;
                    }
                    case "3": {
                        if (((ColorDrawable) mesaTres.getBackground()).getColor() == corLivre) {
                            Toast.makeText(ReservasActivity.this, "Mesa "+numMesa+" livre!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mesaTres.setBackgroundColor(corLivre);
                        btnMesaTres.setEnabled(true);
                        AtualizarCorSharedPreferences(String.valueOf(mesaTres.getId()), corLivre);
                        break;
                    }
                    case "4": {
                        if (((ColorDrawable) mesaQuatro.getBackground()).getColor() == corLivre) {
                            Toast.makeText(ReservasActivity.this, "Mesa "+numMesa+" livre!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mesaQuatro.setBackgroundColor(corLivre);
                        btnMesaQuatro.setEnabled(true);
                        AtualizarCorSharedPreferences(String.valueOf(mesaQuatro.getId()), corLivre);
                        break;
                    }
                    default: {
                        Toast.makeText(ReservasActivity.this, "Mesa "+numMesa+" não existe!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });

        // Configuração de mesas
        mesaUm = findViewById(R.id.mesaUm);
        btnMesaUm = findViewById(R.id.btnMesaUm);
        adicionarClickListener(mesaUm, btnMesaUm);

        mesaDois = findViewById(R.id.mesaDois);
        btnMesaDois = findViewById(R.id.btnMesaDois);
        adicionarClickListener(mesaDois, btnMesaDois);

        mesaTres = findViewById(R.id.mesaTres);
        btnMesaTres = findViewById(R.id.btnMesaTres);
        adicionarClickListener(mesaTres, btnMesaTres);

        mesaQuatro = findViewById(R.id.mesaQuatro);
        btnMesaQuatro = findViewById(R.id.btnMesaQuatro);
        adicionarClickListener(mesaQuatro, btnMesaQuatro);

        InicializarCoresMesas();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int corReservadoAtual = data.getIntExtra(Shared.KEY_COR_RESERVA, corReservado);
                AtualizarCorSharedPreferences(Shared.KEY_COR_RESERVA, corReservadoAtual);
                AtualizarCorMesa(mesaUm, btnMesaUm, corReservadoAtual, corLivre, corLivre);
                AtualizarCorMesa(mesaDois, btnMesaDois, corReservadoAtual, corLivre, corLivre);
                AtualizarCorMesa(mesaTres, btnMesaTres, corReservadoAtual, corLivre, corLivre);
                AtualizarCorMesa(mesaQuatro, btnMesaQuatro, corReservadoAtual, corLivre, corLivre);
                corReservado = corReservadoAtual;
            }
            if (resultCode == RESULT_FIRST_USER) {
                int corLivreAtual = data.getIntExtra(Shared.KEY_COR_LIVRE, corLivre);
                AtualizarCorSharedPreferences(Shared.KEY_COR_LIVRE, corLivreAtual);
                AtualizarCorMesa(mesaUm, btnMesaUm, corReservado, corLivreAtual, corLivre);
                AtualizarCorMesa(mesaDois, btnMesaDois, corReservado, corLivreAtual, corLivre);
                AtualizarCorMesa(mesaTres, btnMesaTres, corReservado, corLivreAtual, corLivre);
                AtualizarCorMesa(mesaQuatro, btnMesaQuatro, corReservado, corLivreAtual, corLivre);
                corLivre = corLivreAtual;
            }
        }
    }

    //Dependentes

    private void InicializarCoresMesas() {
        // Mesa 1
        InicializarCorMesa(mesaUm, btnMesaUm);
        // Mesa 2
        InicializarCorMesa(mesaDois, btnMesaDois);
        // Mesa 3
        InicializarCorMesa(mesaTres, btnMesaTres);
        // Mesa 4
        InicializarCorMesa(mesaQuatro, btnMesaQuatro);
    }

    //Precisam de corLivre/Reservado

    private void InicializarCorMesa(LinearLayout mesa, Button btnMesa) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReservasActivity.this);
        int corMesa = preferences.getInt(String.valueOf(mesa.getId()), corLivre);
        if (corMesa != corLivre) btnMesa.setEnabled(false);
        mesa.setBackgroundColor(corMesa);
    }

    private void adicionarClickListener(LinearLayout mesa, Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int corAtual = ((ColorDrawable) mesa.getBackground()).getColor();
                if (corAtual == corLivre) {
                    mesa.setBackgroundColor(corReservado);
                    btn.setEnabled(false);
                    AtualizarMesaSharedPreferences(mesa);
                } else {
                    if (!silent) {
                        Toast.makeText(ReservasActivity.this, "Mesa já reservada!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //Independentes
    private void AtualizarCorMesa(LinearLayout mesa, Button botao, int corReservadoNova, int corLivreNova, int corLivreAtual) {
        int corAtual = ((ColorDrawable) mesa.getBackground()).getColor();
        if (corAtual != corLivreAtual) {
            mesa.setBackgroundColor(corReservadoNova);
            botao.setEnabled(false);
        }
        else {
            mesa.setBackgroundColor(corLivreNova);
            botao.setEnabled(true);
        }

        AtualizarMesaSharedPreferences(mesa);
    }

    private void AtualizarCorSharedPreferences(String key, int cor) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReservasActivity.this);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, cor);
        edit.apply();
    }

    private void AtualizarMesaSharedPreferences(LinearLayout mesa) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReservasActivity.this);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(String.valueOf(mesa.getId()), ((ColorDrawable) mesa.getBackground()).getColor());
        edit.apply();
    }

}
