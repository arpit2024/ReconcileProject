package Bitespeed.services;

import Bitespeed.models.Contact;
import Bitespeed.repositories.ReconcileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReconcileServiceImpl {

    private ReconcileRepo reconcileRepo;

    @Autowired
    public ReconcileServiceImpl(ReconcileRepo reconcileRepo) {
        this.reconcileRepo = reconcileRepo;
    }

    public Contact getAllDetails(String mail, String phNumber) {
        Optional<Contact> contactoptional=reconcileRepo.findByEmailoOrPhoneNumber(mail, phNumber);
        if (contactoptional.isEmpty()) {
            reconcileRepo.save(new Contact(mail,phNumber));
        }
        else if(contactoptional.isPresent()){

        }

        return null;
    }

}
