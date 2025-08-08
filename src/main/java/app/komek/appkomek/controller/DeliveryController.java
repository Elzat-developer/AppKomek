package app.komek.appkomek.controller;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.OrderListDto;
import app.komek.appkomek.model.dto.ProfileDto;
import app.komek.appkomek.model.entity.User;
import app.komek.appkomek.service.DeliveryService;
import app.komek.appkomek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final UserService userService;
    private final DeliveryService deliveryService;
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable User user){
        return ResponseEntity.ok(userService.getProfile(user.getId()));
    }
    @GetMapping("/orders/create")
    public ResponseEntity<List<OrderListDto>> getOrdersCreate(){
        return new ResponseEntity<>(deliveryService.getOrders("CREATE"), HttpStatus.OK);
    }
    @GetMapping("/orders/in_progress")
    public ResponseEntity<List<OrderListDto>> getOrdersIn_progress(){
        return new ResponseEntity<>(deliveryService.getOrders("IN_PROGRESS"), HttpStatus.OK);
    }
    @GetMapping("/orders/ready")
    public ResponseEntity<List<OrderListDto>> getOrdersReady(){
        return new ResponseEntity<>(deliveryService.getOrders("READY"), HttpStatus.OK);
    }
    @GetMapping("/orders/done")
    public ResponseEntity<List<OrderListDto>> getOrdersDone(){
        return new ResponseEntity<>(deliveryService.getOrders("DONE"), HttpStatus.OK);
    }
    @GetMapping("/order/{orderId}/")
    public ResponseEntity<OrderDtos> getOrder(@PathVariable Integer orderId){
        return new ResponseEntity<>(deliveryService.getOrder(orderId),HttpStatus.OK);
    }
    @PostMapping("/scan")
    public ResponseEntity<String> scanQrCode(@RequestParam String qrCode) {
        deliveryService.scanQrCode(qrCode);
        return ResponseEntity.ok("Order Status Ready");
    }

}
