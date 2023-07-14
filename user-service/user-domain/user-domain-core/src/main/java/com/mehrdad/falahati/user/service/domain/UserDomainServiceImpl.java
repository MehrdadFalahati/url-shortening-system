package com.mehrdad.falahati.user.service.domain;

import com.mehrdad.falahati.user.service.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {
    @Override
    public void initiateUser(User user) {
        user.initializeUser();
        log.info("User[id={}] init with role = {}", user.getId(), user.getRole());
    }
}
