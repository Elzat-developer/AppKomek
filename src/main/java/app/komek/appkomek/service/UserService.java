package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    void createOrder(int userId, CreateOrderDto orderDtos);

    List<OrderDtos> getOrders(int userId);

    List<PharmacyDto> getPharmacies();

    PharmacyDto getPharmacy(int pharmacyID);

    ProfileDto getProfile(int id);

    void editOrder(Integer orderId, EditOrderDto editOrder);

    void deleteOrder(Integer orderId);
}
