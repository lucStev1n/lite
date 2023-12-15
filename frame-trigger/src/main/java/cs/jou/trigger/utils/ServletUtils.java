package cs.jou.trigger.utils;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ServletUtils {
    private static Gson gson;

    public static HttpServletRequest request() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "");
        return requestAttributes.getRequest();
    }

    public static HttpServletResponse response() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "");
        return requestAttributes.getResponse();
    }

    @SneakyThrows
    public static void sendResponse(Object json) {
        HttpServletResponse response = response();
        Assert.notNull(response, "");

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        try (var writer = response.getWriter()){
            writer.write(gson.toJson(json));
        }
    }

    public static String ip() {
        HttpServletRequest request = request();

        String ip = request.getHeader("x - forwarded - for");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy - Client - IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL - Proxy - Client - IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
    }

    @Autowired
    public void setGson(Gson gson) {
        ServletUtils.gson = gson;
    }
}
