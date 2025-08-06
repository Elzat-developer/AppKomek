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
    @PostMapping("/{orderId}/order_fulfillment")
    public ResponseEntity<String> orderFulfillment(
            @PathVariable Integer orderId,
            @RequestParam Integer pharmacyId,
            @RequestParam Integer deliveryId
            ){
        pharmacyService.orderFulfillment(orderId,pharmacyId,deliveryId);
        return new ResponseEntity<>("Order fulfillment sent!", HttpStatus.ACCEPTED);
    }
    @PostMapping("/{orderId}/reserve_drug_warehouse")
    public ResponseEntity<String> addDrugWarehouseOrder(@PathVariable Integer orderId) {
        pharmacyService.addDrugWarehouseOrder(orderId);
        return new ResponseEntity<>("Drug add warehouse!",HttpStatus.ACCEPTED);
    }
    @PostMapping("/{orderId}/give_out_drug")
    public ResponseEntity<String> giveOutDrug(@PathVariable Integer orderId){
        pharmacyService.giveOutDrug(orderId);
        return new ResponseEntity<>("The order has been completed.",HttpStatus.ACCEPTED);
    }
}
