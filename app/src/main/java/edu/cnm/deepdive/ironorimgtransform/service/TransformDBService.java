package edu.cnm.deepdive.ironorimgtransform.service;

import android.support.annotation.Nullable;
import edu.cnm.deepdive.android.BaseFluentAsyncTask;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import edu.cnm.deepdive.ironorimgtransform.model.pojo.TransformWithAccesses;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import edu.cnm.deepdive.ironorimgtransform.model.pojo.TransformWithAccesses;
import edu.cnm.deepdive.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides a service layer for accessing the {@link TransformDB} Room/SQLite database. Each operation is
 * implemented as a static nested class that extends {@link BaseFluentAsyncTask}.
 */
public final class TransformDBService {

  private TransformDBService() {
  }

  /**
   * Implements an asynchronous <code>INSERT</code> of one or more {@link Transform} instances, with
   * related {@link Image} instances, into the local database.
   */
  public static class InsertApodTask
      extends BaseFluentAsyncTask<Transform, Void, List<Long>, List<Long>> {

    private boolean foreground;

    /**
     * Initializes <code>INSERT</code> task with <code>foreground</code> indicating intention to
     * present image immediately in user interface
     *
     * @param foreground <code>true</code> if image will be displayed immediately;
     * <code>false</code> otherwise.
     */
    public InsertApodTask(boolean foreground) {
      this.foreground = foreground;
    }

    @Override
    protected List<Long> perform(Transform... transforms) {
      List<Long> transformIds = TransformDB.getInstance().getTransformDao().insert(transforms);
      if (foreground) {
        List<Image> accesses = new LinkedList<>();
        for (long id : transformIds) {
          Image access = new Image();
          access.setTransformId(id);
          accesses.add(access);
        }
        TransformDB.getInstance().getImageDao().insert(accesses);
      }
      return transformIds;
    }

  }

  /**
   * Implements an asynchronous <code>SELECT</code> of a single {@link Transform} instance, and an
   * <code>INSERT</code> of a related {@link Image} instance, in the local database.
   */
  public static class SelectApodTask extends BaseFluentAsyncTask<Date, Void, Transform, Transform> {

//    @Override
//  protected Transform perform(Date... dates) {
//    Transform transform =TransformDB.getInstance().getTransformDao().findFirstByDate(dates[0]);
//    if (transform == null) {
//      throw new TaskException();
//    }
//    Image access = new Image();
//    access.setApodId(transform.getId());
//    ApodDB.getInstance().getAccessDao().insert(access);
//    return apod;
//  }

}

  /**
   * Implements an asynchronous <code>SELECT</code> of all {@link Transform} instances (sorted in
   * descending date order) from the local database.
   */
  public static class SelectAllApodTask
      extends BaseFluentAsyncTask<Void, Void, List<Transform>, List<Transform>> {

    @Override
    protected List<Transform> perform(Void... voids) {
      return TransformDB.getInstance().getTransformDao().findAll();
    }

  }

  public static class SelectAllApodWithAccessesTask
      extends BaseFluentAsyncTask<Void, Void, List<TransformWithAccesses>, List<TransformWithAccesses>> {

//    @Override
//    protected List<TransformWithAccesses> perform(Void... voids) {
//      return TransformDB.getInstance().getTransformDao().findAllWithAccesses();
//    }

  }

  /**
   * Implements an asynchronous <code>DELETE</code> of one or more {@link Image} instances from the
   * local database.
   */
  public static class DeleteApodTask extends BaseFluentAsyncTask<Transform, Void, Void, Void> {

    @Nullable
    @Override
    protected Void perform(Transform... transforms) throws TaskException {
      TransformDB.getInstance().getTransformDao().delete(transforms);
      return null;
    }

  }

  /**
   * Implements an asynchronous <code>INSERT</code> of one or more {@link Image} instances into the
   * local database.
   */
  public static class InsertAccessTask
      extends BaseFluentAsyncTask<Image, Void, List<Long>, List<Long>> {

    @Nullable
    @Override
    protected List<Long> perform(Image... images) throws TaskException {
      return TransformDB.getInstance().getImageDao().insert(images);
    }

  }

}
