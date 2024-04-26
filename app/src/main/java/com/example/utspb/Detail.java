package com.example.utspb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utspb.data.response.Users;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<Users> userCall = apiService.getUser(username);

            TextView textView = findViewById(R.id.nama);
            TextView textView2 = findViewById(R.id.username);
            TextView textView3 = findViewById(R.id.bio);
            TextView textview4 = findViewById(R.id.email);
            TextView textview5 = findViewById(R.id.twitter);
            ImageView imageView = findViewById(R.id.gambar);

            showLoading(true);
            userCall.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        Users user = response.body();
                        if (user != null){
                            String name = "Name: " + user.getName();
                            String usernames = "@" +user.getUsername();
                            String bio = "Bio: " + user.getBio();
                            String email = "Email: " + user.getEmail();
                            String twitter = "Twitter: " + user.getTwitter();
                            String gambar = user.getAvatarUrl();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            textview4.setText(email);
                            textview5.setText(twitter);
                            Picasso.get().load(gambar).into(imageView);
                        }else {
                            Toast.makeText(Detail.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(Detail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}