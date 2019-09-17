package com.example.repossearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.repossearch.adapter.DataAdapter;
import com.example.repossearch.model.Data;
import com.example.repossearch.remote.ApiService;
import com.example.repossearch.remote.ApiUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText ed1;
    private RecyclerView rc;
    private Button b_search;
    private TextView tv_count;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiUtil.getDataService();

        ed1 = findViewById(R.id.ed_name);
        rc  = findViewById(R.id.rcr);
        b_search = findViewById(R.id.b_s);
        tv_count = findViewById(R.id.count);

        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = ed1.getText().toString();

                SearchRepo(name);

            }
        });

    }

    public void SearchRepo(String name){

        Call<ArrayList<Data>> call = apiService.getData(name);
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {

                if (response.isSuccessful()){
                    DataAdapter dataAdapter = new DataAdapter(MainActivity.this, response.body());
                    rc.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rc.setAdapter(dataAdapter);

                    tv_count.setText("Repesitories Found (" +String.valueOf(dataAdapter.getItemCount())+")");

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                Log.e("Error", t.getMessage() );
            }
        });

    }

}
