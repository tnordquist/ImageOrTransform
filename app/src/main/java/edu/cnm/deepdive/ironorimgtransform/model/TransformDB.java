package edu.cnm.deepdive.ironorimgtransform.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.ironorimgtransform.TransformApplication;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB.Converters;
import edu.cnm.deepdive.ironorimgtransform.model.dao.ImageDao;
import edu.cnm.deepdive.ironorimgtransform.model.dao.TransformDao;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import java.util.Date;
import java.util.concurrent.Executors;

/**
 * Defines the local database as a collection of its entities and converters, with the singleton
 * pattern implemented for app-wide use of a single connection, and declares methods to retrieve
 * data access objects (DAOs) for the database entities.
 */
@Database(
    entities = {Transform.class, Image.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters.class)

public abstract class TransformDB extends RoomDatabase {

  private static final String DB_NAME = "transform_db";

  /**
   * Returns the single instance of {@link TransformDB} for the current application context.
   *
   * @return single {@link TransformDB} instance reference.
   */
  public static TransformDB getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Returns an instance of a Room-generated implementation of {@link TransformDao}.
   *
   * @return data access object for CRUD operations involving {@link Transform} instances.
   */
  public abstract TransformDao getTransformDao();

  public abstract ImageDao getImageDao();


  private static class InstanceHolder {

    private static final TransformDB INSTANCE = Room.databaseBuilder(
        TransformApplication.getInstance().getApplicationContext(),
        TransformDB.class, DB_NAME)
        .addCallback(new Prepopulate())
        .build();

  }

  public static class Converters {

    /**
     * Converts an {@link Date} value containing the specific instant in time, with millisecond
     * precision..
     *
     * @param time local date as a number of days since the start of the Unix epoch.
     * @return time as a {@link Date} instance.
     */
    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    /**
     * Converts a {@link Date} local date value to a number of days since the start of the Unix
     * epoch (1970-01-01), and returns the latter. Both of these are interpreted as local dates,
     * with no reference to time zone.
     *
     * @param date local date as a {@link Date} instance.
     * @return local date as a number of days since the start of the Unix epoch.
     */
    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }

  }

  private static class Prepopulate extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
        @Override
        public void run() {
          Transform transform = new Transform();
          transform.setName("Gaussian Blur");
          transform.setClazz("edu.cnm.deepdive.ironorimgtransform.service.GaussianBlur");
          getInstance().getTransformDao().insert(transform);

          Transform transform2 = new Transform();
          transform2.setName("Scale");
          transform2.setClazz(null);
          getInstance().getTransformDao().insert(transform2);

        }
      });
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }

  }

}
