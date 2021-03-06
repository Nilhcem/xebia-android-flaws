# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /opt/android-sdk-linux/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Retry button
-keepclassmembers class com.example.codingconfessional.ui.main.MainActivity {
    public void onRetryButtonClicked(**);
}

# EventBus
-keepclassmembers class ** {
    public void onEvent*(**);
}

# JNI
-keep public class com.example.codingconfessional.utils.DecryptUtils

-keepclasseswithmembers class * {
    void onClick*(...);
}
-keepclasseswithmembers class * {
    *** *Callback(...);
}

# Jackson
-keepnames class com.fasterxml.jackson.** { *; }
-keep class org.fasterxml.** { *; }
-keepattributes *Annotation*,EnclosingMethod
-dontskipnonpubliclibraryclassmembers

-keepattributes Signature
-keepattributes *Annotation*,EnclosingMethod

-keepnames class org.codehaus.jackson.** { *; }

-dontwarn javax.xml.**
-dontwarn javax.xml.stream.events.**
-dontwarn com.fasterxml.jackson.databind.**

-keep public class com.example.codingconfessional.models.** {
  public void set*(***);
  public *** get();
}
 -dontwarn com.fasterxml.jackson.databind.**

# Retrofit
-keep class retrofit.** { *; }
-dontwarn rx.**
-dontwarn com.squareup.okhttp.internal.http.**
