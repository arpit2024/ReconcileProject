package Bitespeed.services;

import Bitespeed.dto.ContactDto;
import Bitespeed.dto.contactResponseWrapper;
import Bitespeed.models.Contact;
import Bitespeed.repositories.ReconcileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReconcileServiceImpl {

    private final ReconcileRepo reconcileRepo;

    @Autowired
    public ReconcileServiceImpl(ReconcileRepo reconcileRepo) {
        this.reconcileRepo = reconcileRepo;
    }

    //    public Contact save(Contact contact){
//        return reconcileRepo.save(contact);
//    }


    public contactResponseWrapper getAllDetails(String email, String phoneNumber) {
        List<Contact> contactList = reconcileRepo.findByEmailOrPhoneNumber(email, phoneNumber);

//From the database get the contact details based on the email and phone number provided as input
//in the input we can also use requestDto to get the email and phone number

        Contact primaryContact = null;

//if the DB already have a contact with the same email or phone number than assign
// it to primaryContact variable
        for (Contact contact : contactList) {
            if (contact.getLinkPrecedence().equals(Contact.LinkPrecedence.PRIMARY)) {
                primaryContact = contact;
                break;
            }
        }

// if the primaryContact is null than create a new contact and save it to the DB
        if (primaryContact == null) {
            primaryContact = new Contact();
            primaryContact.setEmail(email);
            primaryContact.setPhoneNumber(phoneNumber);
            primaryContact.setLinkPrecedence(Contact.LinkPrecedence.PRIMARY);
            primaryContact.setCreatedAt(LocalDateTime.now());
            primaryContact.setUpdatedAt(LocalDateTime.now());
            primaryContact.setDeletedAt(null);

        //save the primaryContact to the DB
            reconcileRepo.save(primaryContact);

        }
    //if the primarycontact is not null and any input data (mail/phnumber) already present in Database
    //than create a new contact row and save the remaing data to the DB
        else{
            boolean isUpdated = false;
// My reference - previously had only 1 if loop with in else cae- where condition was written using or
//but now trying to differentiate the loops - based on observation
// this is req1-> a, b, req2-> c,d, req3-> a,d

//Request 3
// so in 1st if loop check-. for a primary contact  phone number, if mail is present in DB and the input/new request  mail is different
//than create a new contact as secondary,
            if (email != null && !email.equals(primaryContact.getEmail())) {
            // considering primarycontact phonenumber's linked mail
                Contact secondaryContact = new Contact();
                secondaryContact.setEmail(email);
            // here for the new req using primary contact phno- as only mail id differs
                secondaryContact.setPhoneNumber(primaryContact.getPhoneNumber());
                secondaryContact.setLinkPrecedence(Contact.LinkPrecedence.SECONDARY);
                secondaryContact.setLinkedId(primaryContact.getId());
                secondaryContact.setCreatedAt(LocalDateTime.now());
                secondaryContact.setUpdatedAt(LocalDateTime.now());
                secondaryContact.setDeletedAt(null);

                reconcileRepo.save(secondaryContact);
                isUpdated = true;
            }

//Request 3
//similarly for primary contact -> mail id- phone number is present but the new request has different phone number than create
//a new contact as secondary
            if (phoneNumber != null && !phoneNumber.equals(primaryContact.getPhoneNumber())) {
                Contact secondaryContact = new Contact();
                secondaryContact.setEmail(primaryContact.getEmail());
                secondaryContact.setPhoneNumber(phoneNumber);
                secondaryContact.setLinkPrecedence(Contact.LinkPrecedence.SECONDARY);
                secondaryContact.setLinkedId(primaryContact.getId());
                secondaryContact.setCreatedAt(LocalDateTime.now());
                secondaryContact.setUpdatedAt(LocalDateTime.now());
                secondaryContact.setDeletedAt(null);
                reconcileRepo.save(secondaryContact);
                isUpdated = true;
            }

    //since changes are made
            if (isUpdated) {
                contactList = reconcileRepo.findByEmailOrPhoneNumber(email, phoneNumber);
            }
        }

        ContactDto contactDto = ContactDto.from(primaryContact, contactList);
        return new contactResponseWrapper(contactDto);
    }
}



//    public void resetAutoIncrement(){
//        reconcileRepo.resetAutoIncrement();
//    }


//        Contact primaryContact = null;
//        if(primaryContact==null){
//            primaryContact = new Contact();
//        }
//        return null;


