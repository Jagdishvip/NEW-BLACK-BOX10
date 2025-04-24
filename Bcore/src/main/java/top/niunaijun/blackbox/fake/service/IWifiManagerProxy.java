package top.niunaijun.blackbox.fake.service;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.WorkSource;
import android.util.Log;

import java.lang.reflect.Method;

import black.android.net.wifi.BRIWifiManagerStub;
import black.android.net.wifi.BRWifiInfo;
import black.android.net.wifi.BRWifiSsid;
import black.android.os.BRServiceManager;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.ArrayUtils;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/**
 * Created by Milk on 4/12/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class IWifiManagerProxy extends BinderInvocationStub {
    public static final String TAG = "IWifiManagerProxy";

    // Constructor to bind to the 'wifi' system service
    public IWifiManagerProxy() {
        super(BRServiceManager.get().getService("wifi"));
    }

    // This method is used to check if the environment is "bad" (not used in this case)
    @Override
    public boolean isBadEnv() {
        return false;
    }

    // This method gets the actual interface for the wifi manager service
    @Override
    protected Object getWho() {
        return BRIWifiManagerStub.get().asInterface(BRServiceManager.get().getService("wifi"));
    }

    // This method injects our custom implementation of the wifi system service
    @Override
    protected void inject(Object obj, Object obj2) {
        replaceSystemService("wifi");
    }

    // MethodHook for 'getConnectionInfo' that simulates specific wifi connection details
    @ProxyMethod("getConnectionInfo")
    public static class GetConnectionInfo extends MethodHook {

        @Override
        protected Object hook(Object obj, Method method, Object[] objArr) throws Throwable {
            // Get the actual WifiInfo object from the system
            WifiInfo wifiInfo = (WifiInfo) method.invoke(obj, objArr);

            // Modify the WifiInfo to simulate a specific Wi-Fi connection
            BRWifiInfo.get(wifiInfo)._set_mBSSID("ac:62:5a:82:65:c4"); // Set BSSID (MAC Address)
            BRWifiInfo.get(wifiInfo)._set_mMacAddress("ac:62:5a:82:65:c4"); // Set MAC Address
            BRWifiInfo.get(wifiInfo)._set_mWifiSsid(BRWifiSsid.get().createFromAsciiEncoded("BlackBox_Wifi")); // Set SSID

            return wifiInfo; // Return the modified WifiInfo
        }

        // Convert an integer IP address to a string IP address (e.g., 192.168.0.1)
        public static String intIP2StringIP(int i) {
            return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
        }

        // Convert a string IP address (e.g., 192.168.0.1) to an integer
        public static int ip2Int(String str) {
            String[] split = str.split("\\.");
            int i = 0;
            for (int i2 = 0; i2 < split.length; i2++) {
                i |= Integer.parseInt(split[i2]) << (i2 * 8);
            }
            return i;
        }
    }
}
