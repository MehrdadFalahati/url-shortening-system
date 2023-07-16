package com.mehrdad.falahati.url.shortener.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "us_clicking_history")
@Entity
public class UrlShortenerClickingHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "US_MAPPING_ID")
    private UrlShortenerMappingEntity urlShortenerMapping;
    private Long clickingCounter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlShortenerClickingHistoryEntity that = (UrlShortenerClickingHistoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UrlShortenerClickingHistoryEntity{" +
                "id=" + id +
                ", clickingCounter=" + clickingCounter +
                '}';
    }
}
