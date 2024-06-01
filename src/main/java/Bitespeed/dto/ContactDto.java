package Bitespeed.dto;

import Bitespeed.models.Contact;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//(OBV 3)
@JsonPropertyOrder({"primaryContactId", "emails", "phoneNumbers", "secondaryContactIds"})
//I am using this class as a response Format
public class ContactDto {

    private Integer primaryContactId;
    private List<String> emails = new ArrayList<>();
    private List<String> phoneNumbersList = new ArrayList<>();
    private List<Integer> secondaryIdList = new ArrayList<>();

    //Here-Mapping contact class to ContactDto for the response body
    public static ContactDto from(Contact primaryContact, List<Contact> contacts) {
        ContactDto contactDto = new ContactDto();
        contactDto.primaryContactId = primaryContact.getId();

        //added the contact in list as it was primary/ added early
        if (primaryContact.getEmail() != null) {
            contactDto.getEmails().add(primaryContact.getEmail());
        }
        if (primaryContact.getPhoneNumber() != null) {
            contactDto.getPhoneNumbersList().add(primaryContact.getPhoneNumber());
        }

        for (Contact contactList : contacts) {
            if (contactList.getLinkPrecedence().equals(Contact.LinkPrecedence.SECONDARY)) {
                contactDto.secondaryIdList.add(contactList.getId());
            }
        //OBV2) here to avoid adding duplicate emails and phone numbers , i should save list of emails and phone numbers
            if (contactList.getEmail() != null && !contactDto.getEmails().contains(contactList.getEmail())) {
                contactDto.getEmails().add(contactList.getEmail());
            }
            if (contactList.getPhoneNumber() != null && !contactDto.getPhoneNumbersList().contains(contactList.getPhoneNumber())) {
                contactDto.getPhoneNumbersList().add(contactList.getPhoneNumber());
            }
        }

        //        if(primarycontact.getEmail()!=null && !contactDto.emailsList.contains(primarycontact.getEmail())){
//            contactDto.emailsList.add(primarycontact.getEmail());
//        }
//
//        if(primarycontact.getPhoneNumber()!=null && !contactDto.phoneNumbersList.contains(primarycontact.getPhoneNumber())){
//            contactDto.phoneNumbersList.add(primarycontact.getPhoneNumber());
//        }

        return contactDto;
    }
}
