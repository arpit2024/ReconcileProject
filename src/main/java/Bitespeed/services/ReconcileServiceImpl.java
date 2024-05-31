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

        //From the database get the contact details based on the email and phone number provided as input
        // in the input we can also use requestDto to get the email and phone number
        List<Contact> contactList = reconcileRepo.findByEmailOrPhoneNumber(mail,phNumber);

        Contact primaryContact=null;

    //if the DB already have a contact with the same email or phone number than assign it to primaryContact variable
        for(Contact var: contactList){
            if(var.getLinkPrecedence().equals(Contact.LinkPrecedence.PRIMARY)){
                primaryContact=var;
                break;
            }
        }

   //if the primaryContact is null than create a new contact and save it to the DB
        if(primaryContact==null) {
            primaryContact = new Contact();
            primaryContact.setEmail(mail);
            primaryContact.setPhoneNumber(phNumber);
            primaryContact.setLinkPrecedence(Contact.LinkPrecedence.PRIMARY);
            primaryContact.setCreatedAt(LocalDateTime.now());
            primaryContact.setUpdatedAt(LocalDateTime.now());
            primaryContact.setDeletedAt(null);

        //save the primaryContact to the DB
            reconcileRepo.save(primaryContact);

    //if the primarycontact is not null and any input data (mail/phnumber) already present in Database
    //than create a new contact row and save the remaing data to the DB
        } else{
            if(!primaryContact.getEmail().equals(mail)||!primaryContact.getPhoneNumber().equals(phNumber)){
                Contact SecondaryContact=new Contact();

                SecondaryContact.setEmail(mail);
                SecondaryContact.setPhoneNumber(phNumber);
                SecondaryContact.setLinkPrecedence(Contact.LinkPrecedence.SECONDARY);
                SecondaryContact.setLinkedId(primaryContact.getId());
                SecondaryContact.setCreatedAt(LocalDateTime.now());
                SecondaryContact.setUpdatedAt(LocalDateTime.now());
                SecondaryContact.setDeletedAt(null);

                reconcileRepo.save(SecondaryContact);
            }
        }

        return ContactDto.from(primaryContact, contactList);
    }
}

//        Contact primaryContact = null;
//        if(primaryContact==null){
//            primaryContact = new Contact();
//        }
//        return null;


