package com.mehrdad.falahati.url.shortener.service.dataaccess.entity;

import com.mehrdad.falahati.common.domain.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url_shortener_user_m_view", schema = "us_user")
@Entity
public class UserEntity {
    @Id
    private UUID id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isEnabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' + '}';
    }
}
