package Bitespeed.dto;


import lombok.Getter;
import lombok.Setter;


//(Obv 6)
@Getter
@Setter
public class contactResponseWrapper {

    ContactDto contact;

    public contactResponseWrapper(ContactDto contactDto) {
        this.contact = contactDto;
    }

}
