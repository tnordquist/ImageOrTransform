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

## Third Party Libraries

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

## External Services
The app consumes the following external service:

[Google Sign-In](https://developers.google.com/identity/sign-in/android/start-integrating)

## Aesthetic/Cosmetic Improvements
The following cosmetic changes would improve the appearance of the app: 
- Style the buttons or exchange the buttons with icons.
- Move the history button to the top bar of the screen.
- Style the history list.


## Functional Stretch Goals
The app could be extended in the following ways:
- More transform algorithm choices need to be added.  The app is already structured to allow new algorithms to be added to the app.
- Allow the user to share favorite transformed images.

## Wireframes

[Main Screen](ImageOrTransform Wire Frame Main Screen.pdf)

[Transform Dialog](docs/ImageOrTransform Transform Wire Frame Dialog Screen.pdf)

[Image Dialog](ImageOrTransform Wire Frame Image Dialog.pdf)

[History Screen](ImageOrTransform Wire Frame History.pdf)

## User Stories

- As an eight year old I think it’s cool that I can keep changing the picture and that it allows me to press buttons that get new pictures.

- As a student I need to make myself take intermittent short breaks while studying and writing programs so that I can rest and recharge my brain. The generative art application let’s me both get my mind off of my studies and to inspire me with what can be done with computer technology.

- As a person who works at a desk all day I found this app and I immediately loved watching the amazing pictures that can be made out of images. I never get tired of see what an image can be changed into. Also, it always helps me relax.
    
## [DDL](docs/ddl.md) 

Links to the apps data model.

## [**Javadoc**](docs/api)
Links to generated Javadoc HTML pages in repository.

## Instructions for Building the App

1. Clone the repository into a local Android Studio project.
2. Build the app onto a device running a minimum of API 21.

## Instructions for Using the App

1. The initial screen is the Main Menu.  It presents users with the option to transform the image that loads onto the screen or to choose to get another image. It also presents the user with the option to see the history of images transformed.
2. Upon clicking the IMAGE button, the user is taken to an alert dialog which allows the user to choose an image from the gallery, or to take a photo, or to cancel and go back to the main screen.
3. - Upon clicking the TRANSFORM button, the user is taken to a menu which gives the user a choice of transforms.
    - Upon clicking a menu choice, the user is taken to an alert dialog
which requires the user to input information for parameters needed for the particular transform chosen.
    - Once the user has entered the required information and clicked on the on button in the alert dialog, the user is taken back to the main screen to see the change made to the image. The user can also choose to cancel the transform and so returns to the main screen with the image unchanged.
4. Upon clicking the HISTORY button the user is taken to a recyclerview with a list of urls corresponding to each image that has been transformed. The Android back navigation button returns the user to the main screen.

