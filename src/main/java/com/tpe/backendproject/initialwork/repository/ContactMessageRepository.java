package com.tpe.backendproject.initialwork.repository;

import com.tpe.backendproject.initialwork.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
}
