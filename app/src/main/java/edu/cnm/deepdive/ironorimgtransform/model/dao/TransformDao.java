package edu.cnm.deepdive.ironorimgtransform.model.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import android.arch.persistence.room.Dao;
import java.util.List;

/**
 * Declares basic CRUD operations for {@link Transform} instances in the local
 * database, using Room annotations.
 */

@SuppressWarnings("ALL")
@Dao
public interface TransformDao {

  /**
   * Inserts one or more {@link Transform} instances into the local database.
   * Any primary or unique key constraint violations will result in the existing
   * records being retained.
   *
   * @param transforms {@link Transform} instance(s) to be inserted.
   * @return inserted record ID(s).
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  List<Long> insert(Transform... transforms);


  /**
   * For inserting multiple {@link Transform} instances into the local database.Any
   * primary or unique key constraint violations will result in the existing *
   * records being retained.
   *
   * @param transforms {@link Transform} instances(s)
   * @return inserted records.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  List<Long> insert(List<Transform> transforms);

  /**
   * Selects and returns the single {@link Transform} instance (or null) for the
   * specified {@link transform_id}.
   *
   * @param Transform desired {@link transform_id} {@link transform_id}.
   * @return {@link Transform} instance if found in database; <code>null</code>
   * otherwise.
   */
  @Query("SELECT * FROM Transform WHERE transform_id = :id")
  Transform findById(Long id);

  /**
   * Selects and returns all {@link Transform} instances in the local database,
   * sorting the result in descending id order.
   *
   * @return all {@link Transform} instances in local database.
   */
  @Query("SELECT * FROM Transform ORDER BY name ASC")
  List<Transform> findAll();

  /**
   * Deletes one or more {@link Transform} instances from local database.
   *
   * @param transforms instances of {@link Transform} to be deleted from
   * database.
   * @return number of records deleted.
   */
  @Delete
  int delete(Transform... transforms);


}
