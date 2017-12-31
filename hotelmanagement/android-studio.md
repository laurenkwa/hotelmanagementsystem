A project has multiple modules.
It reduces dependency among each functions (for example, login, search, and modify data)
A project is an app and a module is a function.



#login
##manifests
###AndroidManifest.xml
must be exactly this name "AndroidManifest.xml"
required file to build an Android app.

##java
###androidTest
###test

##res
###drawable
###layout
###mipmap
- android:roundIcon="@mipmap/ic_launcher_round"
###values
colors.xml: holds colours for this app.
- 
dimens.xml: dimensions for this app.
- 
strings.xml: strings for this app. (i.e. placeholder to prompt user to enter an email.)
- android:hint="@string/prompt_password"
styles.xml: styles for this app.
- android:theme="@style/AppTheme"
