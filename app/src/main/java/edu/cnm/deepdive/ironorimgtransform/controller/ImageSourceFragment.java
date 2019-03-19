package edu.cnm.deepdive.ironorimgtransform.controller;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import edu.cnm.deepdive.ironorimgtransform.controller.TransformPickerDialogFragment.BitmapAccess;
import edu.cnm.deepdive.ironorimgtransform.service.TransformOperation;

public class ImageSourceFragment extends DialogFragment {

  private TransformOperation operation;
  private BitmapAccess access;
  private Object OnClickListener;

  public static ImageSourceFragment newInstance(TransformOperation operation) {
    ImageSourceFragment fragment = new ImageSourceFragment();
    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    access = (BitmapAccess) context;
  }

//  @NonNull
//  @Override
//  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//    AlertDialog.Builder builder = new Builder(getActivity());
//    LayoutInflater inflater = requireActivity().getLayoutInflater();
//
//    View view = inflater.inflate(operation.getLayout(), null);
//    builder.setView(view)
//        .setPositiveButton("Image", (dialog, which) -> {
//          Bitmap result = operation.transform(access.getBitmap(), view);
//          // TODO Update database, local storage.
//          access.setBitmap(result, getArguments().getLong(TRANSFORM_ID_KEY));
//        })
//        .setNegativeButton("Cancel", ((dialog, which) -> {
//
//        }));
//    builder.setTitle("The image");
//    return builder.create();
//  }

  public interface ImageAcquisition {

    void getImage(boolean camera);
  }
}
