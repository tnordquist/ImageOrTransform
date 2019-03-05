package edu.cnm.deepdive.ironorimgtransform.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Encapsulates the attributes of a single image transformation algorithm. Room and GSon annotations are used to specify entity &amp; attribute mapping for database persistence, and
 * property mapping for JSON serialization/deserialization mapping.
 */

@Entity

public class Transform {

  @ColumnInfo(name = "transform_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

 @ColumnInfo(name = "name", index = true)
  private String name;

  @ColumnInfo(name = "detail", index = true)
  private String detail;// this may become several columns

  @ColumnInfo(name = "example", index = true)
  private String example;// this can be a drawable resource or it could be a url.

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getExample() {
    return example;
  }

  public void setExample(String example) {
    this.example = example;
  }
}
