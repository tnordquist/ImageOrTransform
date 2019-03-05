package edu.cnm.deepdive.ironorimgtransform.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.ATransform;

/**
 * This class is a controller
 */

public class TransformFragment extends Fragment {

  private ATransform transform;
  private Button on;
  private Button cancel;
  public Spinner spinner;

  Transform1 transform1;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    transform = new ATransform();

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.dialog_transform, container, false);

    spinner = view.findViewById(R.id.transforms_spinner);

    transform1 = new Transform1();

    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner, getResources().getStringArray(R.array.transforms_choice));

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position,
          long id) {

        switch (position){
          case 1:
            break;
          case 2:
            break;
        }

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    on = view.findViewById(R.id.on);
    on.setEnabled(false);

    cancel = view.findViewById(R.id.cancel);
    cancel.setEnabled(false);

    return view;


  }

//  public void setFragment(Fragment fragment) {
//    FragmentTransaction fragmentTransaction = (FragmentManager) getSupportFragmentManager().beginTransaction();
//  }

}
