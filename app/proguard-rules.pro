# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
############################################
# XR classes (Fix R8 Missing Classes Error)
############################################

-dontwarn com.android.extensions.xr.**
-dontwarn com.google.androidxr.**

-keep class com.android.extensions.xr.** { *; }
-keep class com.google.androidxr.** { *; }

############################################
# Kotlin
############################################

-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

############################################
# Coroutines
############################################

-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

############################################
# Gson (if you use it)
############################################

-keepattributes Signature
-keepattributes *Annotation*

############################################
# Compose (usually safe)
############################################

-dontwarn androidx.compose.**

############################################
# General
############################################

-keepattributes *Annotation*
-keepattributes Signature