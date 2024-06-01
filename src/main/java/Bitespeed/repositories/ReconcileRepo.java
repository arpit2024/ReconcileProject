package Bitespeed.repositories;

import Bitespeed.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconcileRepo extends JpaRepository<Contact, Long> {

//        Contact save(Contact contact);

//    Optional<Contact> findByEmail(String email);

//    Optional<Contact> findByPhoneNumber(String phoneNumber);


    List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);

 //   (obv 4)
//    @Modifying
//    @Transactional
//    @Query(value = "ALTER TABLE contact AUTO_INCREMENT = 1 ", nativeQuery = true)
//    void resetAutoIncrement();
}
