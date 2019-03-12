package edu.cnm.deepdive.ironorimgtransform.service;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.SeekBar;
import edu.cnm.deepdive.ironorimgtransform.R;

public class GaussianBlur extends TransformOperation {

  @Override
  public Bitmap transform(Bitmap src, View view) {
    SeekBar standardDeviation = view.findViewById(R.id.standard_deviation);
    int stDev = standardDeviation.getProgress();
    Bitmap bitmap = super.transform(src, view);
    return bitmap;
    // TODO query view to get values of ui controls and create new bitmap accordingly
  }

  @Override
  public int getLayout() {
    return R.layout.fragment_gaussian_blur;
  }
}
