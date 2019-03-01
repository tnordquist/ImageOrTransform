package edu.cnm.deepdive.ironorimgtransform;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This class is a controller
 */

public class TransformFragment extends Fragment {

    private Transform transform;
    private Button button1;
    private Button button2;
    private Button button3;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    transform = new Transform();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_transform, container,false);

    button1 = (Button) view.findViewById(R.id.transform_1);
    button1.setEnabled(false);

    button2 = (Button) view.findViewById(R.id.transform_2);
    button2.setEnabled(false);

    button3 = (Button) view.findViewById(R.id.transform_3);
    button3.setEnabled(false);

    return view;


  }
}
