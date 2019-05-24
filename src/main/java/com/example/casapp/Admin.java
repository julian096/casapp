package com.example.casapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;

public class Admin extends AppCompatActivity {

    ImageButton Acocina, Asala, Abaño, Acuarto;
    FloatingActionButton openRegister;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        openRegister = (FloatingActionButton) findViewById(R.id.openRegister);
        Acocina = (ImageButton) findViewById(R.id.Acocina);
        Asala = (ImageButton) findViewById(R.id.Asala);
        Abaño = (ImageButton) findViewById(R.id.Abaño);
        Acuarto = (ImageButton) findViewById(R.id.Acuarto);

        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioRegistro = new Intent(getApplicationContext(),Register.class);
                finish();
                startActivity(cambioRegistro);
            }
        });
    }

    public void enviar(View view) {
        if(view.getId()==Acocina.getId()){
            if(Acocina.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Acocina.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Acocina.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Asala.getId()){
            if(Asala.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Asala.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Asala.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Abaño.getId()){
            if(Abaño.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Abaño.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Abaño.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Acuarto.getId()){
            if(Acuarto.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Acuarto.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Acuarto.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }
    }

}
