package cs.jou.trigger.http.limit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import cs.jou.common.auth.CurrentUser;
import cs.jou.common.support.WebContext;
import cs.jou.trigger.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLimitHandler implements HandlerInterceptor {
    private final WebContext context;

    private final Cache<String, LimitMeta> block = Caffeine.newBuilder()
                                                     .expireAfterWrite(60, TimeUnit.SECONDS)
                                                     .initialCapacity(100)
                                                     .maximumSize(1000)
                                                     .build();

    private final Cache<String, LimitMeta> count = Caffeine.newBuilder()
                                                     .expireAfterWrite(10, TimeUnit.SECONDS)
                                                     .initialCapacity(100)
                                                     .maximumSize(1000)
                                                     .build();

    @Override
    public synchronized boolean preHandle(@NonNull HttpServletRequest request,
                                          @NonNull HttpServletResponse response,
                                          @NonNull Object handler) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            if (AnnotatedElementUtils.isAnnotated(hm.getMethod(), Limit.class)) {
                CurrentUser user = context.getUser();
                String key = String.format("%s-%s", ServletUtils.ip(), user.getId());
                LimitMeta isBlock = block.getIfPresent(key);
                if (isBlock != null) {
                    log.info("账号阻塞");
                    ServletUtils.sendResponse("block");
                    return false;
                }

                int second = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
                LimitMeta meta = this.count.get(key, k -> new LimitMeta(ServletUtils.ip(), 1, second));
                if (Math.abs(meta.getSecond() - second) >= 1) {
                    meta = new LimitMeta(ServletUtils.ip(), 1, second);
                }
                else {
                    int inc = meta.inc();
                    if (inc == 5) {
                        block.put(key, meta);

                        log.info("阻塞了不允许访问");
                        ServletUtils.sendResponse("block");
                        return false;
                    }
                }
                count.put(key, meta);
            }
        }

        return true;
    }
}
