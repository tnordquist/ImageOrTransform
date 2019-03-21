package edu.cnm.deepdive.ironorimgtransform.controller;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.ironorimgtransform.R;
import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
  private static final String TAG = "position";
  ArrayList<String> userImages;

  RecyclerView recyclerView;

  public HistoryAdapter(ArrayList<String> userImages) {
    this.userImages = userImages;
  }

  @NonNull
  @Override
  public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.image_row, recyclerView, false);
    Log.d(TAG, "what is position?: " + position);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int position) {
    viewHolder.image_item.setText(userImages.get(position));
  }

  @Override
  public int getItemCount() {

    return userImages.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView image_item;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      image_item = itemView.findViewById(R.id.image_history);
    }
  }
}
