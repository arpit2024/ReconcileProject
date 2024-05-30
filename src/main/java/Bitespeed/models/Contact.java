package Bitespeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "contact")

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phoneNumber;
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer linkedId;
    private LocalDateTime deletedAt;


    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence;

    //set predefined values to declare precedence instance
    public enum LinkPrecedence{
        PRIMARY,
        SECONDARY
    }


}
