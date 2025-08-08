package app.komek.appkomek.controller;

import app.komek.appkomek.model.dto.*;
import app.komek.appkomek.model.role.Authorities;
import app.komek.appkomek.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/drug_list")
    public ResponseEntity<List<DrugListDto>> getDrugs(){
        return ResponseEntity.ok(adminService.getDrugs());
    }
    @GetMapping("/user_list")
    public ResponseEntity<List<UsersListDto>> getUsersStatusUser() {
        return ResponseEntity.ok(adminService.getUsersByRole(Authorities.USER));
    }

    @GetMapping("/pharmacy_list")
    public ResponseEntity<List<UsersListDto>> getPharmacyUsers() {
        return ResponseEntity.ok(adminService.getUsersByRole(Authorities.PHARMACY));
    }

    @GetMapping("/delivery_list")
    public ResponseEntity<List<UsersListDto>> getDeliveryUsers() {
        return ResponseEntity.ok(adminService.getUsersByRole(Authorities.DELIVERY));
    }

    @PostMapping("/create_pharmacy")
    public ResponseEntity<String> createPharmacy(CreatePharmacy createPharmacy){
        adminService.createPharmacy(createPharmacy);
        return new ResponseEntity<>("Pharmacy successfully created!", HttpStatus.CREATED);
    }
    @PostMapping("/create_account_pharmacy")
    public ResponseEntity<String> createAccountPharmacy(CreateAccountPharmacy createAccountPharmacy){
        adminService.createAccountPharmacy(createAccountPharmacy);
        return new ResponseEntity<>("Account Pharmacy successfully created!", HttpStatus.CREATED);
    }
    @PostMapping("/create_drug")
    public ResponseEntity<String> createDrug(CreateDrug createDrug){
        adminService.createDrug(createDrug);
        return new ResponseEntity<>("Drug successfully created!", HttpStatus.CREATED);
    }
    @PostMapping("/create_delivery")
    public ResponseEntity<String> createDelivery(CreateDelivery createDelivery){
        adminService.createDelivery(createDelivery);
        return new ResponseEntity<>("Delivery successfully created!", HttpStatus.CREATED);
    }
}
