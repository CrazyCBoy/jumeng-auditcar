package com.jumeng.auditcar.web.auditor;

import com.jumeng.auditcar.web.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName SpringSecurityAuditorAware
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/18 15:40
 * @Version 1.0
 **/
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserName());
    }
}
