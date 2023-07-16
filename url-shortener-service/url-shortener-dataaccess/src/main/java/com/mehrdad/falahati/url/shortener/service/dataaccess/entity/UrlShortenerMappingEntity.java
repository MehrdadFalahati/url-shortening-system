package com.mehrdad.falahati.url.shortener.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "us_mapping")
@Entity
@NamedEntityGraph(name = "urlShortenerClickingHistory",
        attributeNodes = @NamedAttributeNode("urlShortenerClickingHistory"))
public class UrlShortenerMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String originalUrl;
    @Column(unique = true)
    private String shortUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Instant modifyAt;

    @OneToOne(mappedBy = "urlShortenerMapping", cascade = CascadeType.ALL)
    private UrlShortenerClickingHistoryEntity urlShortenerClickingHistory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlShortenerMappingEntity that = (UrlShortenerMappingEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UrlShortenerMappingEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
