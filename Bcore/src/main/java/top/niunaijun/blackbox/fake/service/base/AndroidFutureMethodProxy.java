package top.niunaijun.blackbox.fake.service.base;

import android.internal.infra.AndroidFuture;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

public class AndroidFutureMethodProxy extends ValueMethodProxy{

    public AndroidFutureMethodProxy(String name, Object value) {
        super(name, value);
    }

    @Override
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
        Object hook = super.hook(who, method, args);
        if (BuildCompat.isT() || !BuildCompat.isS() || (hook instanceof AndroidFuture)){
            return hook;
        }
        AndroidFuture androidFuture = new AndroidFuture();
        androidFuture.complete(hook);
        return androidFuture;
    }
}
