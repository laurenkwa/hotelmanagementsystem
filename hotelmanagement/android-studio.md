# Guide for Android Studio

A project has multiple modules.
It reduces dependency among each functions (for example, login, search, and modify data)
A project is an app and a module is a function.

## Structure of folders in a module

This section uses "login" module for an example.

### manifests

AndroidManifest.xml

```
must be exactly this name "AndroidManifest.xml"
required file to build an Android app.
```

### java

com.github.laurenkwa.hotelmanagement

```
LoginActivity: java file for functionality(login).
```

androidTest, test

```
comes by default (haven't used yet)
```

### res

Global resources for this app.


drawable

```
comes by default (haven't used yet)
```

layout

```
activity_login.xml: we can use "design" tab on this file for preview. It's on the left-bottom side of Android Studio's UI. 

    - ID: it's like a name of an object. we can reference objects from xml to java file through its ID. 
    - for example: "import" android.widget.TextView;

```

mipmap

```
holds and passes values for other files such as (android:roundIcon="@mipmap/ic_launcher_round")
```

values

```
colors.xml: holds colours for this app.
dimens.xml: dimensions for this app.
strings.xml: strings for this app. (i.e. placeholder to prompt user to enter an email.)
    - android:hint="@string/prompt_password"
styles.xml: styles for this app.
    - android:theme="@style/AppTheme"
```

## hyperlinks for study

[Getting started - Android Developers](https://developer.android.com/training/index.html)
[Google Samples - Android Developers](https://developer.android.com/samples/index.html?language=java)
