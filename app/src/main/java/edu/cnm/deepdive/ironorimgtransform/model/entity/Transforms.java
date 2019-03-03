package edu.cnm.deepdive.ironorimgtransform.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;

@Entity(
    indices = @Index(value = "date", unique = true))

public class Transforms {

  @ColumnInfo(name = "transform_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @Expose
  private String name;

  @Expose
  private String detail;// this may become several columns

  @Expose
  private String example;// should this be a url?

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
