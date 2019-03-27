package edu.cnm.deepdive.ironorimgtransform.controller;

import android.app.Activity;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import edu.cnm.deepdive.android.BaseFluentAsyncTask;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import edu.cnm.deepdive.ironorimgtransform.service.TransformOperation;
import edu.cnm.deepdive.ironorimgtransform.service.Utility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import edu.cnm.deepdive.ironorimgtransform.service.GoogleSignInService;

/**
 * Primary controller class of the ImageOrTransform app. This activity configures and then responds
 * to clicks on button views at the bottom of the view screen one to display a pop up menu giving
 * option to activate an image transforming algorithm. The other button view at the bottom of the
 * screen responds to a click by displaying an alert dialog which gives the user the option to
 * choose an image stored in the gallery or to use the camera to generate an image to be
 * transformed.  A click of the third button, at the top of the screen, opens an activity which
 * displays links to each image that has been transformed by the user.
 */
public class MainActivity extends AppCompatActivity implements
    OnMenuItemClickListener,
    TransformPickerDialogFragment.BitmapAccess {

  /**
   * This field set when user chooses to access an image file.
   */
  private static final int SELECT_FILE = 1;

  /**
   * This field set when user chooses to access the camera.
   */
  private static final int REQUEST_CAMERA = 2;

  /**
   * List which holds instances of image transform algorithms.
   */
  private List<Transform> transforms;

  /**
   * The view used to display the popup menu of image transform types.
   */
  private ImageView transformingImage;

  /**
   * String value which indicates whether the {@link #galleryIntent()} or the {@link
   * #cameraIntent()} is to be invoked.
   */
  private String userChosenTask;

  /**
   * Used to create an instance of the {@link TransformDB} object.
   */
  private static TransformDB transformDB;

  /**
   * Used to create a button instance for click accessing a user's history of image transforms.
   */
  private static Button imgHistory;

  /**
   * Handles a click on a button which starts the {@link History} activity which sets into motion
   * the set up of the {@link android.support.v7.widget.RecyclerView} for displaying a list of the
   * images transformed.  Listeners are also set on the transform menu button and the image
   * selection button.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    transformDB = TransformDB.getInstance();
    new TransformListQuery().execute();
    Button transformButton = findViewById(R.id.transform_button);
    transformingImage = findViewById(R.id.transforming_image);
    transformButton.setOnClickListener((v) -> showPopup(v));
    Button image = findViewById(R.id.image_button);
    image.setOnClickListener((v) -> selectImage());

    imgHistory = findViewById(R.id.history_button);
    imgHistory.setOnClickListener(view -> {
      startActivity(new Intent(MainActivity.this, History.class));
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  /**
   * Sets the popup menu to display choices of transform operations.  Inflates the chosen {@link
   * TransformPickerDialogFragment} alert dialog so that user can enter information needed for the
   * chosen transform type.
   *
   * @param v current {@link View} instance of popup menu.
   */
  private void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    // TODO read from database instead of inflating.
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.transform_options, popup.getMenu());

    for (Transform transform : transforms) {

      popup.getMenu()
          .add(transform.getName())
          .setOnMenuItemClickListener((item) -> {
            try {
              if (transform.getClazz()!=null) {
                Class<? extends TransformOperation> clazz =
                    (Class<? extends TransformOperation>) getClass()
                        .getClassLoader().loadClass(transform.getClazz());
                TransformOperation operation = clazz.newInstance();
                DialogFragment dialogFragment = TransformPickerDialogFragment
                    .newInstance(operation, transform.getId());
                dialogFragment.show(getSupportFragmentManager(),
                    dialogFragment.getClass().getSimpleName());
              }
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

  /**
   * Create an instance of the dialog fragment and show it.
   */
  public void showNoticeDialog(String str) {

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

  /**
   * This method makes it easy to load a bitmap of arbitrarily large size into a file.  This method
   * is also used to set data regarding the {@link Image} entity into the data base.
   *
   * @param bitmap bitmap to be compressed and stored in the gallery file and in the database.
   * @param transId the id of the type of transform applied to the image.
   */
  @Override
  public void setBitmap(Bitmap bitmap, long transId) {
    transformingImage.setImageBitmap(bitmap);

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
    }
    File destination = new File(getExternalFilesDir(null),
        System.currentTimeMillis() + ".jpg");
    FileOutputStream fo;
    try {
      destination.createNewFile();
      destination.toURL();
      fo = new FileOutputStream(destination);
      fo.write(bytes.toByteArray());
      fo.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image image = new Image();
    image.setTimestamp(new Date());
    image.setInternalURL(destination.toString());
    image.setTransformId(transId);
    new BaseFluentAsyncTask<Void, Void, Long, Long>()
        .setPerformer((ignore) -> transformDB.getImageDao().insert(image))
        .execute();
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

  /**
   * Alert dialog which invokes {@link #galleryIntent()} or {@link #cameraIntent()} or cancels the
   * dialog depending on the choice of the user.
   */
  private void selectImage() {
    final CharSequence[] items = {"Take Photo", "Choose from Library",
        "Cancel"};

    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("Add Photo!");
    builder.setItems(items, (dialog, item) -> {
      boolean result = Utility.checkPermission(MainActivity.this);

      if (items[item].equals("Take Photo")) {
        userChosenTask = "Take Photo";
        if (result) {
          cameraIntent();
        }

      } else if (items[item].equals("Choose from Library")) {
        userChosenTask = "Choose from Library";
        if (result) {
          galleryIntent();
        }

      } else if (items[item].equals("Cancel")) {
        dialog.dismiss();
      }
    });
    builder.show();
  } // end selectImage()

  /**
   * This method, when invoked, activates an intent which provides access to the camera.
   */
  private void cameraIntent() {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, REQUEST_CAMERA);
  }

  /**
   * This method, when invoked, activates an intent which provides access to the image gallery
   * files.
   */
  private void galleryIntent() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);//
    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

  }

  /**
   * onRequestPermissionsResult() is inbuilt method which receives a callback of this dialog action
   * for particular activity from which checkPermssion() has been called.If permission has been
   * granted then the value of grantResults[0] would be PERMISSION_GRANTED. And if permission has
   * been revoked then the value of grantResults[0] would be PERMISSION_DENIED.
   *
   * Here, if permission has been granted, then the method for the specific {@link Intent} is
   * invoked according to the value of the userChosenTask variable.
   */
  @Override
  public void onRequestPermissionsResult(int requestCode,
      @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          if (userChosenTask.equals("Take Photo")) {
            cameraIntent();
          } else if (userChosenTask.equals("Choose from Library")) {
            galleryIntent();
          }
        } else {
          //code for deny
        }
        break;
    }
  }

  /**
   * The image either from camera or from gallery, is handled here. Handle the result received by
   * calling startActivityForResult() Method.
   *
   * @param requestCode The request code is what has been passed to startActivityForResult(). In
   * this case it is REQUEST_CAMERA.
   * @param resultCode RESULT_OK if the operation was successful or RESULT_CANCEL if the operation
   * was somehow cancelled or unsuccessful.
   * @param data The intent carries the result data – in this case it is the image captured from the
   * camera.
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == SELECT_FILE) {
        onSelectFromGalleryResult(data);
      } else if (requestCode == REQUEST_CAMERA) {
        onCaptureImageResult(data);
      }
    }
  }

  /**
   * Sets a Bitmap as the content of this ImageView when the user has chosen to select a file from
   * the gallery.
   *
   * @param data Intent passed to this method when invoked in {@link #onActivityResult(int, int,
   * Intent)} containing information regarding the particular image file chosen.
   */
  private void onSelectFromGalleryResult(Intent data) {
    Bitmap bm = null;
    if (data != null) {
      try {
        bm = MediaStore.Images.Media
            .getBitmap(getApplicationContext().getContentResolver(), data.getData());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    transformingImage.setImageBitmap(bm);
  }


  private void onCaptureImageResult(Intent data) {
    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    thumbnail.compress(Bitmap.CompressFormat.JPEG, 75, bytes);
    File destination = new File(Environment.getExternalStorageDirectory(),
        System.currentTimeMillis() + ".jpg");
    FileOutputStream fo;
    try {
      destination.createNewFile();
      destination.toURL();
      Image image = new Image();
      image.setInternalURL(destination.toString());
      transformDB.getImageDao().insert(image);
      fo = new FileOutputStream(destination);
      fo.write(bytes.toByteArray());
      fo.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    transformingImage
        .setImageBitmap(thumbnail);
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
