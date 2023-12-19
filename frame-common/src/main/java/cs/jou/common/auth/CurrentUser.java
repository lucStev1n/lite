package cs.jou.common.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {
    protected Integer id;
    protected String name;
    protected Boolean authenticated;
    protected String ip;
    protected Date loginTime = Date.from(Instant.now());
    protected Object payload;
    protected String password;

    public boolean isGrant() {
        return Boolean.TRUE.equals(authenticated);
    }

    public static CurrentUser anonymous() {
        CurrentUser user = new CurrentUser();

        user.setId(0).setName("anonymous");

        return user;
    }

    public static CurrentUser authorized(String id, String name) {
        return authorized(Integer.valueOf(id), name, null, null);
    }

    public static CurrentUser authorized(Integer id, String name) {
        return authorized(id, name, null, null);
    }

    public static CurrentUser authorized(Integer id, String name, String ip, Object payload) {
        CurrentUser user = new CurrentUser();

        user.setId(id).setName(name).setAuthenticated(true).setIp(ip).setPayload(payload);

        return user;
    }
}
