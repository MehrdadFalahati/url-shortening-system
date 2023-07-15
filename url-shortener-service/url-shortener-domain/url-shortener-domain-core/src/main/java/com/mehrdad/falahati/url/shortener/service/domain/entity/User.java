package com.mehrdad.falahati.url.shortener.service.domain.entity;

import com.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.mehrdad.falahati.common.domain.valueobject.UserId;

public class User extends AggregateRoot<UserId> {
    public User() {
    }

    public User(UserId userId) {
        super.setId(userId);
    }
}
