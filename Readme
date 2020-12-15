# 这是我的一个仓库工程
在自己的工程里面想要使用的话方式如下：
在根build.gradle里面添加：

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext.kotlin_version = "1.4.10"
    repositories {
        google()
        jcenter()
        //这是要添加的内容
        maven { url "https://jitpack.io" }
        maven{
            url 'https://raw.githubusercontent.com/1169927533/BaseMavenProject/master'
        }
        //----------------

    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.2.0-alpha13"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
         //这是要添加的内容
        classpath 'com.pince.gradle_plugin:versionplugin:1.0.0'
        //----
    }
}

allprojects {
    apply plugin:'com.pince.gradle_plugin'
    repositories {
        google()
        jcenter()
        //这是要添加的内容
        maven { url "https://jitpack.io" }
        maven{
            url 'https://raw.githubusercontent.com/1169927533/BaseMavenProject/master'
        }
         //----

    }
}
```
