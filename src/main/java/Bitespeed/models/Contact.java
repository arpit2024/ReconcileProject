package Bitespeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contact_type",discriminatorType =DiscriminatorType.STRING)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phoneNumber;
    private String email;

    private LocalDateTime createdAt= LocalDateTime.now();;
    private LocalDateTime updatedAt= LocalDateTime.now();;

    private LocalDateTime deletedAt=null;

    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence;

    //set predefined values to declare precedence instance
    public enum LinkPrecedence{
        PRIMARY,
        SECONDARY
    }


}
