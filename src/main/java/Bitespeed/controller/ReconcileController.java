package Bitespeed.controller;


import Bitespeed.dto.ContactReqDto;
import Bitespeed.dto.contactResponseWrapper;
import Bitespeed.services.ReconcileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/")
public class ReconcileController {

    private ReconcileServiceImpl reconcileService;

    @Autowired
    public ReconcileController(ReconcileServiceImpl reconcileService){
        this.reconcileService=reconcileService;
    }


//    @POST("identify")
//    public ResponseEntity<List<Contact>> identify(@RequestBody Contact contact){
//        ResponseEntity<List<Contact>> response= new ResponseEntity<>(reconcileService.getAllDetails(),
//                HttpStatus.OK);
//        return response;
//        //return null;
//    }

    @PostMapping("identify")
    public ResponseEntity<contactResponseWrapper> identifyDetails(@RequestBody ContactReqDto contactReqDto) {
        contactResponseWrapper responseWrapper=reconcileService.getAllDetails(contactReqDto.getEmail(), contactReqDto.getPhoneNumber() );



        //return null;
        return ResponseEntity.ok(responseWrapper);

        /*
        catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        * */
    }
}
