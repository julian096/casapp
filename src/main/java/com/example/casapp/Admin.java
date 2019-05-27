package com.example.casapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Admin extends AppCompatActivity {

    ImageButton Acocina, Asala, Abaño, Acuarto;
    LinearLayout AfocoCocina,AfocoSala,AfocoBaño,AfocoCuarto;
    FloatingActionButton openRegister;
    RequestQueue requestQueue;
    Button logoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initElem();

        AfocoCocina.setVisibility(View.INVISIBLE);
        AfocoSala.setVisibility(View.INVISIBLE);
        AfocoBaño.setVisibility(View.INVISIBLE);
        AfocoCuarto.setVisibility(View.INVISIBLE);
        Intent inte = getIntent();
        Bundle b = inte.getExtras();
        String spotlights = b.getString("spotlights");
        String arraySpotlights[] = spotlights.split(",");

        Toast.makeText(getApplicationContext(),spotlights,Toast.LENGTH_SHORT).show();

        for(int i=0; i<arraySpotlights.length; i++){
            if(arraySpotlights[i].equals("kitchen")){
                if(!arraySpotlights[i+1].equals("off")){
                    Abaño.setImageResource(R.drawable.focoon);
                }
                AfocoCocina.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("living_room")){
                if(!arraySpotlights[i+1].equals("off")){
                    Abaño.setImageResource(R.drawable.focoon);
                }
                AfocoSala.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("bathroom")){
                if(!arraySpotlights[i+1].equals("off")){
                    Abaño.setImageResource(R.drawable.focoon);
                }
                AfocoBaño.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("bedroom")){
                if(!arraySpotlights[i+1].equals("off")){
                    Abaño.setImageResource(R.drawable.focoon);
                }
                AfocoCuarto.setVisibility(View.VISIBLE);
            }
        }

        logoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutAdmin = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(logoutAdmin);
            }
        });

        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioRegistro = new Intent(getApplicationContext(),Register.class);
                finish();
                startActivity(cambioRegistro);
            }
        });
    }

    public void initElem(){
        AfocoCocina = (LinearLayout) findViewById(R.id.AfocoCocina);
        AfocoSala = (LinearLayout) findViewById(R.id.AfocoSala);
        AfocoBaño = (LinearLayout) findViewById(R.id.AfocoBaño);
        AfocoCuarto = (LinearLayout) findViewById(R.id.AfocoCuarto);
        logoutAdmin = (Button) findViewById(R.id.logoutAdmin);
        openRegister = (FloatingActionButton) findViewById(R.id.openRegister);
        Acocina = (ImageButton) findViewById(R.id.Acocina);
        Asala = (ImageButton) findViewById(R.id.Asala);
        Abaño = (ImageButton) findViewById(R.id.Abaño);
        Acuarto = (ImageButton) findViewById(R.id.Acuarto);
    }

    public void changeStatus(String sala, String value) throws JSONException {

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.0.6:5000/spotlight/"+sala;
        JSONObject jsonbody = new JSONObject();
        jsonbody.put("status", value);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String time = response.getString("timeElapsed");
                    String status = response.getString("status");
                    if(status.equals("off")){
                        Toast.makeText(getApplicationContext(),""+time,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void enviar(View view) throws JSONException {
        if(view.getId()==Acocina.getId()){
            if(Acocina.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("kitchen","on");
                Acocina.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("kitchen","off");
                Acocina.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Asala.getId()){
            if(Asala.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("living_room","on");
                Asala.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("living_room","off");
                Asala.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Abaño.getId()){
            if(Abaño.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("bathroom","on");
                Abaño.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("bathroom","off");
                Abaño.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Acuarto.getId()){
            if(Acuarto.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatus("bedroom","on");
                Acuarto.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatus("bedroom","off");
                Acuarto.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }
    }

}
