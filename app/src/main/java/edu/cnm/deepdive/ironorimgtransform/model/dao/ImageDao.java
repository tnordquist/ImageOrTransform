package edu.cnm.deepdive.ironorimgtransform.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import java.util.List;

@Dao
public interface ImageDao {

  @Insert
  List<Long> insert(Image... accesses);

  @Insert
  List<Long> insert(List<Image> accesses);

}
