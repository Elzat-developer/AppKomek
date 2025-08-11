package app.komek.appkomek.controller;

import app.komek.appkomek.model.dto.CreateOrderDto;
import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.PharmacyDto;
import app.komek.appkomek.model.dto.ProfileDto;
import app.komek.appkomek.model.entity.User;
import app.komek.appkomek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable User user){
        return ResponseEntity.ok(userService.getProfile(user.getId()));
    }
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderDtos>> getOrders(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getOrders(userId));
    }
    @GetMapping("/pharmacies")
    public ResponseEntity<List<PharmacyDto>> getPharmacies(){
        return ResponseEntity.ok(userService.getPharmacies());
    }
    @GetMapping("/pharmacies/{pharmacyID}")
    public ResponseEntity<PharmacyDto> getPharmacy(@PathVariable int pharmacyID){
        return ResponseEntity.ok(userService.getPharmacy(pharmacyID));
    }
    @PostMapping("/{userId}/create_order")
    public ResponseEntity<String> createOrder(@PathVariable Integer userId,@RequestBody CreateOrderDto orderDtos){
        userService.createOrder(userId,orderDtos);
        return new ResponseEntity<>("Order successfully created!", HttpStatus.CREATED);
    }
}
