package edu.cnm.deepdive.ironorimgtransform.service;

import android.graphics.Bitmap;
import android.view.View;
import java.io.Serializable;

public class TransformOperation implements Serializable {

  private static final long serialVersionUID = 2455031342366739846L;

  public Bitmap transform(Bitmap src, View view) {

    return src;

  }

  public int getLayout() {

    return 0;
  }

}
