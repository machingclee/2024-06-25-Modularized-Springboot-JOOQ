package com.kotlinspring.restapi.event

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Service


@Service
class EventPublisher : ApplicationEventPublisherAware {
    lateinit var eventPublisher: ApplicationEventPublisher

    fun publishEvent(event: ApplicationEvent) {
        eventPublisher.publishEvent(event)
    }

    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher
    }
}