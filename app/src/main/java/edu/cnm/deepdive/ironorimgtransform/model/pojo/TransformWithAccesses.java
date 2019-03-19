package edu.cnm.deepdive.ironorimgtransform.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;

public class TransformWithAccesses {
    @Embedded
    private Transform transform;

    @Relation(parentColumn = "apod_id", entityColumn = "apod_id")
    private List<Image> accesses;

    public Transform getApod() {
      return transform;
    }

    public void setApod(Transform apod) {
      this.transform = apod;
    }

    public List<Image> getAccesses() {
      return accesses;
    }

    public void setAccesses(List<Image> accesses) {
      this.accesses = accesses;
    }

  }