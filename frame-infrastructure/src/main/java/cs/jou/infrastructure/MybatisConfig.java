package cs.jou.infrastructure;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import cs.jou.infrastructure.support.SqlBeautyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
        "cs.jou.infrastructure.mapper"
})
public class MybatisConfig {
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setMaxLimit(3000L);
        return innerInterceptor;
    }

    @Bean
    @ConditionalOnProperty(name = {"sql.beauty"}, havingValue = "true", matchIfMissing = true)
    public SqlBeautyInterceptor sqlBeautyInterceptor() {
        return new SqlBeautyInterceptor();
    }
}
