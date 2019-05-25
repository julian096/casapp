package com.example.casapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText userName, password;
    CheckBox checkCocina, checkSala, checkBaño, checkCuarto;
    Button register, back, exit2;
    RequestQueue requestQueue;

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
                    if(!userName.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()){
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
                            Datos += "bedroom,";
                        }

                        //Elimina el ultimo caracter de la cadena
                        Datos = Datos.substring(0, Datos.length() - 1);

                        //llamada a funcion para insersion de datos
                        saveUser(userName.getText().toString(),password.getText().toString(),Datos);

                        //Toast.makeText(getApplicationContext(),Datos,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Porfavor rellene todos los campos",Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Porfavor seleccione al menos una sala",Toast.LENGTH_SHORT).show();
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

    public void saveUser(String name, String pass, String focos) throws JSONException {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.0.6:5000/addUpdate";
        JSONObject dataUser = new JSONObject();
        dataUser.put("username", name);
        dataUser.put("password", pass);
        dataUser.put("spotlights", focos);
        final String requestData = dataUser.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
                userName.setText("");
                password.setText("");
                checkCocina.setChecked(false);
                checkSala.setChecked(false);
                checkBaño.setChecked(false);
                checkCuarto.setChecked(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Hubo un problema",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestData == null ? null : requestData.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Toast.makeText(getApplicationContext(), "No soporta codificacion utf-8", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }


}
