package edu.cnm.deepdive.ironorimgtransform;

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
//  private int activeCorrect;
//  private int passiveCorrect;
//  private int incorrect;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //numberView = findViewById(R.id.number_view);
    transformToggle = findViewById(R.id.transform_toggle);
    imageToggle = findViewById(R.id.image_toggle);
    transformView = findViewById(R.id.transform_view);
    imageView = findViewById(R.id.current_view);
    rng = new Random();
    updateTallyDisplay();
  }

  private void updateTallyDisplay() {
    imageView.setText(getString(R.string.current_image));
    transformView.setText(getString(R.string.transform_name));
  }

//  private void updateView() {
//    value = 1 + rng.nextInt(UPPER_BOUND);
//    runOnUiThread(new Runnable() {
//      @Override
//      public void run() {
//        updateTallyDisplay();
//        numberView.setText(Integer.toString(value));
//        fizzToggle.setChecked(false);
//        buzzToggle.setChecked(false);
//      }
//    });
//  }

}
