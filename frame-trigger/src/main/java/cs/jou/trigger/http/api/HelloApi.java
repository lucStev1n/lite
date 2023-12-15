package cs.jou.trigger.http.api;

import cs.jou.domain.comment.model.entity.CommentCmd;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "1tn", description = "2td")
@RequestMapping("/v1")
public interface HelloApi {

    @PostMapping
    Object query(@RequestBody  CommentCmd.Create cmd);
}
