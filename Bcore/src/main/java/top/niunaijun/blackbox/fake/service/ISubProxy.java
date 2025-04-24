package top.niunaijun.blackbox.fake.service;

import black.android.os.BRServiceManager;
import black.com.android.internal.telephony.BRISubStub;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.service.base.ValueMethodProxy;

public class ISubProxy extends BinderInvocationStub {
    public static final String TAG = "ISubProxy";

    // Constructor initializes the base class with the correct service name
    public ISubProxy() {
        super(BRServiceManager.get().getService("isub"));
    }

    // Method to check for bad environment, returning false in this case.
    @Override
    public boolean isBadEnv() {
        return false;
    }

    // Returns the underlying service interface for the 'isub' service
    @Override
    protected Object getWho() {
        return BRISubStub.get().asInterface(BRServiceManager.get().getService("isub"));
    }

    // Method to inject the service by replacing the system service "isub"
    @Override
    protected void inject(Object obj, Object obj2) {
        replaceSystemService("isub");
    }

    // Returns the proxy invocation, calling the base method.
    @Override
    public Object getProxyInvocation() {
        return super.getProxyInvocation();
    }

    // Enables or disables proxy behavior based on the 'z' flag
    public void onlyProxy(boolean z) {
        super.onlyProxy(z);
    }

    // Method to bind necessary method hooks to the service
    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        
        // Adding method hooks for various subscription-related methods
        addMethodHook(new ValueMethodProxy("getAllSubInfoList", (Object) null));
        addMethodHook(new ValueMethodProxy("getAllSubInfoCount", -1));
        addMethodHook(new ValueMethodProxy("getActiveSubscriptionInfo", (Object) null));
        addMethodHook(new ValueMethodProxy("getActiveSubscriptionInfoForIccId", (Object) null));
        addMethodHook(new ValueMethodProxy("getActiveSubscriptionInfoForSimSlotIndex", (Object) null));
        addMethodHook(new ValueMethodProxy("getActiveSubscriptionInfoList", (Object) null));
        addMethodHook(new ValueMethodProxy("getActiveSubInfoCount", -1));
        addMethodHook(new ValueMethodProxy("getActiveSubInfoCountMax", -1));
        addMethodHook(new ValueMethodProxy("getAvailableSubscriptionInfoList", (Object) null));
        addMethodHook(new ValueMethodProxy("getAccessibleSubscriptionInfoList", (Object) null));
        addMethodHook(new ValueMethodProxy("addSubInfoRecord", -1));
        addMethodHook(new ValueMethodProxy("addSubInfo", -1));
        addMethodHook(new ValueMethodProxy("removeSubInfo", -1));
    }
}
