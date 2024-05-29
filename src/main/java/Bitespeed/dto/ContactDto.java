package Bitespeed.dto;

import Bitespeed.models.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ContactDto {

    private String PrimaryContactId;
    private List<String> emails;

    private List<String> phoneNumbers;

    private List<Integer> secondaryContactId;

    //Here-Mapping contact class to ContactDto for the response body
    public static ContactDto from(Contact primarycontact, List<Contact> secondaryContacts){
        if(primarycontact==null){
            return null;
        }


        ContactDto contactDto = new ContactDto();
        contactDto.setPrimaryContactId(String.valueOf(primarycontact.getId()));

        //here i am adding the primary details at the first of list individually
        List<String> emails = new ArrayList<>();
        emails.add(primarycontact.getEmail());

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(primarycontact.getPhoneNumber());

        List<Integer> secondaryContactIds = new ArrayList<>();

// here i am traversing through the list of Secondary contact row and adding them
// to my Dto list as per the Requirement
        for (Contact contact : secondaryContacts) {
            if (contact.getEmail() != null) {
                emails.add(contact.getEmail());
            }
            if (contact.getPhoneNumber() != null) {
                phoneNumbers.add(contact.getPhoneNumber());
            }
            secondaryContactIds.add(contact.getId());
        }

//After the traversal the lists are set to Dto object
        contactDto.setEmails(emails);
        contactDto.setPhoneNumbers(phoneNumbers);
        contactDto.setSecondaryContactId(secondaryContactIds);

        return contactDto;

    }

}
