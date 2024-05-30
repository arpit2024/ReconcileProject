package Bitespeed.services;

import Bitespeed.dto.ContactDto;
import Bitespeed.models.Contact;
import Bitespeed.repositories.ReconcileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReconcileServiceImpl {

    private ReconcileRepo reconcileRepo;

    @Autowired
    public ReconcileServiceImpl(ReconcileRepo reconcileRepo) {
        this.reconcileRepo = reconcileRepo;
    }


//    public Contact save(Contact contact){
//        return reconcileRepo.save(contact);
//    }
    public ContactDto getAllDetails(String mail, String phNumber) {
        List<Contact> contacts = reconcileRepo.findByEmailOrPhoneNumber(mail,phNumber);

        Contact primaryContact = new Contact();
        primaryContact.setEmail(mail);
        primaryContact.setPhoneNumber(phNumber);
        primaryContact.setLinkPrecedence(Contact.LinkPrecedence.PRIMARY);
        primaryContact.setCreatedAt(LocalDateTime.now());
        primaryContact.setUpdatedAt(LocalDateTime.now());
        primaryContact.setDeletedAt(null);

        reconcileRepo.save(primaryContact);

        return ContactDto.from(primaryContact, contacts);

//        Contact primaryContact = null;
//        if(primaryContact==null){
//            primaryContact = new Contact();
//        }
//        return null;

    }
}
