package cs.jou.common.support;

import cs.jou.common.auth.CurrentUser;
import org.springframework.context.ApplicationEvent;

public interface WebContext {

    void publish(ApplicationEvent event);

    CurrentUser getUser();

    Object getBean(Class<?> clazz);
}
