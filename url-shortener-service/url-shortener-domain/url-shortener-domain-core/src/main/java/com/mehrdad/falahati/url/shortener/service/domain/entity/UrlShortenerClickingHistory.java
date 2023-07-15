package com.mehrdad.falahati.url.shortener.service.domain.entity;

import com.mehrdad.falahati.common.domain.entity.BaseEntity;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerClickingHistoryId;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;

public class UrlShortenerClickingHistory extends BaseEntity<UrlShortenerClickingHistoryId> {

    private final UrlShortenerMappingId urlShortenerMappingId;
    private final long clickCounter;

    private UrlShortenerClickingHistory(Builder builder) {
        setId(builder.id);
        urlShortenerMappingId = builder.urlShortenerMappingId;
        clickCounter = builder.clickCounter;
    }

    public UrlShortenerMappingId getUrlMappingId() {
        return urlShortenerMappingId;
    }

    public long getClickCounter() {
        return clickCounter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UrlShortenerClickingHistoryId id;
        private UrlShortenerMappingId urlShortenerMappingId;
        private long clickCounter;

        private Builder() {
        }

        public Builder id(UrlShortenerClickingHistoryId val) {
            id = val;
            return this;
        }

        public Builder urlMappingId(UrlShortenerMappingId val) {
            urlShortenerMappingId = val;
            return this;
        }

        public Builder clickCounter(long val) {
            clickCounter = val;
            return this;
        }

        public UrlShortenerClickingHistory build() {
            return new UrlShortenerClickingHistory(this);
        }
    }
}
