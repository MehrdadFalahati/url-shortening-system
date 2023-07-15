package com.mehrdad.falahati.user.service.container;

import com.mehrdad.falahati.user.service.domain.UserDomainService;
import com.mehrdad.falahati.user.service.domain.UserDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainServiceImpl();
    }
}
