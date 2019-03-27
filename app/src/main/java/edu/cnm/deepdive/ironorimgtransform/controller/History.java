package edu.cnm.deepdive.ironorimgtransform.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import edu.cnm.deepdive.android.BaseFluentAsyncTask.ResultListener;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.service.TransformDBService.GetHistoryTask;
import edu.cnm.deepdive.ironorimgtransform.view.HistoryAdapter;
import java.util.List;

/**
 * This {@link android.app.Activity} exists to make a {@link android.view.View} needed to display
 * the {@link RecyclerView} for displaying the history of user transformed images.
 */
public class History extends AppCompatActivity {

  RecyclerView recyclerview;
  RecyclerView.Adapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.history);

    recyclerview = findViewById(R.id.recycler_view);

    new GetHistoryTask().setSuccessListener(new ResultListener<List<Image>>() {
      @Override
      public void handle(List<Image> images) {
        recyclerview.setLayoutManager(new LinearLayoutManager(History.this));
        adapter = new HistoryAdapter(images);
        recyclerview.setAdapter(adapter);
      }
    }).execute();


  }
}
