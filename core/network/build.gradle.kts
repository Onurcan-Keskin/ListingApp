plugins {
    alias(libs.plugins.onurcan.library)
}

android {
    namespace = "com.onurcan.core.network"

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("String", "BASE_IMG_URL", "\"https://fastly.picsum.photos/\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("String", "BASE_IMG_URL", "\"https://fastly.picsum.photos/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))

    api(libs.bundles.network)
    implementation(libs.retrofit.adapter.rxjava)
}