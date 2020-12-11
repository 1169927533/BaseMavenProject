public class DependComp {
    int compileSdkVersion = 29
    String buildToolsVersion = '29.0.3'

    int minSdkVersion = 17
    int targetSdkVersion = 29
    String supportVersion = '29.1.1'

    boolean latest = true
    boolean thirdPartLatest = false


    String appcompat = "1.1.0"
    String constraint_layout = "1.1.3"
    String core_ktx = "1.3.0"

    String kotlin = "1.3.72"
    String kotlin_coroutine = "1.3.7"

    String lifecycle_version = "2.2.0"
    String lifecycle_extensions = "2.2.0"
    String lifecycle_runtime = "2.2.0"
    String recyclerview = "1.1.0"

    String appCompat() {
        return "androidx.appcompat:appcompat:${appcompat}"
    }

    String appCompat(String version) {
        return "androidx.appcompat:appcompat:${version}"
    }

    String constraintlayout() {
        return "androidx.constraintlayout:constraintlayout:${constraint_layout}"
    }

    String constraintlayout(String version) {
        return "androidx.constraintlayout:constraintlayout:${version}"
    }

    String core_ktx() {
        return "androidx.core:core-ktx:${core_ktx}"
    }

    String core_ktx(String version) {
        return "androidx.core:core-ktx:${version}"
    }

    String kotlin_stdlib() {
        return "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${kotlin}"
    }

    String kotlin_test() {
        return "org.jetbrains.kotlin:kotlin-test-junit:${kotlin}"
    }

    String kotlin_plugin() {
        return "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlin}"
    }

    String kotlin_allopen() {
        return "org.jetbrains.kotlin:kotlin-allopen:${kotlin}"
    }

    String kotlin_core() {
        return "org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlin_coroutine}"
    }

    String kotlin_kandroid() {
        return "org.jetbrains.kotlinx:kotlinx-coroutines-android:${kotlin_coroutine}"
    }

    String kotlin_kcm() {
        return "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${kotlin_coroutine}"
    }

    String kotlin_multidex() {
        return "com.android.support:multidex:1.0.3"
    }

    String lifecycle_version() {
        return "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle_version}"
    }

    String lifecycle_extensions() {
        return "androidx.lifecycle:lifecycle-extensions:${lifecycle_extensions}"
    }

    String viewmodel() {
        return "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}"
    }

    String liceData() {
        return "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}"
    }

    String lifecycleruntime() {
        return "androidx.lifecycle:lifecycle-runtime:${lifecycle_runtime}"
    }

    String recyclerview() {
        return "androidx.recyclerview:recyclerview:${recyclerview}"
    }
}