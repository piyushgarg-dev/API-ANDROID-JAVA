package dev.piyushgarg.jsonapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Gson gson = new Gson();
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);





    }
    public void sendApi(View v){
        //final Blog[] blogs = new Blog[100];
        final View view = v;
        final ArrayList<Blog> blogs= new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        String url = "https://randomblogs.herokuapp.com/api/posts";
        Snackbar.make(view,"Fetching Blogs",Snackbar.LENGTH_SHORT).show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("blog");
                    for(int i=0; i<jsonArray.length(); i++){
                      JSONObject blog = jsonArray.getJSONObject(i);
                        blogs.add(new Blog(blog.getString("id"),blog.getString("title"),blog.getString("body")));
                    }
                    adapter = new ExampleAdapter(blogs);
                    recyclerView.hasFixedSize();
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);


                    Snackbar.make(view,"Successfully Fetched",Snackbar.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Snackbar.make(view,"Error While Fetching",Snackbar.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        requestQueue.add(request);

    }

}
