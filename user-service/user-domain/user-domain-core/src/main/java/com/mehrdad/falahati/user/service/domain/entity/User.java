package com.mehrdad.falahati.user.service.domain.entity;


import com.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.mehrdad.falahati.common.domain.entity.Role;
import com.mehrdad.falahati.common.domain.valueobject.UserId;

import java.util.UUID;

public class User extends AggregateRoot<UserId> {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String phoneNumber;
    private Role role;
    private Boolean isEnabled;

    public void initializeUser() {
        setId(new UserId(UUID.randomUUID()));
        this.role = Role.USER;
        this.isEnabled = true;
    }

    private User(Builder builder) {
        super.setId(builder.id);
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
        password = builder.password;
        phoneNumber = builder.phoneNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public static final class Builder {
        private UserId id;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String phoneNumber;
        private Builder() {
        }

        public Builder id(UserId val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
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

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
