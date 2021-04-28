import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBootStrap {
    public static void main(String[] args) throws Exception {
        // 构建一个 ApplicationContext 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册一个配置 Bean
        context.register(UserConfiguration.class);
        // 调用 refresh 启动容器
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println("user.getName() = "+user.getUsername());
        synchronized (AnnotationBootStrap.class) {
            while (true) {
                try {
                    AnnotationBootStrap.class.wait();
                } catch (InterruptedException e) {
                    System.out.println("应用后台管理服务异常终止!");
                }
            }
        }
    }
}
