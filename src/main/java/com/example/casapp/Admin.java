package com.example.casapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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

    public void enviar(View view) throws JSONException {
        if(view.getId()==Acocina.getId()){
            if(Acocina.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("kitchen","on");
                //Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Acocina.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("kitchen","off");
                //Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Acocina.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Asala.getId()){
            if(Asala.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("living_room","on");
                //Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Asala.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("living_room","off");
                //Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Asala.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Abaño.getId()){
            if(Abaño.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("bathroom","on");
                //Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Abaño.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("bathroom","off");
                //Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Abaño.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Acuarto.getId()){
            if(Acuarto.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("bedroom","on");
                //Toast.makeText(getApplicationContext(),"Foco encendido",Toast.LENGTH_SHORT).show();
                Acuarto.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("bedroom","off");
                //Toast.makeText(getApplicationContext(),"Foco apagado",Toast.LENGTH_SHORT).show();
                Acuarto.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }
    }

    public void changeStatus(String sala, String value) throws JSONException {

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.0.6:5000/spotlight/"+sala;
        JSONObject jsonbody = new JSONObject();
        jsonbody.put("status", value);
        final String requestStatus = jsonbody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"si jala", Toast.LENGTH_SHORT).show();
                //Obtengo el tiempo encendido del foco
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    return requestStatus == null ? null : requestStatus.getBytes("utf-8");
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
