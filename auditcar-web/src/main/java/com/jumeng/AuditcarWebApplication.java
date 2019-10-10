package com.jumeng;

import com.jumeng.auditcar.domain.reposiotry.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableJpaRepositories(basePackages = "com.jumeng", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class AuditcarWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditcarWebApplication.class, args);
    }

}
