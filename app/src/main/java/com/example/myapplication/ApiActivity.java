package com.example.myapplication;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class ApiActivity extends AppCompatActivity {
    private RecyclerView articleRecyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        articleRecyclerView = findViewById(R.id.articleRecyclerView);
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FetchArticlesTask fetchArticlesTask = new FetchArticlesTask();
        fetchArticlesTask.execute();
    }

    private class FetchArticlesTask extends AsyncTask<Void, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(Void... voids) {
            String url = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=15Dc5M08AtUFj30VlW0vObWWnuR3S3Il";
            List<Article> articles = new ArrayList<>();

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject responseObject = new JSONObject(response.toString());
                    JSONArray resultsArray = responseObject.getJSONArray("results");

                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject articleObject = resultsArray.getJSONObject(i);
                        String title = articleObject.getString("title");
                        String abstractText = articleObject.getString("abstract");

                        Article article = new Article(title, abstractText);
                        articles.add(article);
                    }
                }

                connection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            articleAdapter = new ArticleAdapter(articles);
            articleRecyclerView.setAdapter(articleAdapter);
        }
    }
}