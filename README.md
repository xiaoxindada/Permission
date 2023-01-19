### Permission
安卓 app 权限申请偷懒用

### 如何使用
app的Manifest.xml中添加权限

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.xiaoxin.exampleapp">

    <uses-permission android:name="android.permission.CALL_PHONE"/>
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.CAMERA" />

    ...
</manifest>
```

申请权限使用 `request()` 方法

```java
...

String[] permissions = new String[] {
    "android.permission.READ_CONTACTS",
    "android.permission.CAMERA",
    "android.permission.CALL_PHONE"
};
Permission.request(MainActivity.this, permissions);
```

```java
...

Permission.request(MainActivity.this, "android.permission.CALL_PHONE");
```

检查权限是否以申请成功，使用 `isGrated()` 方法

```java
...

if (!Permission.isGrated(MainActivity.this, "android.permission.CALL_PHONE"))
    Permission.request(MainActivity.this, "android.permission.CALL_PHONE");
```

如果要同时检查数组里面的所有权限是否都申请成功 使用 `AllIsGrated()` 方法

```java
...

String[] permissions = new String[] {
    "android.permission.READ_CONTACTS",
    "android.permission.CAMERA",
    "android.permission.CALL_PHONE"
};
if (!Permission.AllIsGrated(MainActivity.this, permissions))
    Permission.request(MainActivity.this, permissions);
```

如果要需要用户自己去管理app的权限 需要跳转到系统设置的app权限设置界面 使用 `forwardToSettingsDialog()` 方法

```java
...

Permission.forwardToSettingsDialog(MainActivity.this);
```

```java
...

Permission.forwardToSettingsDialog(MainActivity.this,"自定义窗口","是否跳转到设置","是", "否");
```
