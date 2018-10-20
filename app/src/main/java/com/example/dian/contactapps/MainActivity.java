package com.example.dian.contactapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dian.contactapps.Entity.ProfileArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listViewProfil;
    ListAdapter listAdapter;
    ArrayList<ProfileArray> itemsProfile = new ArrayList<ProfileArray>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsProfile.clear();

        listViewProfil=(ListView)findViewById(R.id.listViewProfil);
        listAdapter=new ListAdapter(getApplicationContext(),itemsProfile);

        listViewProfil.setAdapter(listAdapter);

        setData();

    }

    private void setData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constan.UrlGet,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {


                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            JSONArray jsob = jsonObj.getJSONArray("data");
                            for(int i = 0;i<jsob.length();i++){
                                ProfileArray profileArray = new ProfileArray();
                                Integer id = jsob.getJSONObject(i).getInt("id");
                                String firstname = jsob.getJSONObject(i).getString("firstname");
                                String lastname = jsob.getJSONObject(i).getString("lastname");
                                Boolean isfavorite = jsob.getJSONObject(i).getBoolean("isfavorite");
                                profileArray.setId(id);
                                profileArray.setFirstname(firstname);
                                profileArray.setLastname(lastname);
                                profileArray.setIsfavorite(isfavorite);
                                profileArray.setAvatar(jsob.getJSONObject(i).getString("imageurl"));

                                itemsProfile.add(profileArray);
                                listAdapter.notifyDataSetChanged();

                                Log.i("Data",s);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters

                //returning parameters
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        /*for (int i=0;i<10;i++) {
            ProfileArray profileArray = new ProfileArray();
            profileArray.setFirstname("Dian "+String.valueOf(i));
            profileArray.setAvatar("https://www.shareicon.net/data/512x512/2016/09/15/829459_man_512x512.png");

            itemsProfile.add(profileArray);
            listAdapter.notifyDataSetChanged();
        }*/
    }
}
