package com.mehrdad.falahati.common.domain.event.publisher;


import com.mehrdad.falahati.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
