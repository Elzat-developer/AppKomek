package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.PharmacyDto;
import app.komek.appkomek.model.dto.ProfileDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    void createOrder(OrderDtos orderDtos);

    List<OrderDtos> getOrders();

    List<PharmacyDto> getPharmacies();

    PharmacyDto getPharmacy(Integer pharmacyID);

    ProfileDto getProfile(int id);
}
