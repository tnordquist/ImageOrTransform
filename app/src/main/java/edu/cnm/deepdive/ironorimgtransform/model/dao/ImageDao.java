package edu.cnm.deepdive.ironorimgtransform.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import java.util.Date;
import java.util.List;

@Dao
public interface ImageDao {

  @Insert
  List<Long> insert(Image... accesses);

  @Insert
  List<Long> insert(List<Image> accesses);

  /**
   * Selects and returns the single {@link Image} instance (or null) for the
   * specified {@link Date}.
   *
   * @param id desired {@link Image}.
   * @return {@link Image} instance if found in database; <code>null</code>
   * otherwise.
   */
  @Query("SELECT * FROM Image WHERE transform_id = :id")
  Image findFirstById(Long id);

  /**
   * Selects and returns all {@link Image} instances in the local database,
   * sorting the result in descending id order.
   *
   * @return all {@link Image} instances in local database.
   */
  @Query("SELECT * FROM Image ORDER BY transform_id DESC")
  List<Image> findAll();

  /**
   * Selects and returns a chosen history {@link Image} instances in the local
   * database, sorting the result in descending id order.
   *
   * @return all {@link Image} instances in local database.
   */
  @Query("SELECT * FROM Image ORDER BY timestamp DESC LIMIT :limit")
  List<Image> findHistory(long limit);

  /**
   * Selects and returns all images changed by a particular transform type
   * {@link Image} instances in the local database, sorting the result in
   * descending id order.
   *
   * @return all {@link Image} instances in local database.
   */
  @Query("SELECT * FROM Image WHERE transform_id = :transform")
  List<Image> findGroupByTransform(long transform);

  /**
   * Deletes one or more {@link Image} instances from local database.
   *
   * @param images instances of {@link Image} to be deleted from database.
   * @return number of records deleted.
   */
  @Delete
  int delete(Image... images);

}
