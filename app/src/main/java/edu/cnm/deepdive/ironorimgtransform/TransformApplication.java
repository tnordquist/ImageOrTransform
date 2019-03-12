package edu.cnm.deepdive.ironorimgtransform;

import android.app.Application;
import android.os.AsyncTask;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;

/**
 * Extends {@link Application}, in order to initialize <a href="http://facebook.github.io/stetho/">Stetho</a>
 * inspection and set up access to this instance via the singleton pattern. At runtime, any instance
 * of an {@link Application} subclass is a de facto singleton; the common {@link #getInstance()}
 * implementation is used here to enable access to the singleton by other classes in the app.
 */
public class TransformApplication extends Application {

  private static TransformApplication instance = null;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this); // Comment out this line to disable Stetho.
    new AsyncTask<Void, Void, Void>(){
      @Override
      protected Void doInBackground(Void... voids) {
        TransformDB.getInstance().getTransformDao().findAll();
        return null;
      }
    }
    .execute();
  }

  /**
   * Returns this instance, for access to application context across the app.
   *
   * @return singleton instance.
   */
  public static TransformApplication getInstance() {
    return instance;
  }

}