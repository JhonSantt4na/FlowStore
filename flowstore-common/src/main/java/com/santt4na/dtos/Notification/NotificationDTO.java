package com.santt4na.dtos.Notification;

import com.santt4na.enums.TypeNotification;

public record NotificationDTO(Long id, String UserId, TypeNotification type, String content, String sendAt) {
}