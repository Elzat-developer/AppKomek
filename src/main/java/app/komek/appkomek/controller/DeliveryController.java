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
    public ResponseEntity<?> getOrdersCreate(){
        List<OrderListDto> orders = deliveryService.getOrders("CREATE");
        if (orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не Найдено!");
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }
    @GetMapping("/orders/in_progress")
    public ResponseEntity<?> getOrdersIn_progress(){
        List<OrderListDto> orders = deliveryService.getOrders("IN_PROGRESS");
        if (orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не Найдено!");
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }
    @GetMapping("/orders/ready")
    public ResponseEntity<?> getOrdersReady(){
        List<OrderListDto> orders = deliveryService.getOrders("READY");
        if (orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не Найдено!");
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }
    @GetMapping("/orders/done")
    public ResponseEntity<?> getOrdersDone(){
        List<OrderListDto> orders = deliveryService.getOrders("DONE");
        if (orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не Найдено!");
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDtos> getOrder(@PathVariable Integer orderId){
        return new ResponseEntity<>(deliveryService.getOrder(orderId),HttpStatus.OK);
    }
    @PostMapping("/scan")
    public ResponseEntity<String> scanQrCode(@RequestParam String qrCode) {
        deliveryService.scanQrCode(qrCode);
        return ResponseEntity.ok("Order Status Ready");
    }

}
