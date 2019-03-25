```sql
CREATE TABLE IF NOT EXISTS `Transform` (
`transform_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
`name` TEXT NOT NULL, 
`detail` TEXT COLLATE NOCASE, 
`example` TEXT, 
`clazz` TEXT
);

CREATE  INDEX `index_Transform_name` ON `Transform` (
`name`
);

CREATE  INDEX `index_Transform_detail` ON `Transform` (
`detail`
);

CREATE TABLE IF NOT EXISTS `Image` (
`image_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
`external_url` TEXT, `internal_url` TEXT, 
`transform_id` INTEGER, 
`timestamp` INTEGER, 
`info` TEXT, 
`from_id` INTEGER, 
FOREIGN KEY(`transform_id`) REFERENCES `Transform`(`transform_id`) ON UPDATE NO ACTION ON DELETE CASCADE 
);

CREATE  INDEX `index_Image_external_url` ON `Image` (
`external_url`
);

CREATE  INDEX `index_Image_internal_url` ON `Image` (
`internal_url`
);

CREATE  INDEX `index_Image_transform_id` ON `Image` (
`transform_id`
);

CREATE  INDEX `index_Image_timestamp` ON `Image` (
`timestamp`
);

CREATE  INDEX `index_Image_info` ON `Image` (
`info`);
```