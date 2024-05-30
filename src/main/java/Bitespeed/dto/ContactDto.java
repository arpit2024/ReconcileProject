package Bitespeed.dto;

import Bitespeed.models.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

//I am using this class as a response Format
public class ContactDto {

    private Integer PrimaryContactId;
    private List<String> emailsList=new ArrayList<>();

    private List<String> phoneNumbersList=new ArrayList<>();

    private List<Integer> secondaryIds=new ArrayList<>();

    //Here-Mapping contact class to ContactDto for the response body
    public static ContactDto from(Contact primarycontact, List<Contact> secondaryContacts){

        ContactDto contactDto = new ContactDto();
        contactDto.PrimaryContactId=primarycontact.getId();

        for(Contact listContact : secondaryContacts){
            if(listContact.getLinkPrecedence().equals(Contact.LinkPrecedence.SECONDARY)){
                contactDto.secondaryIds.add(listContact.getId());
            }

            if(listContact.getEmail()!=null && !contactDto.emailsList.contains(listContact)){
                contactDto.emailsList.add(listContact.getEmail());
            }

            if(listContact.getPhoneNumber()!=null && !contactDto.phoneNumbersList.contains(listContact)){
                contactDto.phoneNumbersList.add(listContact.getPhoneNumber());
            }
        }

        if(primarycontact.getEmail()!=null && !contactDto.emailsList.contains(primarycontact.getEmail())){
            contactDto.emailsList.add(primarycontact.getEmail());
        }

        if(primarycontact.getPhoneNumber()!=null && !contactDto.phoneNumbersList.contains(primarycontact.getPhoneNumber())){
            contactDto.phoneNumbersList.add(primarycontact.getPhoneNumber());
        }

        return contactDto;
    }

}
