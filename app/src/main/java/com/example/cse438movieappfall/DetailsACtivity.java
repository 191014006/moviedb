package com.example.cse438movieappfall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

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