package Bitespeed.controller;


import Bitespeed.models.Contact;
import Bitespeed.services.ReconcileServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/")
public class ReconcileController {

    private ReconcileServiceImpl reconcileService;

    @RequestMapping("identify")
    public ResponseEntity<List<Contact>> identify(@RequestBody Contact contact){
        ResponseEntity<List<Contact>> response= new ResponseEntity<>(reconcileService.getAllDetails(),
                HttpStatus.OK);
        return response;
        //return null;
    }


}
