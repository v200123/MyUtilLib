# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
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

#指定要执行的优化遍数
-optimizationpasses 5

#混淆时不生成大小写混合的类名，即全部小写
-dontusemixedcaseclassnames

#指定不忽略非公共的库的类
-dontskipnonpubliclibraryclasses

#指定不忽略包可见的库类成员（字段和方法）。
-dontskipnonpubliclibraryclassmembers

#把混淆类中的方法名也混淆了
#为需要混淆的类生成唯一的混淆名称
-useuniqueclassmembernames

#关闭预验证
-dontpreverify

# 打印过程日志，在处理期间输出更多信息
-verbose

#-dontshrink #禁用压缩

#指定优化算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#关闭优化
-dontoptimize

#扩大类和类成员的访问权限，使优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#四大组件和Application的子类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends androidx.appcompat.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

#如果使用的是Google的可选许可证验证库，则可以将其代码与自己的代码混淆。 必须保留其ILicensingService接口以使库正常工作
-keep public interface com.android.vending.licensing.ILicensingService

#将混淆堆栈跟踪文件来源重命名为“SourceFile”
-renamesourcefileattribute SourceFile

#保护注解。如果代码依赖注释，则可能需要保留注释，典型应用EventBus的事件接收回调
-keepattributes *Annotation*

#保留源文件名，变量名和行号，以产生有用的混淆堆栈跟踪
-keepattributes SourceFile,LineNumberTable

#保留异常，内部类/接口，泛型，Deprecated不推荐的方法
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,EnclosingMethod

#如果引用了v4或者v7包, 不报警告，使ProGuard知道该库引用了并非所有版本的API都可用的某些类
-dontwarn android.support.**

#保留native方法
-keepclasseswithmembernames class * {
   native <methods>;
}

#保留自定义View的类及构造函数，以使它们可以被XML布局文件引用
-keep class * extends android.view.View {
   public <init>(android.content.Context);
   public <init>(android.content.Context, android.util.AttributeSet);
   public <init>(android.content.Context, android.util.AttributeSet, int);
}


#保留自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
  void set*(***);
  *** get*();
}

#保持Activity中View及其子类为入参的方法，比如android:onClick
-keepclassmembers class * extends android.app.Activity {
  public void *(android.view.View);
}


#保留符合指定构造函数类型的自定义控件类，如果和下面的写在一起，那么只有同时有这两类构造函数的类才满足
-keepclasseswithmembers class * {
   public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
   public <init>(android.content.Context, android.util.AttributeSet, int);
}


#保留R文件的静态成员，以使调用代码通过自省访问这些字段
-keepclassmembers class **.R$* {
   public static <fields>;
}

#保留枚举
-keepclassmembers enum * {
   public static **[] values();
   public static ** valueOf(java.lang.String);
}

#保留实现了Parcelable接口的类中的静态成员
-keep class * implements android.os.Parcelable {
 public static final android.os.Parcelable$Creator *;
}

#保持所有实现Serializable接口的类成员
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

#指定不混淆指定的包名称
-keeppackagenames com.milanac007.*

#指定包名下的文件都保留
-keep class com.milanac007..blecommsdk.**{*;}

