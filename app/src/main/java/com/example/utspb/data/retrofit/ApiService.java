package com.example.utspb.data.retrofit;

import com.example.utspb.data.response.GithubSearch;
import com.example.utspb.data.response.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token "})
    @GET("search/users")
    Call<GithubSearch> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token "})
    @GET("users/{username}")
    Call<Users> getUser(@Path("username") String username);


}