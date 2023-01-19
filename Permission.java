
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;


public class Permission {
    /**
     *
     * @param context 上下文
     * @param permission 权限字符串
     */
    public static void request(Context context, String permission) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 1);
    }

    /**
     *
     * @param context 上下文
     * @param permissions 权限字符串数组
     */
    public static void request(Context context, String[] permissions) {
        ActivityCompat.requestPermissions((Activity) context, permissions, 1);
    }

    /**
     *
     * @param context 上下文
     * @param permission 权限字符串
     * @return 权限是否已申请成功的结果
     */
    public static boolean isGrated(Context context, String permission) {
        final String TAG = "PermissionCheck";
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission: " + permission + " died");
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param context 上下文
     * @param permissions 权限字符串数组
     * @return 权限是否已申请成功的结果
     */
    public static boolean AllIsGrated(Context context, String[] permissions) {
        final String TAG = "PermissionCheck";
        ArrayList<String> diedList = new ArrayList<>();
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED)
                diedList.add(p);
        }
        if (diedList.size() > 0) {
            Log.d(TAG, "PermissionDiedList: " + diedList);
            return false;
        } else {
            return true;
        }
    }

    /**
    *
    * @param context 上下文
    *
    **/
    private static void forwardToSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri url = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(url);
        context.startActivity(intent);
    }

    /**
     *
     * @param context 上下文
     * @param dialogTitle 窗口标题
     * @param dialogMessage 窗口描述
     * @param dialogPositiveMessage 窗口确认描述
     * @param dialogNegativeMessage 窗口取消描述
     **/
    public static void forwardToSettingsDialog(Context context, String dialogTitle, String dialogMessage, String dialogPositiveMessage, String dialogNegativeMessage) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(dialogTitle);
        dialog.setMessage(dialogMessage);
        dialog.setCancelable(false);
        dialog.setPositiveButton(dialogPositiveMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                forwardToSettings(context);
            }
        });
        dialog.setNegativeButton(dialogNegativeMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    /**
     *
     * @param context 上下文
     **/
    public static void forwardToSettingsDialog(Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Tips");
        dialog.setMessage("Go to the SystemAppPermissionEditor");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                forwardToSettings(context);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
