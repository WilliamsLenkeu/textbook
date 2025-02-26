package com.cahier.backend.services;

import com.cahier.backend.entities.Notification;
import com.cahier.backend.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(String id) {
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    public List<Notification> getUnreadNotifications(String destinataire) {
        return notificationRepository.findByDestinataireAndLu(destinataire, 0);
    }

    public Notification markAsRead(String id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setLu(1);
            return notificationRepository.save(notification);
        }
        return null;
    }
}
