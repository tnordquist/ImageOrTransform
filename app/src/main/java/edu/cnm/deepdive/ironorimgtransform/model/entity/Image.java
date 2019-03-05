package edu.cnm.deepdive.ironorimgtransform.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Transform.class, parentColumns = "transform_id",
    childColumns = "transform_id", onDelete = ForeignKey.CASCADE))

public class Image { // Serializable needed?

  @ColumnInfo(name = "image_id")
  @PrimaryKey(autoGenerate = true) // assures there's always a unique key
  private Long id;

  @ColumnInfo(name = "transform_id", index = true)
  private long transformId;

  @Expose
  private String ext_Url;

  @Expose
  private String int_URL;

  @Expose
  private Date timestamp;

  @Expose
  private String info;

  @ColumnInfo(name = "from_id") // Do I need new_id or just need a new id?
  @PrimaryKey(autoGenerate = true)
  private Long from_id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTransformId() {
    return transformId;
  }

  public void setTransformId(long transformId) {
    this.transformId = transformId;
  }

  public String getExt_Url() {
    return ext_Url;
  }

  public void setExt_Url(String ext_Url) {
    this.ext_Url = ext_Url;
  }

  public String getInt_URL() {
    return int_URL;
  }

  public void setInt_URL(String int_URL) {
    this.int_URL = int_URL;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public long getNew_id() {
    return from_id;
  }

  public void setNew_id(long new_id) {
    this.from_id = new_id;
  }


}
