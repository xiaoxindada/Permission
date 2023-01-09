### PermissionManager
这是一个非常简单的权限管理库实现 用于简化app权限申请的所需要的逻辑

### 如何使用
在你app的Manifest.xml中添加权限

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.xiaoxin.exampleapp">

    <uses-permission android:name="android.permission.CALL_PHONE"/>
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.CAMERA" />

    ...
</manifest>
```

在你的 app/build.gradle 添加

```
repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.xiaoxindada:PermissionManager:1.0.1'
}

```

申请权限使用 `request()` 方法

```kotlin
...
val permissions = arrayOf(
    "android.permission.READ_CONTACTS",
    "android.permission.CAMERA",
    "android.permission.CALL_PHONE"
)
Permission.request(this@MainActivity, permissions)
```

```kotlin
Permission.request(this@MainActivity, "android.permission.CALL_PHONE")
```

检查权限是否以申请成功，使用 `isGrated()` 方法

```kotlin
if (!Permission.isGrated(this@MainActivity, "android.permission.CALL_PHONE", false))
    Permission.request(this@MainActivity, "android.permission.CALL_PHONE")
```

如果要同时检查数组里面的所有权限是否都申请成功 使用 `AllIsGrated()` 方法

```kotlin
val permissions = arrayOf(
    "android.permission.READ_CONTACTS",
    "android.permission.CAMERA",
    "android.permission.CALL_PHONE"
)
if (!Permission.AllIsGrated(this@MainActivity, permissions, false))
    Permission.request(this@MainActivity, permissions)
```

如果要需要用户自己去管理app的权限 需要跳转到系统设置的app权限设置界面 使用 `forwardToSettings()` 方法

```kotlin
Permission.forwardToSettingsDialog(this@MainActivity)
```

```kotlin
Permission.forwardToSettingsDialog(this@MainActivity,"自定义窗口","是否跳转到设置","是", "否")
```

**更多多例子请查看 exampleapp**

### 可选编译成jar

```
cd <source path>
python3 build.py
```
