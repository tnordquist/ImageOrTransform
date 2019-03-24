package edu.cnm.deepdive.ironorimgtransform.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

  private static final String TAG = "position";
  List<Image> userImages;

  public HistoryAdapter(List<Image> userImages) {
    this.userImages = userImages;
  }

  @NonNull
  @Override
  public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.image_row, parent, false);
    Log.d(TAG, "what is position?: " + viewType);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int position) {
    viewHolder.image_item.setText(userImages.get(position).getInternalURL());
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
