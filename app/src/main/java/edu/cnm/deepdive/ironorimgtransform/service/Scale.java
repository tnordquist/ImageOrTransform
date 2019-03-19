package edu.cnm.deepdive.ironorimgtransform.service;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.SeekBar;
import edu.cnm.deepdive.ironorimgtransform.R;

public class Scale extends TransformOperation {

    public final int TRANS_DEV = 3;

//    @Override
//    public Bitmap transform(Bitmap src, View view) {
//      SeekBar standardDeviation = view.findViewById(R.id.standard_deviation);
//      int stDev = standardDeviation.getProgress();
//      stDev = stDev / 3;
//      Bitmap bitmap = super.transform(src, view);
//      //pixelsArr(bitmap);
//     // bitmap = fastblur(src, stDev, 10);
//      return bitmap;
//      // TODO query view to get values of ui controls and create new bitmap accordingly
//
//    }

//    @Override
//    public int getLayout() {
//      return R.layout.fragment_gaussian_blur;
//    }

    //  private int[][] pixelsArr(Bitmap src) {
    //
    //    int[][] pixelsArr = new int[src.getWidth()][src.getHeight()];
    //
    //    for (int row = 0; row < src.getHeight(); ++row) {
    //      src.getPixels(pixelsArr[row], 0, src.getWidth(), 0, row, src.getWidth(),
    //          1);
    //    }
    //    return pixelsArr;
    //  }
  }

