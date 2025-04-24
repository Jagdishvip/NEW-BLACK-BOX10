package top.niunaijun.blackbox.fake.service;


import black.android.media.BRIAudioServiceStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

public class IAudioManagerProxy extends BinderInvocationStub {

    public IAudioManagerProxy() {
        super(BRServiceManager.get().getService("audio"));
    }

    @Override
    protected Object getWho() {
        return BRIAudioServiceStub.get().asInterface(
            BRServiceManager.get().getService("audio")
        );
    }

    @Override
    protected void inject(Object baseService, Object proxy) {
        replaceSystemService("audio");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    // Generalized hook method to avoid repetition
    private static Object hookReplaceAppPkg(Object proxy, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceLastAppPkg(args);
        return method.invoke(proxy, args);
    }

    @ProxyMethod("abandonAudioFocus")
    public static class AbandonAudioFocus extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("adjustLocalOrRemoteStreamVolume")
    public static class AdjustLocalOrRemoteStreamVolume extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("adjustMasterVolume")
    public static class AdjustMasterVolume extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("adjustStreamVolume")
    public static class AdjustStreamVolume extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("adjustSuggestedStreamVolume")
    public static class AdjustSuggestedStreamVolume extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("setMicrophoneMute")
    public static class SetMicrophoneMute extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceLastAppPkg(args);
            MethodParameterUtils.replaceLastUserId(args);
            return method.invoke(proxy, args);
        }
    }

    // Similarly add other hook classes here with same approach:
    // avrcpSupportsAbsoluteVolume, disableSafeMediaVolume, etc.

    @ProxyMethod("setSpeakerphoneOn")
    public static class SetSpeakerphoneOn extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("setStreamVolume")
    public static class SetStreamVolume extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("startBluetoothSco")
    public static class StartBluetoothSco extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("stopBluetoothSco")
    public static class StopBluetoothSco extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }

    @ProxyMethod("unregisterAudioFocusClient")
    public static class UnregisterAudioFocusClient extends MethodHook {
        @Override
        protected Object hook(Object proxy, Method method, Object[] args) throws Throwable {
            return hookReplaceAppPkg(proxy, method, args);
        }
    }
}
