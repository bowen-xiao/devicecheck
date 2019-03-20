package bowen.caipiao.devicecheck;

import android.os.Build;

import java.util.ArrayList;
import java.util.Map;

/***
 * 对于root机器隐藏和显示底部按键
 */
public class CloseBarUtil {
    /**
     * 关闭底部导航条
     */
    public static void closeBar() {
        try {
            // 需要root 权限
            Build.VERSION_CODES vc = new Build.VERSION_CODES();
            Build.VERSION vr = new Build.VERSION();
            String ProcID = "79";
            if (vr.SDK_INT >= vc.ICE_CREAM_SANDWICH) {
                ProcID = "42"; // ICS AND NEWER
            }
            // 需要root 权限
            Process proc = Runtime.getRuntime().exec(
                    new String[]{
                            "su",
                            "-c",
                            "service call activity " + ProcID
                                    + " s16 com.android.systemui"}); // WAS 79
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示底部导航条
     */
    public static void showBar() {
        try {
            Process proc = Runtime.getRuntime().exec(
                    new String[]{"am", "startservice", "-n",
                            "com.android.systemui/.SystemUIService"});
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示导航栏
     */
    public static void showBar2() {
        try {
            String command;
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
            ArrayList<String> envlist = new ArrayList<String>();
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                envlist.add(envName + "=" + env.get(envName));
            }
            String[] envp = envlist.toArray(new String[0]);
            Process proc = Runtime.getRuntime().exec(
                    new String[] { "su", "-c", command }, envp);
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
