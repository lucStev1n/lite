package cs.jou.trigger.http;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.service.CommentService;
import cs.jou.trigger.http.api.HelloApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController implements HelloApi {
    final CommentService service;

    @Override
    public Object query(CommentCmd.Create cmd) {
        service.save(cmd);
        return null;
    }

    public Object fallback(Throwable throwable) {
        log.error("fallback call: ", throwable);
        return throwable;
    }
}
