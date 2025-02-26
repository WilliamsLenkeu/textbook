package com.cahier.backend.repositories;

import com.cahier.backend.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByDestinataireAndLu(String destinataire, int lu);
}
