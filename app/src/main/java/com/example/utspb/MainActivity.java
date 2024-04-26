package com.example.utspb;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspb.data.response.GithubSearch;
import com.example.utspb.data.response.Users;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import com.example.utspb.ui.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubSearch> call = apiService.searchUsers("rey");

        call.enqueue(new Callback<GithubSearch>() {
            @Override
            public void onResponse(Call<GithubSearch> call, Response<GithubSearch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Users> users = response.body().getUsers();
                    adapter = new UserAdapter(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubSearch> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}