package com.example.qthjen.jsonarrayrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String url = "http://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        readJsonArray();
    }

    public void readJsonArray() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) { //jsonArrayRequest đọc jsonArray và lưu array vào response
                for ( int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject)response.get(i);
                        String khoahoc = jsonObject.getString("khoahoc");   // nhập key trong json
                        Integer hocphi = jsonObject.getInt("hocphi");

                        tv.append("Khoa hoc: " + khoahoc + "\n" + "Hoc phi: " + hocphi);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Error input key: " + e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
