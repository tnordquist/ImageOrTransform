package edu.cnm.deepdive.ironorimgtransform.controller;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import edu.cnm.deepdive.ironorimgtransform.service.TransformOperation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
    OnMenuItemClickListener,
    TransformPickerDialogFragment.BitmapAccess{

  private ImageButton menuButton;
  private List<Transform> transforms;
  private ImageView transformingImage;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new TransformListQuery().execute();
    Button transformButton = findViewById(R.id.transform_button);
    transformingImage = findViewById(R.id.transforming_image);
    transformButton.setOnClickListener((v) -> showPopup(v));
  }

  public void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    // TODO read from database instead of inflating.
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.transform_options, popup.getMenu());

    for (Transform transform : transforms) {

      popup.getMenu()
          .add(transform.getName())
          .setOnMenuItemClickListener((item) -> {
            try {
              Class<? extends TransformOperation> clazz =
                  (Class<? extends TransformOperation>) getClass().getClassLoader().loadClass(transform.getClazz());
              TransformOperation operation = clazz.newInstance();
              DialogFragment dialogFragment = TransformPickerDialogFragment.newInstance(operation);
              dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
              return true;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
              e.printStackTrace();
              return false;
            }
          });
    }

    popup.setOnMenuItemClickListener(menuItem -> {
      showNoticeDialog("Transform1");
      return true;
    });
    popup.show();
  }

  public void showNoticeDialog(String str) {
    // Create an instance of the dialog fragment and show it.
    DialogFragment dialogFragment = new TransformPickerDialogFragment();
    dialogFragment.show(getSupportFragmentManager(), "Notice Dialog Fragment");
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    return false;
  }

  @Override
  public Bitmap getBitmap() {
    return ((BitmapDrawable) transformingImage.getDrawable()).getBitmap();
  }

  @Override
  public void setBitmap(Bitmap bitmap) {
    transformingImage.setImageBitmap(bitmap);
  }


  private class TransformListQuery extends
      AsyncTask<Void, Void, List<Transform>> {


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
