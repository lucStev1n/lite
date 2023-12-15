package cs.jou.auth;

import cs.jou.trigger.utils.ServletUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class FrameRememberMeService extends AbstractRememberMeServices {
    private String key;

    public FrameRememberMeService(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication token) {
        String uuid = UUID.randomUUID().toString();

        if (token instanceof UsernamePasswordAuthenticationToken) {

        }
        //        getParameter()
        setCookie(new String[]{"1", "admin", String.format("%s,%s", key, uuid)}, - 1, request, response);
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        if (cookieTokens.length != 3) {
            throw new InvalidCookieException("");
        }

        AuthorizedUser user = AuthorizedUser.withUserName(cookieTokens[1]).id(cookieTokens[0]);
        user.setIp(ServletUtils.ip());
        return user;
    }

    public FrameRememberMeService key(String hashKey) {
        this.key = hashKey;

        return this;
    }
}
