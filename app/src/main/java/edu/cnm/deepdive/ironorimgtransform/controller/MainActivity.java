package edu.cnm.deepdive.ironorimgtransform.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.util.Log;
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
import edu.cnm.deepdive.ironorimgtransform.service.Utility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import edu.cnm.deepdive.ironorimgtransform.service.GoogleSignInService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MainActivity extends AppCompatActivity implements
    OnMenuItemClickListener,
    TransformPickerDialogFragment.BitmapAccess {

  private static final int SELECT_FILE = 1;
  private static final int REQUEST_CAMERA = 2;
  private ImageButton menuButton;
  private List<Transform> transforms;
  private ImageView transformingImage;
  private String userChosenTask;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try (
        InputStream input = getResources().openRawResource(R.raw.transforms);
        Reader reader = new InputStreamReader(input);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)
    ) {
      for (CSVRecord record : parser) {
        String col0 = record.get(0);
        String col1 = record.get(1);
        String col2 = record.get(2);
        // TODO Create entity instances, invoke setters to set fields from CSV
        // data, use DAOs to write entity instances to DB(best if DAO provides
        // an insert(List) form).
      }
    } catch (IOException e) {
      Log.e("Something went wrong!", getClass().getSimpleName());
    }

    setContentView(R.layout.activity_main);
    new TransformListQuery().execute();
    Button transformButton = findViewById(R.id.transform_button);
    transformingImage = findViewById(R.id.transforming_image);
    transformButton.setOnClickListener((v) -> showPopup(v));
    Button image = findViewById(R.id.image_button);
    image.setOnClickListener((v) -> selectImage());
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
                  (Class<? extends TransformOperation>) getClass()
                      .getClassLoader().loadClass(transform.getClazz());
              TransformOperation operation = clazz.newInstance();
              DialogFragment dialogFragment = TransformPickerDialogFragment
                  .newInstance(operation);
              dialogFragment.show(getSupportFragmentManager(),
                  dialogFragment.getClass().getSimpleName());
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

  public MainActivity() {
    super();
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

  private void selectImage() {
    final CharSequence[] items = { "Take Photo", "Choose from Library",
        "Cancel" };

    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("Add Photo!");
    builder.setItems(items, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int item) {
        boolean result= Utility.checkPermission(MainActivity.this);

        if (items[item].equals("Take Photo")) {
          userChosenTask="Take Photo";
          if(result)
            cameraIntent();

        } else if (items[item].equals("Choose from Library")) {
          userChosenTask="Choose from Library";
          if(result)
            galleryIntent();

        } else if (items[item].equals("Cancel")) {
          dialog.dismiss();
        }
      }
    });
    builder.show();
  } // end selectImage()

  private void cameraIntent()
  {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, REQUEST_CAMERA);
  }

  private void galleryIntent()
  {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);//
    startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          if(userChosenTask.equals("Take Photo"))
            cameraIntent();
          else if(userChosenTask.equals("Choose from Library"))
            galleryIntent();
        } else {
          //code for deny
        }
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == SELECT_FILE)
        onSelectFromGalleryResult(data);
      else if (requestCode == REQUEST_CAMERA)
        onCaptureImageResult(data);
    }
  }


  private void onSelectFromGalleryResult(Intent data) {
    Bitmap bm=null;
    if (data != null) {
      try {
        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    transformingImage.setImageBitmap(bm);
  }

  private void onCaptureImageResult(Intent data) {
    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
    File destination = new File(Environment.getExternalStorageDirectory(),
        System.currentTimeMillis() + ".jpg");
    FileOutputStream fo;
    try {
      destination.createNewFile();
      fo = new FileOutputStream(destination);
      fo.write(bytes.toByteArray());
      fo.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    transformingImage.setImageBitmap(thumbnail); // Do I need an ImageView? Should I match AppGuruz layout?
  }

  private void signOut() {
    GoogleSignInService.getInstance().getClient().signOut()
        .addOnCompleteListener(this, (task) -> {
          GoogleSignInService.getInstance().setAccount(null);
          Intent intent = new Intent(this, LoginActivity.class);
          intent.addFlags(
              Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }


}
