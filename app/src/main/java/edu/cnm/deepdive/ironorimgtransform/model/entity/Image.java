package edu.cnm.deepdive.ironorimgtransform.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Transform.class, parentColumns = "transform_id",
    childColumns = "transform_id", onDelete = ForeignKey.CASCADE))

public class Image {

  @ColumnInfo(name = "image_id")
  @PrimaryKey(autoGenerate = true) // assures there's always a unique key
  private long id;

  @ColumnInfo(name = "external_url", index = true)
  private String externalUrl;

  @ColumnInfo(name = "internal_url", index = true)
  private String internalURL;

  @ColumnInfo(name = "transform_id", index = true)
  private Long transformId;

  @ColumnInfo(name = "timestamp", index = true)
  private Date timestamp;

  @ColumnInfo(name = "info", index = true)
  private String info;

  @ColumnInfo(name = "from_id")
  @PrimaryKey(autoGenerate = true)
  private Long fromId;

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
    return externalUrl;
  }

  public void setExt_Url(String ext_Url) {
    this.externalUrl = ext_Url;
  }

  public String getInt_URL() {
    return internalURL;
  }

  public void setInt_URL(String int_URL) {
    this.internalURL = int_URL;
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
    return fromId;
  }

  public void setNew_id(long new_id) {
    this.fromId = new_id;
  }


}
