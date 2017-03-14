package com.example.radog.a2017_03_13_patm_verdatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerDatos extends AppCompatActivity {

    @BindView(R.id.tvNombre) TextView tvNombre;
    @BindView(R.id.tvTelefono) TextView tvTelefono;
    @BindView(R.id.tvEmail) TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);
        ButterKnife.bind(this);

        Bundle datos = getIntent().getExtras();
        tvNombre.setText(datos.getString("NOM"));
        tvTelefono.setText(datos.getString("TEL"));
        tvEmail.setText(datos.getString("EMA"));
    }
}
