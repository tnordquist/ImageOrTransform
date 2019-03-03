package edu.cnm.deepdive.ironorimgtransform;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.cnm.deepdive.ironorimgtransform.controller.TransformFragment;

public class SpinnerActivity extends TransformFragment implements
    OnItemSelectedListener {


  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position,
      long id) {

    // An item was selected. You can retrieve the selected item using
    // parent.getItemAtPosition(pos)
    spinner.setOnItemSelectedListener(this);
    spinner.getItemAtPosition(position);
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

    // Another interface callback

  }

}
