package edu.cnm.deepdive.ironorimgtransform.controller;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import edu.cnm.deepdive.ironorimgtransform.R;

public class TransformPickerFragment extends DialogFragment {

  // Use this instance of the interface to deliver action events
  private NoticeDialogListener listener;

  // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    listener = (NoticeDialogListener) context;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    //Get the layout inflater
    LayoutInflater inflater = requireActivity().getLayoutInflater();

    //Inflate and set the layout for the dialog
    //pass null as the parent view because it is going on the dialog layout.
    builder
        .setView(inflater.inflate(R.layout.dialog_transform, null))

        // Add action buttons
        .setPositiveButton(R.string.transforms_on,
            (dialog, which) -> listener.onDialogPositiveClick(this))
        .setNegativeButton(R.string.transforms_cancel,
            (dialog, which) -> listener.onDialogNegativeClick(this));
    return builder.create();
  }

  /* The activity that creates an instance of this dialog fragment must
   * implement this interface in order to receive event callbacks.
   * Each method passes the DialogFragment in case the host needs to query it. */
  public interface NoticeDialogListener {

    void onDialogPositiveClick(DialogFragment dialog);

    void onDialogNegativeClick(DialogFragment dialog);
  }

}
