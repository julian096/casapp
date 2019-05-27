package com.example.casapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User extends AppCompatActivity {
    TextView etiUser;
    RequestQueue requestQueue;
    Button logoutUser;
    LinearLayout focoCocina, focoSala, focoBaño, focoCuarto;
    ImageButton Ucocina, Usala, Ubaño, Ucuarto;
    ArrayList<String> cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initElem();

        focoCocina.setVisibility(View.INVISIBLE);
        focoSala.setVisibility(View.INVISIBLE);
        focoBaño.setVisibility(View.INVISIBLE);
        focoCuarto.setVisibility(View.INVISIBLE);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        String name = b.getString("user");
        String spotlights = b.getString("spotlights");
        String arraySpotlights[] = spotlights.split(",");
        etiUser.setText(name);

        Toast.makeText(getApplicationContext(),spotlights,Toast.LENGTH_SHORT).show();

        for(int i=0; i<arraySpotlights.length; i++){
            if(arraySpotlights[i].equals("kitchen")){
                if(!arraySpotlights[i+1].equals("off")){
                    Ucuarto.setImageResource(R.drawable.focoon);
                }
                focoCocina.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("living_room")){
                if(!arraySpotlights[i+1].equals("off")){
                    Usala.setImageResource(R.drawable.focoon);
                }
                focoSala.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("bathroom")){
                if(!arraySpotlights[i+1].equals("off")){
                    Ubaño.setImageResource(R.drawable.focoon);
                }
                focoBaño.setVisibility(View.VISIBLE);
            }
            if(arraySpotlights[i].equals("bedroom")){
                if(!arraySpotlights[i+1].equals("off")){
                    Ucuarto.setImageResource(R.drawable.focoon);
                }
                focoCuarto.setVisibility(View.VISIBLE);
            }
        }

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(logout);
            }
        });


    }

    public void initElem(){
        focoCocina = (LinearLayout) findViewById(R.id.focoCocina);
        focoSala = (LinearLayout) findViewById(R.id.focoSala);
        focoBaño = (LinearLayout) findViewById(R.id.focoBaño);
        focoCuarto = (LinearLayout) findViewById(R.id.focoCuarto);
        logoutUser = (Button) findViewById(R.id.logoutUser);
        etiUser = (TextView) findViewById(R.id.etiUser);
        Ucocina = (ImageButton) findViewById(R.id.Ucocina);
        Usala = (ImageButton) findViewById(R.id.Usala);
        Ubaño = (ImageButton) findViewById(R.id.Ubaño);
        Ucuarto = (ImageButton) findViewById(R.id.Ucuarto);
    }

    public void changeStatusFoco(String sala, String value) throws JSONException {

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
        if(view.getId()==Ucocina.getId()){
            if(Ucocina.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatusFoco("kitchen","on");
                Ucocina.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatusFoco("kitchen","off");
                Ucocina.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Usala.getId()){
            if(Usala.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatusFoco("living_room","on");
                Usala.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatusFoco("living_room","off");
                Usala.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Ubaño.getId()){
            if(Ubaño.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatusFoco("bathroom","on");
                Ubaño.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatusFoco("bathroom","off");
                Ubaño.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }else if(view.getId()==Ucuarto.getId()){
            if(Ucuarto.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.focooff).getConstantState())){
                changeStatusFoco("bedroom","on");
                Ucuarto.setImageResource(R.drawable.focoon);
                //metodo status on
            }else{
                changeStatusFoco("bedroom","off");
                Ucuarto.setImageResource(R.drawable.focooff);
                //metodo status off
            }
        }

    }


}
