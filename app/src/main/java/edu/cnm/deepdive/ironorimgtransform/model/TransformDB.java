package edu.cnm.deepdive.ironorimgtransform.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.util.Log;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.TransformApplication;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB.Converters;
import edu.cnm.deepdive.ironorimgtransform.model.dao.ImageDao;
import edu.cnm.deepdive.ironorimgtransform.model.dao.TransformDao;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

    /**
     * This method pre-populates the Transform database with id, name, and class name.
     *
     * @param db database to which pre-load items will be added
     */
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
        @Override
        public void run() {

          try (
              InputStream input = TransformApplication.getInstance().getResources()
                  .openRawResource(R.raw.transforms);
              Reader reader = new InputStreamReader(input);
              CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim())
          ) {
            List<Transform> transforms = new LinkedList<>();
            for (CSVRecord record : parser) {
              Transform transform = new Transform();
              transform.setName(record.get(0));
              transform.setClazz(record.get(1).isEmpty() ? null : record.get(1));
              transforms.add(transform);
            }
            getInstance().getTransformDao().insert(transforms);
          } catch (IOException e) {
            Log.e("Something went wrong!", getClass().getSimpleName());
          }
        }
      });
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }

  }

}
