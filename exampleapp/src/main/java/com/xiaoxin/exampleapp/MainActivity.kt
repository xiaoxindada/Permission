package com.xiaoxin.exampleapp;

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xiaoxin.permissionmanager.Permission


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)

        val permissions = arrayOf(
            "android.permission.READ_CONTACTS",
            "android.permission.CAMERA",
            "android.permission.CALL_PHONE"
        )

        button1.setOnClickListener {
            val diedList: MutableList<String> = mutableListOf()

            if (!Permission.AllisGrated(this@MainActivity, permissions, false)) {
                Permission.request(this@MainActivity, permissions)
                for (p in permissions) {
                    if (!Permission.isGrated(this@MainActivity, p, false))
                        diedList.add(p)
                }
                if (diedList.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, "拒绝列表: $diedList", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "此app已获得所有权限", Toast.LENGTH_LONG).show()
            }
        }


        button2.setOnClickListener {
            val grantedPermissionsList: MutableList<String> = mutableListOf()

            for (p in permissions) {
                if (Permission.isGrated(this@MainActivity, p, false))
                    grantedPermissionsList.add(p)
            }

            if (grantedPermissionsList.isNotEmpty())
                Toast.makeText(
                    this@MainActivity,
                    "成功获取权限的列表: $grantedPermissionsList",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(this@MainActivity, "此app没有任何权限", Toast.LENGTH_LONG).show()
        }

        button3.setOnClickListener {
            Permission.forwardToSettingsDialog(this@MainActivity)
        }

        button4.setOnClickListener {
            Permission.forwardToSettingsDialog(this@MainActivity,"自定义窗口","是否跳转到设置","是", "否")
        }
    }
}