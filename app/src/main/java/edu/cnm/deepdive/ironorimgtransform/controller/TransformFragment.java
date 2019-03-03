package edu.cnm.deepdive.ironorimgtransform.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.Transform;

/**
 * This class is a controller
 */

public class TransformFragment extends Fragment {

  private Transform transform;
  private Button on;
  private Button cancel;
  public Spinner spinner;

  Transform1 transform1;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    transform = new Transform();

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.dialog_transform, container, false);

    spinner =  view.findViewById(R.id.transforms_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.transforms_choice, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
    spinner.setAdapter(adapter);

    transform1 = new Transform1();

    on = view.findViewById(R.id.on);
    on.setEnabled(false);

    cancel = view.findViewById(R.id.cancel);
    cancel.setEnabled(false);

    return view;


  }
}
