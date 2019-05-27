package com.example.casapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity{
    Button login;
    EditText user, pass;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login(user.getText().toString(),pass.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login(final String user, final String pass) throws JSONException {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.0.6:5000/login";
        JSONObject jsonData = new JSONObject();
        jsonData.put("username", user);
        jsonData.put("password", pass);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,  jsonData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name = response.getString("message");
                    String salas = response.getString("spotlightsAndStatus");
                    if(name.equals("admin")){
                        Intent changeAdmin = new Intent(getApplicationContext(),Admin.class);
                        changeAdmin.putExtra("spotlights", salas);
                        finish();
                        startActivity(changeAdmin);
                    }else{
                        Intent changeUser = new Intent(getApplicationContext(),User.class);
                        changeUser.putExtra("user", name);
                        changeUser.putExtra("spotlights", salas);
                        finish();
                        startActivity(changeUser);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Usuario invalido",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
