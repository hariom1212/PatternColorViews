PatternColorViews
=================

## Prerequisite

This document is intend to provide an easy step for building the PatternView Android project. Some of the pre-required tools which need to be configured before starting the build process are,

*   Git
*   [JDK 1.7](http://www.oracle.com/technetwork/java/javase/downloads/index.html "JDK 1.7") This comes pre installed on MAC OSX, on others check java --version on cmd/terminal, if not available then download and install.
*   [Android SDK Latest](https://developer.android.com/sdk/index.html "Android SDK Latest")

## PatternColorViews – Build Setup
1.   Clone the PatternColorViews project from Github using following command.

    ```git clone https://github.com/hariom1212/PatternColorViews.git```

2.   The project root folder contains the following folders and files.
    *   app – Contains the source and resource files.
    *   gradle – Contains the gradle wrapper for building the project.
    *   keystore – Contains keystore for build and release mode.
    *   build.gradle – file that defines the project.
    *   settings.gradle – file that defines the dependencies.
    *   gradlew – gradle wrapper files for linux and macintosh.
    *   gradlew.bat – gradle wrapper files for windows

3. If you wish to make changes and test the project, open the application in Android studio. Please refer to Android Studio for setting up and building PatternColorViews Android application.

4. If you wish to build and install the project using console, please refer to Building projects/gradle wrapper.

5. Once the project is built, the APKs are generated under the below folder<project path>/app/build/apk
    The folder contains the following files. (there will be -unaligned files which is not required and can be ignored)
  

## Android Studio
*   Download and install latest [Android studio package](http://developer.android.com/sdk/installing/studio.html#Installing "Android studio package")
*   Install Oracle JDK 1.7(OpenJDK versions are not supported) and update the `JAVA_HOME` environment variable.
*   When launching Android Studio for the first time, a welcome screen will be displayed.
*   Click on Import Project and browse the existing projects root folder(the path where build.gradle file is present) and click OK
*   Check on auto-import while importing from the gradle model, once the project is imported and setup you would see the following structure on the projects tab(alt+1/View->Window->Projects)
*   The Android studio package contains Android SDK package. To update the SDK launch SDK Manager by clicking the SDK manager icon in Toolbar.
* For using a different SDK package, go to File->Other Settings->Default Project Structure->Android SDK and change Android SDK Location.
Make sure the File->Other Settings->Default Project Structure->SDKs is pointing to the JDK 1.7 installation path.

## Building the project
Android projects can be built using android-gradle plugin in Android studio and gradle wrapper or gradle in Command line. In both cases the output APK files will be created under <project path>/module_path/build/apk Using Android Studio

### Open the project in Android studio.

*   Click Build>Make Project to build the project
*   Click Build>Clean Project to clean
*   Click Build>Rebuild Project to clean and build the project in one step.

Press Shift+F10 to install and run the application in connected device or emulator.Check Run>Edit configurations for changing the default Run settings.
If the project contains more than one build variant like debug, release etc, change the build mode in the Build Variants dialog (usually present in left corner of the window) to required variant before building the project.

### Using Gradle Wrapper

The Gradle Wrapper is the preferred and platform independant way of starting a Gradle build. It consists of a batch script for Windows support and a shell script for support on OS X and Linux. These scripts allow to run a Gradle build without requiring that Gradle be installed on the system. The wrapper created by default for all the projects exported to or created in Gradle system.

open terminal and cd to the root project path(the path where build.gradle file is present). The gradle wrapper scripts are present in the path and execute the below commands for the respective outputs.

*   ```./gradlew clean``` to clean the project.
*   ```./gradlew build``` to build the project.
*   ```./gradlew clean build``` to clean build the project. It creates APK files for all available build varients.
*   ```./gradlew installDebug``` to build and install the debug version of project on a connected device.
*   ```./gradlew installRelease``` to build and install the debug version of project on a connected device.
*   To explore more options and tasks, run ```./gradlew tasks```

##Installing the app onto the device or emulator

ADB(Android Debug Bridge) is a tool available and provided by android, it will be available under $ANDROID_HOME(SDK PATH)/platform-tools/adb

*   adb install -r <project path>/<module path>/build/apk/<file name>.apk to install the application on the connected device or emulator.
*   For more adb related tasks/help refer to [Developer Documentation provided by Android](http://developer.android.com/tools/help/adb.html"Developer Documentation")
