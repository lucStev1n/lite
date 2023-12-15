package cs.jou.trigger.http;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.service.ICommentService;
import cs.jou.trigger.http.api.HelloApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController implements HelloApi {
    final ICommentService service;

    @Override
    public Object query(CommentCmd.Create cmd) {
        service.save(cmd);
        return null;
    }
}
