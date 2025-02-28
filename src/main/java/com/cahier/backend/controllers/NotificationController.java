package com.cahier.backend.controllers;

import com.cahier.backend.dtos.NotificationDTO;
import com.cahier.backend.entities.Notification;
import com.cahier.backend.services.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return notifications.stream()
                .map(n -> new NotificationDTO(n.getId(), n.getContenu(), n.getExpediteur(), n.getDestinataire(), n.getLu()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable String id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(n -> ResponseEntity.ok(new NotificationDTO(n.getId(), n.getContenu(), n.getExpediteur(), n.getDestinataire(), n.getLu())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/unread/{destinataire}")
    public List<NotificationDTO> getUnreadNotifications(@PathVariable String destinataire) {
        List<Notification> notifications = notificationService.getUnreadNotifications(destinataire);
        return notifications.stream()
                .map(n -> new NotificationDTO(n.getId(), n.getContenu(), n.getExpediteur(), n.getDestinataire(), n.getLu()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody @Valid NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setContenu(notificationDTO.getContenu());
        notification.setExpediteur(notificationDTO.getExpediteur());
        notification.setDestinataire(notificationDTO.getDestinataire());

        Notification savedNotification = notificationService.saveNotification(notification);
        return ResponseEntity.ok(new NotificationDTO(savedNotification.getId(), savedNotification.getContenu(), savedNotification.getExpediteur(), savedNotification.getDestinataire(), savedNotification.getLu()));
    }

    @PatchMapping("/mark-as-read/{id}")
    public ResponseEntity<NotificationDTO> markAsRead(@PathVariable String id) {
        Notification updatedNotification = notificationService.markAsRead(id);
        if (updatedNotification != null) {
            return ResponseEntity.ok(new NotificationDTO(updatedNotification.getId(), updatedNotification.getContenu(), updatedNotification.getExpediteur(), updatedNotification.getDestinataire(), updatedNotification.getLu()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
