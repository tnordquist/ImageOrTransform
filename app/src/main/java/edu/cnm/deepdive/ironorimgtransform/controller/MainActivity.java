package edu.cnm.deepdive.ironorimgtransform.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.controller.TransformFragment;

public class MainActivity extends AppCompatActivity {

  private TextView numberView;
  private Button transformButton;
  private Button imageButton;
  private TextView transformView;
  private TextView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    transformButton = findViewById(R.id.transform_button);
    imageButton = findViewById(R.id.image_button);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if (fragment == null) {
      fragment = new TransformFragment();
      fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

  }

}
