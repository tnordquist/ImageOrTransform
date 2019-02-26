## Summary of Needed Data Tables

It appears that possibly six or more tables will be needed to handle the needed data for this app in order for it to do its job of transforming an image that the user has either downloaded taken using the phone or table camera.

#### An Imgur query table
 will require columns of image data including id, title, description, datetime,width,height, size, views, bandwidth, delethash, link, favorite, vote.

#### Camera image table 
will include some of the same columns as imgur query such as datetime,width,height, size.

#### Social data table
 will require using collecting data such as usage of each algorithm, usage rate setting, usage of images, usage of images downloaded, times of app usage, date of app usage.

#### Google sign data table 
will need to keep track of user names, dates, times, possible passwords.

#### Image coordinate data table 
 
 will require keeping track of image pixel coordinates and hex color data of pixels of a given image.
 
#### Transform Algorithm data table
will keep track of algorithms uses on particular images including columns of the algorithm type and the before and after image transformation information.  Also the table columns could include the rate of transformation, and the duration of transformation.

Android can we use image browser that let's us use image source.  Take image from gallery and image from camera: get path from local place where this was stored. I did these transform steps.  Original image (with source image somewhere), store the transformations,.  So I'll need an image table and I will need a transform table.