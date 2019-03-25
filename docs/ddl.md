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

"CREATE TABLE IF NOT EXISTS `Image` (
`image_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
`external_url` TEXT, `internal_url` TEXT, 
`transform_id` INTEGER, 
`timestamp` INTEGER, 
`info` TEXT, 
`from_id` INTEGER, 
FOREIGN KEY(`transform_id`) REFERENCES `Transform`(`transform_id`) ON UPDATE NO ACTION ON DELETE CASCADE 
);
```