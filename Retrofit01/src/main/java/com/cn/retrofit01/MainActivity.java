package com.cn.retrofit01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {
    public final static String TAG="reborn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRetrofit();
    }

    private void testRetrofit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                test5();
            }
        }).start();
    }

    //简单的Get请求
    //https://api.github.com/users/octocat/repos
    public void test1(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService1 gitHubService=retrofit.create(GitHubService1.class);
        Call<List<Repo>> call=gitHubService.listRepos("octocat");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }

    //带参数的Get请求
    //https://api.github.com/users/octocat/repos?sort=desc
    public void test2(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService2 gitHubService=retrofit.create(GitHubService2.class);
        Call<List<Repo>> call=gitHubService.listRepos("octocat","desc");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }

    //带多个参数的Get请求
    //https://api.github.com/users/octocat/repos?sort=desc&page=1
    public void test3(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService3 gitHubService=retrofit.create(GitHubService3.class);
        HashMap<String,String> queryMap=new HashMap<>();
        queryMap.put("sort","desc");
        queryMap.put("page","1");
        Call<List<Repo>> call=gitHubService.listRepos("octocat",queryMap);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }

    //Post携带参数(方式一)
    public void test4(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://106.14.136.52:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService=retrofit.create(LoginService.class);
        Call<LoginResult> call=loginService.login1("andy","123456");
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.i(TAG,"onResponse "+response.body().getMessage());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }


    //Post携带参数(方式二)
    public void test5(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://106.14.136.52:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService=retrofit.create(LoginService.class);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username","andy");
        hashMap.put("password","123456");
        Call<LoginResult> call=loginService.login2(hashMap);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.i(TAG,"onResponse "+response.body().getMessage());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }


}
