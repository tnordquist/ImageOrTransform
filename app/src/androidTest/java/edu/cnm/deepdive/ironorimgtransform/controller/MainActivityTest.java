package edu.cnm.deepdive.ironorimgtransform.controller;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import edu.cnm.deepdive.ironorimgtransform.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
      MainActivity.class);

  @Test
  public void transformMenu() {
    // Click on Navigatioin item
    onView(withId(R.id.transform_button))
        .perform(click());

    //Make sure fragment is loaded
    onView(withText("Stack Blur"))
        .check(matches(isDisplayed()));
  }

  @Test
  public void imageButtonLibrary() {
    // Click on Navigatioin item
    onView(withId(R.id.image_button))
        .perform(click());

    //Make sure fragment is loaded
    onView(withText("Choose from Library"))
        .check(matches(isDisplayed()));
  }

  @Test
  public void imageButtonCamera() {
    // Click on Navigatioin item
    onView(withId(R.id.image_button))
        .perform(click());

    //Make sure fragment is loaded
    onView(withText("Take Photo"))
        .check(matches(isDisplayed()));
  }

  @Test
  public void imageButtonCameraCancel() {
    // Click on Navigatioin item
    onView(withId(R.id.image_button))
        .perform(click());

    //Make sure fragment is loaded
    onView(withText("Cancel"))
        .check(matches(isDisplayed()));
  }

}
