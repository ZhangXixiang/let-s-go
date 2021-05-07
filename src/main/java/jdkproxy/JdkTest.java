package jdkproxy;

import javax.jnlp.DownloadService;
import javax.jnlp.DownloadServiceListener;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * Created by qcl on 2018/11/29
 * desc:测试
 */
public class JdkTest {
    public static void main(String[] args) {
        JdkClass jdkClass = new JdkClass();
        MyInvocationHandler handler = new MyInvocationHandler(jdkClass);
        // Proxy为InvocationHandler实现类动态创建一个符合某一接口的代理实例
        //这里的proxyInstance就是我们目标类的增强代理类
        JdkInterface proxyInstance = (JdkInterface) Proxy.newProxyInstance(jdkClass.getClass().getClassLoader(),
                jdkClass.getClass()
                        .getInterfaces(), handler);
        proxyInstance.add();
        //打印增强过的类类型
        System.out.println("=============" + proxyInstance.getClass());
        System.out.println(new Double(0.1)+new Double(0.2));
    }
}
