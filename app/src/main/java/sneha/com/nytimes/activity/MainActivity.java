package sneha.com.nytimes.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sneha.com.nytimes.R;
import sneha.com.nytimes.adapter.NewsListAdapter;
import sneha.com.nytimes.api.ApiClient;
import sneha.com.nytimes.api.MostPopularListAPI;
import sneha.com.nytimes.models.MostPopularResponse;
import sneha.com.nytimes.models.Result;


public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeNewsList;
    private RecyclerView recyclerListNews;
    private LinearLayout linearProgressBar;
    private LinearLayoutManager layoutManager;
    private NewsListAdapter newsListAdapter;
    private ArrayList<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearProgressBar = findViewById(R.id.linearProgressBar);
        swipeNewsList = findViewById(R.id.swipeNewsList);
        recyclerListNews = findViewById(R.id.recyclerListNews);
        swipeNewsList.setColorSchemeResources(R.color.colorAccent);
        CallApi();

        swipeNewsList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallApi();
            }
        });
    }

    public void CallApi() {

        MostPopularListAPI mostPopularListAPI = ApiClient.getClient().create(MostPopularListAPI.class);
        Call<MostPopularResponse> mostPopularResponseCall = mostPopularListAPI.getData("7", "all-sections");

        mostPopularResponseCall.enqueue(new Callback<MostPopularResponse>() {
            @Override
            public void onResponse(Call<MostPopularResponse> call, Response<MostPopularResponse> response) {
                linearProgressBar.setVisibility(View.GONE);
                swipeNewsList.setVisibility(View.VISIBLE);
                swipeNewsList.setRefreshing(false);
                if (response != null) {
                    results = response.body().getResults();

                    layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    newsListAdapter = new NewsListAdapter(getApplicationContext(), results);
                    recyclerListNews.setLayoutManager(layoutManager);
                    recyclerListNews.setAdapter(newsListAdapter);
                }
            }

            @Override
            public void onFailure(Call<MostPopularResponse> call, Throwable t) {
                t.printStackTrace();
                linearProgressBar.setVisibility(View.GONE);
            }
        });
    }

}
