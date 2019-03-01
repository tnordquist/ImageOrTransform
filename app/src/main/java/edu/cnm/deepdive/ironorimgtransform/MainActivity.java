package edu.cnm.deepdive.ironorimgtransform;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private TextView numberView;
  private ToggleButton transformToggle;
  private ToggleButton imageToggle;
  private TextView transformView;
  private TextView imageView;
  private Random rng;
  private int value;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if (fragment == null) {
      fragment = new TransformFragment();
      fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

  }

}
