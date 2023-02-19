package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.adapter.PostAdapter;
import com.example.retrofit.api.APIInterface;
import com.example.retrofit.api.RetrofitObj;
import com.example.retrofit.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        fetchData();

    }

    private void setAdapter(List<PostModel> postModelList) {
        rvMain.setAdapter(new PostAdapter(postModelList));
    }

    private void fetchData() {
        Retrofit retrofitObj = RetrofitObj.getRetrofitInstance();
        APIInterface apiInterface = retrofitObj.create(APIInterface.class);

        Call<List<PostModel>> postResponse = apiInterface.getAllPosts();
        postResponse.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    setAdapter(response.body());

                } else {
                    Toast.makeText(MainActivity.this, "something wrong, failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something wrong, failed", Toast.LENGTH_SHORT).show();


            }
        });


    }

}