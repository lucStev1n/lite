package cs.jou.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "server")
public class ApplicationProperties {
    private String cookieName = "remember-me";
    private String rememberMeHashKey = "remember-me-key";
    private String rememberMeParam = "remember-me";
    private boolean alwaysRememberMe = false;
}
