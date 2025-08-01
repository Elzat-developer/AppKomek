package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.PharmacyDto;
import app.komek.appkomek.model.dto.ProfileDto;
import app.komek.appkomek.model.entity.Order;
import app.komek.appkomek.model.entity.Pharmacy;
import app.komek.appkomek.model.entity.User;
import app.komek.appkomek.model.status.OrderStatus;
import app.komek.appkomek.repository.OrderRepo;
import app.komek.appkomek.repository.PharmacyRepo;
import app.komek.appkomek.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final PharmacyRepo pharmacyRepo;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepo.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void createOrder(OrderDtos orderDtos) {
        Order order = new Order();
        order.setUserFullName(order.getUserFullName());
        order.setPharmacyName(order.getPharmacyName());
        order.setPharmacyAddress(order.getPharmacyAddress());
        order.setDrugName(order.getDrugName());
        order.setCount(orderDtos.count());
        order.setCreateOrder(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATE);
        orderRepo.save(order);
    }

    @Override
    public List<OrderDtos> getOrders() {
        List<Order> orders = orderRepo.findAll();
        return toDtoListOrders(orders);
    }

    @Override
    public List<PharmacyDto> getPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepo.findAll();
        return toDtoListPharmacies(pharmacies);
    }

    @Override
    public PharmacyDto getPharmacy(int pharmacyID) {
        Pharmacy pharmacy = pharmacyRepo.findById(pharmacyID);
        return toDtoPharmacy(pharmacy);
    }

    @Override
    public ProfileDto getProfile(int user_id) {
        User user = userRepo.findById(user_id);
        return toDtoUser(user);
    }

    private ProfileDto toDtoUser(User user) {
        return new ProfileDto(
                user.getName(),
                user.getSurName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhotoURL(),
                user.getPhone()
        );
    }

    private List<PharmacyDto> toDtoListPharmacies(List<Pharmacy> pharmacies) {
        return pharmacies.stream()
                .map(this::toDtoPharmacy)
                .toList();
    }

    private PharmacyDto toDtoPharmacy(Pharmacy pharmacy) {
        return new PharmacyDto(
                pharmacy.getPharmacyName(),
                pharmacy.getAddress(),
                pharmacy.getPhone(),
                pharmacy.getDescription(),
                pharmacy.getLocation(),
                pharmacy.getPhotoURL()
        );
    }

    private List<OrderDtos> toDtoListOrders(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .toList();
    }

    private OrderDtos toDto(Order order) {
        return new OrderDtos(
                order.getUserFullName(),
                order.getPharmacyName(),
                order.getPharmacyAddress(),
                order.getDrugName(),
                order.getCount()
        );
    }

}
