package cs.jou.trigger.http.limit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class LimitMeta {
    private String ip;
    private AtomicInteger count;
    private int second;

    public LimitMeta(String ip, int count, int second) {
        this.ip = ip;
        this.count = new AtomicInteger(count);
        this.second = second;
    }

    public int inc() {
        return this.count.incrementAndGet();
    }
}
