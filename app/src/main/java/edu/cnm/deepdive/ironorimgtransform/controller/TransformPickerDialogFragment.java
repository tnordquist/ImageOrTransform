package edu.cnm.deepdive.ironorimgtransform.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.service.TransformOperation;

public class TransformPickerDialogFragment extends DialogFragment {

  private static final String TRANSFORM_OPERATION_KEY = "transform";
  private static final String TRANSFORM_ID_KEY = "transformId";
  // Use this instance of the interface to deliver action events
  private BitmapAccess access;

  private TransformOperation operation;

  private EditText x; // TODO and. ch7 wiring up widget: do I do this?

  private SharedPreferences getSharedPreferences;
  private SharedPreferences preferences;
  private static final String PROGRESS = "SEEKBAR";
  private long save;


  public static TransformPickerDialogFragment newInstance(
      TransformOperation operation, long transformId) {
    Bundle args = new Bundle();
    args.putSerializable(TRANSFORM_OPERATION_KEY, operation);
    args.putLong(TRANSFORM_ID_KEY, transformId);
    TransformPickerDialogFragment fragment = new TransformPickerDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    operation = (TransformOperation) getArguments()
        .getSerializable(TRANSFORM_OPERATION_KEY);

  }

  // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    access = (BitmapAccess) context;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    //Get the layout inflater
    LayoutInflater inflater = requireActivity().getLayoutInflater();
    //Inflate and set the layout for the dialog
    //pass null as the parent view because it is going on the dialog layout.
    View view = inflater.inflate(operation.getLayout(), null);

    preferences = getActivity().getSharedPreferences(" ", MODE_PRIVATE);
    float progress = preferences.getFloat("Blur", -1);
    SeekBar standardDeviation = view.findViewById(R.id.standard_deviation);
    standardDeviation.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // TODO Add number intervals to seekbar
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    if (progress >= 0) {
      standardDeviation.setProgress(Math.round(progress));
    }
    builder
        .setView(view)

        // Add action buttons
        .setPositiveButton(R.string.transforms_on, (dialog, which) -> {
          Bitmap result = operation.transform(access.getBitmap(), view);
          // TODO Update database, local storage.
          access.setBitmap(result, getArguments().getLong(TRANSFORM_ID_KEY));
          float stDev = standardDeviation.getProgress();

          preferences = getActivity().getSharedPreferences(" ", MODE_PRIVATE);
          final SharedPreferences.Editor editor = preferences.edit();
          editor.putFloat("Blur", stDev);
          editor.apply();
          standardDeviation.setProgress(preferences.getInt(PROGRESS, 0));
        })
        .setNegativeButton(R.string.transforms_cancel,
            (dialog, which) -> {
            });
    return builder.create();
  }

  /**
   * The activity that creates an instance of this dialog fragment must implement this interface in
   * order to receive event callbacks. Each method passes the DialogFragment in case the host needs
   * to query it.
   */
  public interface BitmapAccess {

    Bitmap getBitmap();

    void setBitmap(Bitmap bitmap, long aLong);
  }

}
