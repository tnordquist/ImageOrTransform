package edu.cnm.deepdive.ironorimgtransform.controller;

import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.controller.TransformPickerFragment.NoticeDialogListener;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
    OnMenuItemClickListener, TransformPickerFragment.NoticeDialogListener {

  private ImageButton menuButton;
  List<Transform> transforms;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button transformButton = findViewById(R.id.transform_button);
    transformButton.setOnClickListener((v ) -> showPopup(v));
    confirmTransformPicker();
  }

  public void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    // TODO read from database instead of inflating.
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.transform_options, popup.getMenu());
    String[] choices = getResources().getStringArray(R.array.transforms_choice);
    for (String choice : choices) {
      MenuItem item = popup.getMenu().add(choice);
    }
//    for (Transform transform : transforms) {
//      popup.getMenu().add(Menu.NONE, (int) transform.getId(), Menu.NONE, transform.getName());
//    }
    popup.show();
  }

  public void showNoticeDialog() {
    // Create an instance of the dialog fragment and show it.
    DialogFragment dialogFragment = new TransformPickerFragment();
    dialogFragment.show(getSupportFragmentManager(), "Notice Dialog Fragment");
  }

  public void confirmTransformPicker() {
    DialogFragment newFragment = new TransformPickerFragment();
    newFragment.show(getSupportFragmentManager(), "transforms");
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {

    return false;
  }

  @Override
  public void onDialogPositiveClick(DialogFragment dialog) {

  }

  @Override
  public void onDialogNegativeClick(DialogFragment dialog) {

  }


  private class TransformListQuery extends AsyncTask<Void, Void, List<Transform>> {


    @Override
    protected List<Transform> doInBackground(Void... voids) {
      return TransformDB.getInstance().getTransformDao().findAll();
    }

    @Override
    protected void onPostExecute(List<Transform> transforms) {
      MainActivity.this.transforms = transforms;
    }

  }
}
