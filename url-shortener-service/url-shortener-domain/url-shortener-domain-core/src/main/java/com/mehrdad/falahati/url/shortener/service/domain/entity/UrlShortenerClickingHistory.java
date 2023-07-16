package com.mehrdad.falahati.url.shortener.service.domain.entity;

import com.mehrdad.falahati.common.domain.entity.BaseEntity;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerClickingHistoryId;

public class UrlShortenerClickingHistory extends BaseEntity<UrlShortenerClickingHistoryId> {

    private final UrlShortenerMapping urlShortenerMapping;
    private final long clickCounter;

    private UrlShortenerClickingHistory(Builder builder) {
        setId(builder.id);
        urlShortenerMapping = builder.urlShortenerMapping;
        clickCounter = builder.clickCounter;
    }

    public UrlShortenerMapping getUrlShortenerMapping() {
        return urlShortenerMapping;
    }

    public long getClickCounter() {
        return clickCounter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UrlShortenerClickingHistoryId id;
        private UrlShortenerMapping urlShortenerMapping;
        private long clickCounter;

        private Builder() {
        }

        public Builder id(UrlShortenerClickingHistoryId val) {
            id = val;
            return this;
        }

        public Builder urlShortenerMapping(UrlShortenerMapping val) {
            urlShortenerMapping = val;
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
