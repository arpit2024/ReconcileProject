package Bitespeed.repositories;

import Bitespeed.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReconcileRepo extends JpaRepository<Contact, Long> {

    Contact save(Contact contact);

//    Optional<Contact> findByEmail(String email);

//    @Override
//    Optional<Contact> findByPhoneNumber(String phoneNumber);

    Optional<Contact> findByEmailoOrPhoneNumber(String email, String phoneNumber);
}
