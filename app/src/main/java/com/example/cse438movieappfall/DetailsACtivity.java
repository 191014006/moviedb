package com.example.cse438movieappfall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsACtivity extends AppCompatActivity {

    Result result;
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        result = (Result) getIntent().getSerializableExtra("result");

        RecyclerView rv = findViewById(R.id.movie_list_view);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));


        rv.setAdapter(new DetailsACtivity.MyAdapter());

        try {
            String data = run("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=1&api_key=<<api_key>&gt;");
            result = new  Gson().fromJson(data, (Type) PopularMovieResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends RecyclerView.Adapter<DetailsACtivity.MovieViewHolder> {

        @NonNull
        @Override
        public DetailsACtivity.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(DetailsACtivity.this)
                    .inflate(R.layout.list_item, parent, false);

            return new DetailsACtivity.MovieViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.MovieViewHolder holder, int position) {
            holder.textView.setText(result.getResults().get(position).getTitle());
            holder.rating.setText(""+result.getResults().get(position).getVoteAverage());

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/w500"+resultgetResults().get(position).getPosterPath())
                    .centerCrop()
                    .into(holder.flagImg);


         
        }

        private CircularArray<Object> resultgetResults() {
        }

        @Override
        public int getItemCount() {
            return result.getResults().size();
        }
    }

        class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView flagImg1;
        ImageView flagImg2;
        TextView rating;
        TextView textView;
        ImageView flagImg;
        TextView textView1;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            flagImg1 = itemView.findViewById(R.id.poster_back);
            flagImg2 = itemView.findViewById(R.id.poster_font);
            rating = itemView.findViewById(R.id.rating1);
            textView = itemView.findViewById(R.id.text_des);
            flagImg = itemView.findViewById(R.id.poster_rc);
            textView = itemView.findViewById(R.id.title1);
        }
    }
        

 
}

  