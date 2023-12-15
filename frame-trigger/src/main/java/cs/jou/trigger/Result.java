package cs.jou.trigger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String errMsg;
    private Object data;
    private Object payload;

    public static Result then(Object data, Object payload) {
        Result result = new Result();

        result.setCode(0);
        result.setData(data);
        result.setPayload(payload);
        result.setErrMsg("request ok");

        return result;
    }

    public static Result then(Object data) {
        return then(data, null);
    }

    public static Result fail(Integer code, String msg, String payload) {
        Result result = new Result();

        result.setCode(code);
        result.setErrMsg(msg);
        result.setPayload(payload);

        return result;
    }

    public static Result fail(Integer code, String msg) {
        return fail(code, msg, null);
    }
}
