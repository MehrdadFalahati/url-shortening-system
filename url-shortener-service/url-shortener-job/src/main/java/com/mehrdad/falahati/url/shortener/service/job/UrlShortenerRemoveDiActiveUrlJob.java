package com.mehrdad.falahati.url.shortener.service.job;

import com.mehrdad.falahati.url.shortener.service.domain.port.input.job.DiActiveUrlRemover;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class UrlShortenerRemoveDiActiveUrlJob {
    private final DiActiveUrlRemover diActiveUrlRemover;

    @Scheduled(cron = "${app.scheduled.time}")
    void execute() {
        diActiveUrlRemover.removeDiActiveUrl();
    }
}
