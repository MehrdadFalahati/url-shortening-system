package com.mehrdad.falahati.url.shortener.service.domain.entity;

import com.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UrlShortenerMapping extends AggregateRoot<UrlShortenerMappingId> {
    private final UserId userId;
    private final String originalUrl;
    private String shortUrl;
    private ZonedDateTime createAt;
    private ZonedDateTime modifyAt;

    public void initialUrlShortenerMapping(String shortUrl) {
        this.createAt = ZonedDateTime.now(ZoneId.of("UTC"));
        this.modifyAt = ZonedDateTime.now(ZoneId.of("UTC"));
        this.shortUrl = shortUrl;
    }

    public void validateUrlShortener(long limitedUrl) {
        if (limitedUrl > 10)
            throw new UrlShortenerDomainException("User[id=" + userId + "] can not add url more than 10");
    }

    public void updateUrlUsageTime() {
        this.modifyAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    private UrlShortenerMapping(Builder builder) {
        setId(builder.id);
        userId = builder.userId;
        originalUrl = builder.originalUrl;
        shortUrl = builder.shortUrl;
        createAt = builder.createAt;
        modifyAt = builder.modifyAt;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public ZonedDateTime getModifyAt() {
        return modifyAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UrlShortenerMappingId id;
        private UserId userId;
        private String originalUrl;
        private String shortUrl;
        private ZonedDateTime createAt;
        private ZonedDateTime modifyAt;

        private Builder() {
        }

        public Builder id(UrlShortenerMappingId val) {
            id = val;
            return this;
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public Builder originalUrl(String val) {
            originalUrl = val;
            return this;
        }

        public Builder shortUrl(String val) {
            shortUrl = val;
            return this;
        }

        public Builder createAt(ZonedDateTime val) {
            createAt = val;
            return this;
        }

        public Builder modifyAt(ZonedDateTime val) {
            modifyAt = val;
            return this;
        }

        public UrlShortenerMapping build() {
            return new UrlShortenerMapping(this);
        }
    }
}
