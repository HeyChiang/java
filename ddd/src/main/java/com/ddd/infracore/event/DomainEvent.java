package com.ddd.infracore.event;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件
 * @author jianghao
 */
public abstract class DomainEvent extends ApplicationEvent {

  /**
   * 领域事件ID
   */
  private String eventId;

  /**
   * 事件发生的时间
   */
  private LocalDateTime occurTime;

  public String getEventName() {
    return (String) this.source;
  }

  public DomainEvent(Object source) {
    super(source);
    eventId = UUID.randomUUID().toString();
    occurTime = LocalDateTime.now();
  }

  public abstract String key();

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public LocalDateTime getOccurTime() {
    return occurTime;
  }

  public void setOccurTime(LocalDateTime occurTime) {
    this.occurTime = occurTime;
  }
}
