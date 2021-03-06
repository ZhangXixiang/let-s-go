import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
    public static void main(String[] args) throws Exception {
        Cache<String, String> cache = CacheBuilder.newBuilder().initialCapacity(100).maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.SECONDS).build();

        cache.put("key1", "value1");
        Thread.sleep(11000);
        String value = cache.getIfPresent("key1");
        System.out.println(value);

        double scoreWithWeight = new BigDecimal(0)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}