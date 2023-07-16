package com.mehrdad.falahati.url.shortener.service.domain.entity;

import com.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.mehrdad.falahati.common.domain.entity.Role;
import com.mehrdad.falahati.common.domain.valueobject.UserId;

public class User extends AggregateRoot<UserId> {
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean isEnabled;

    private User(Builder builder) {
        setId(builder.id);
        username = builder.username;
        password = builder.password;
        role = builder.role;
        isEnabled = builder.isEnabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UserId id;
        private String username;
        private String password;
        private Role role;
        private Boolean isEnabled;

        private Builder() {
        }

        public Builder id(UserId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder role(Role val) {
            role = val;
            return this;
        }

        public Builder isEnabled(Boolean val) {
            isEnabled = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
