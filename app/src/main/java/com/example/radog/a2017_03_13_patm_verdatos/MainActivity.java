package com.example.radog.a2017_03_13_patm_verdatos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvNombre) TextView etNombre;
    @BindView(R.id.tvTelefono) TextView etTelefono;
    @BindView(R.id.tvEmail) TextView etEmail;

    MediaPlayer objM;
    private int pause;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAceptar)
    public void btnAceptar() {
        Intent objI = new Intent(this, VerDatos.class);
        Bundle datos = new Bundle();

        //enviar datos
        datos.putString("NOM", etNombre.getText().toString());
        datos.putString("TEL", etTelefono.getText().toString());
        datos.putString("EMA", etEmail.getText().toString());

        objI.putExtras(datos);
        startActivity(objI);
    }

    @OnClick(R.id.btnPlay)
    public void btnPlay() {

        if(objM == null) {
            //objM = MediaPlayer.create(this, R.raw.guren_no_yumiya_violin);
            objM = MediaPlayer.create(this, R.raw.guren_no_yumiya);
            objM.start();

        } else if(!objM.isPlaying()) {
            objM.seekTo(pause);
            objM.start();
        }
    }

    @OnClick(R.id.btnPause)
    public void btnPause(){
        if(objM != null) {
            objM.pause();
            pause = objM.getCurrentPosition();
        }
    }

    @OnClick(R.id.btnStop)
    public void btnStop() {
        if(objM != null) {
            objM.release();
            objM = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle datos) {
        int position;

        //guardar datos
        datos.putString("NOM",  etNombre.getText().toString());
        datos.putString("TEL", etTelefono.getText().toString());
        datos.putString("EMA", etEmail.getText().toString());

        if(objM != null) { //verifica que exista un objeto MediaPlayer
            //guarda hasta donde se está reproduciendo
            position = objM.getCurrentPosition();
            datos.putInt("POS", position);
            objM.pause(); //pausa la música, se reanuda en onRestoreInstanceState
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle datos) {
        int position;

        //datos recuperados
         etNombre.setText(datos.getString("NOM"));
        etTelefono.setText(datos.getString("TEL"));
        etEmail.setText(datos.getString("EMA"));

        //el objeto se vuelve null... no sé porqué
        if(datos != null && objM == null) {
            objM = MediaPlayer.create(this, R.raw.guren_no_yumiya); //vuelvo a crear el objeto
            position = datos.getInt("POS"); //recupero la posición en donde se quedó
            objM.seekTo(position); //voy a esa posición
            objM.start(); //restauro la música
        }
    }
}
