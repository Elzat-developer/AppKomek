package app.komek.appkomek.controller;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.WarehouseDto;
import app.komek.appkomek.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;
    @PostMapping("/add_drug_warehouse")
    public ResponseEntity<String> addDrugWarehouse(WarehouseDto warehouseDto){
        pharmacyService.addDrugWarehouse(warehouseDto);
        return new ResponseEntity<>("Drug add Warehouse successfully!", HttpStatus.CREATED);
    }
    @GetMapping("/pharmacies/{pharmacyId}/orders")
    public ResponseEntity<List<OrderDtos>> getOrdersByPharmacy(@PathVariable Integer pharmacyId) {
        return ResponseEntity.ok(pharmacyService.getOrdersByPharmacy(pharmacyId));
    }
}
