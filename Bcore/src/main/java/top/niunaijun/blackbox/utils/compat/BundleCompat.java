package top.niunaijun.blackbox.utils.compat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import java.lang.reflect.Method;

public class BundleCompat {

    public static IBinder getBinder(Bundle bundle, String key) {
        if (bundle != null) {
            return bundle.getBinder(key);
        }
        return null;
    }

    public static void putBinder(Bundle bundle, String key, IBinder value) {
        if (bundle != null) {
            bundle.putBinder(key, value);
        }
    }

    public static void putBinder(Intent intent, String key, IBinder value) {
        Bundle bundle = new Bundle();
        putBinder(bundle, "binder", value);
        intent.putExtra(key, bundle);
    }

    public static IBinder getBinder(Intent intent, String key) {
        Bundle bundleExtra = intent.getBundleExtra(key);
        if (bundleExtra != null) {
            return getBinder(bundleExtra, "binder");
        }
        return null;
    }
}
