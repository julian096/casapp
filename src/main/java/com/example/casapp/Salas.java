package com.example.casapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Salas extends AppCompatActivity{

    Button exit;
    ToggleButton f1,f2,f3,f4;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focos);

        f1 = (ToggleButton) findViewById(R.id.f1);
        f2 = (ToggleButton) findViewById(R.id.f2);
        f3 = (ToggleButton) findViewById(R.id.f3);
        f4 = (ToggleButton) findViewById(R.id.f4);
        exit = (Button) findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //Funcion para escuchar los botones
    public void cons(View view) throws JSONException {
        if(view.getId() == f1.getId()){
            if(f1.isChecked()){
                sendData("one","on");
            }else{
                sendData("one","off");
            }
        }else if(view.getId() == f2.getId()){
            if(f2.isChecked()){
                sendData("two","on");
            }else{
                sendData("two","off");
            }

        }else if(view.getId() == f3.getId()){
            if(f3.isChecked()){
                sendData("three","on");
            }else{
                sendData("three","off");
            }
        }else if(view.getId() == f4.getId()){
            if(f4.isChecked()){
                sendData("four","on");
            }else{
                sendData("four","off");
            }
        }
    }

    private void sendData(String foco, String value) throws JSONException {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "http://192.168.43.204:5000/spotlight/"+foco;
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("status", value);
        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Si jala", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
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
