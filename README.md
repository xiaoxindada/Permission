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
内置的函数有

```

```

### 可选编译成jar

```
cd <source path>
python3 build.py
```
