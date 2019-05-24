package com.example.casapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText userName, password;
    CheckBox checkCocina, checkSala, checkBaño, checkCuarto;
    Button register, back, exit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        register = (Button) findViewById(R.id.btnRegister);
        back = (Button) findViewById(R.id.back);
        exit2 = (Button) findViewById(R.id.exit2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String Datos = "";
                    checkCocina = (CheckBox) findViewById(R.id.checkCocina);
                    checkSala = (CheckBox) findViewById(R.id.checkSala);
                    checkBaño = (CheckBox) findViewById(R.id.checkBaño);
                    checkCuarto = (CheckBox) findViewById(R.id.checkCuarto);

                    if(checkCocina.isChecked()){
                        Datos += "kitchen,";
                    }
                    if(checkSala.isChecked()){
                        Datos += "living_room,";
                    }
                    if(checkBaño.isChecked()){
                        Datos += "bathroom,";
                    }
                    if(checkCuarto.isChecked()){
                        Datos += "room,";
                    }

                    //Elimina el ultimo caracter de la cadena
                    Datos = Datos.substring(0, Datos.length() - 1);
                    Toast.makeText(getApplicationContext(),Datos,Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),userName.getText().toString()+"Porfavor seleccione al menos una sala"+password.getText().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioAdmin = new Intent(getApplicationContext(),Admin.class);
                finish();
                startActivity(cambioAdmin);
            }
        });

        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
