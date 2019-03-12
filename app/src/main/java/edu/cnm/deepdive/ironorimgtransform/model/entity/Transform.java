package edu.cnm.deepdive.ironorimgtransform.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Encapsulates the attributes of a single image transformation algorithm. Room
 * and GSon annotations are used to specify entity &amp; attribute mapping for
 * database persistence, and property mapping for JSON serialization/deserialization
 * mapping.
 */

@Entity

public class Transform {

  @ColumnInfo(name = "transform_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @NonNull
  @ColumnInfo(name = "name", index = true)
  private String name = "";

  @ColumnInfo(name = "detail", index = true, collate = ColumnInfo.NOCASE)
  private String detail;// this may become several columns)

  private String example;// this can be a drawable resource or it could be a url.

  private String clazz;

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
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
