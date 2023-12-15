package cs.jou.support;

import cs.jou.common.auth.CurrentUser;
import cs.jou.common.support.WebContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppContext implements WebContext, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public CurrentUser getUser() {
        try {
            return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception ex) {
            return CurrentUser.anonymous();
        }
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return context.getBean(clazz);
    }

    @Override
    public void publish(ApplicationEvent event) {
        context.publishEvent(event);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
