# ImageOrTransform

## Summary

This project was created with the intent of challenging the creater of this app to have to solve the problem of converting algorithms to art display at the ui.  This app is both useful and interesting. The app is useful as a means to inspire users with the idea that science, technology, and art can be connected together to form a unified effect.  A constant challenge for society is to combine scientific and technological progress with the aesthetic needs of society.  Otherwise, controlled transformations of physical reality are always interesting and potentially useful.

## Current State of Completion

There are two main deficiencies regarding the completion of this app: the technical issus regarding camera image distortion and the need, really, to add more image transforming algorithms because currently there is only one.  See bullet issues below.

* Correct initial distortion of camera image. This distortion appears before the image blurring algorithm has been applied.  This distortion does not occur when choosing images not derived from the camera.

* More transforming algorithms need to be added to the app.  Currently, only the GaussianBlur transform type is available.
     
* Implement the fromId located in the Image entity so that it is known from which exact image each transformed image was derived.

* Add button to the history recyclerview which when clicked clears the history of transformed images.

## Android Version Testing

This app has been extensively testing on a Samsung tablet, SM-T 380 (Android 8.1.0, API 27).  It has also been testing using the Android Studio Emulator running API 28 on a Nexus 5X emulation.  Minimum API is 21.  At present, there are not any known Android devices that do not support this app, so long as the minimum Android API is met and the user gives the required permissions. The app currently supports english only.

The Javadoc HTML for this project has been generated on OSX  with the following command line arguments. 
(Note that all arguments are specified on a single line for execution, but they're written on multiple lines for clarity here. Multiple options can also be specified in an options file, using the `@files` option.)

 ```
 -bootclasspath /Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/rt.jar:/opt/android/sdk/platforms/android-21/android.jar 
 -link https://docs.oracle.com/javase/8/docs/api/ 
 -link https://google.github.io/gson/apidocs/ 
 -link https://square.github.io/retrofit/2.x/converter-gson/ 
 -link http://square.github.io/retrofit/2.x/retrofit/ 
 -link https://square.github.io/okhttp/3.x/okhttp/ 
 -linkoffline https://developer.android.com/reference /opt/android/sdk/docs/reference 
 -windowtitle "ImageOrTransform"
 ```

## User Stories


    
## [DDL](docs/ddl.md) 

Links to the apps data model.

## [**Javadoc**](docs/api)

Links to generated Javadoc HTML pages in repository.

### What’s the basic functionality of the app?
The app will allow the user to easily input seed images and data using buttons or marks made using the touch screen that will allow the user to influence the resulting algorithmic art image generated.

### Who would find this fun or useful? 

Anyone interested in the intersection of technology and art.  Anyone interested in artificial intelligence because as software participates independently, to whatever degree, in activities thought only to be human activities, such as art, hints at artificial intelligence.  Anyone who desires a free time activity that sparks the imagination.  Anyone who needs a break to recharge their mental batteries after intense thinking work because the activity keeps the mind interested without taxing the mind.

### Could this app be useful on its own, with no connection to the internet?

Once downloaded the app will contain the algorithms and possible data input features free of need for internet connection.  

### What sort of data would this app use or share if it had a connection to the internet?

It could use emojis and pictures already on the phone to serve as seed images for generating the art.  The app would possibly have the ability to share the final images with others via email or text.

