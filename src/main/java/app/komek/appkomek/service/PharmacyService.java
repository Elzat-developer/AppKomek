package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.*;
import app.komek.appkomek.model.entity.*;
import app.komek.appkomek.model.status.OrderStatus;
import app.komek.appkomek.repository.OrderRepo;
import app.komek.appkomek.repository.PharmacyRepo;
import app.komek.appkomek.repository.UserRepo;
import app.komek.appkomek.repository.WarehouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final WarehouseRepo warehouseRepo;
    private final OrderRepo orderRepo;
    private final PharmacyRepo pharmacyRepo;
    private final MailSenderService mailSenderService;
    private final UserRepo userRepo;

    public void addDrugWarehouse(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDrugName(warehouseDto.drugName());
        warehouse.setCount(warehouseDto.count());
        warehouse.setCreateData(LocalDateTime.now());
        warehouseRepo.save(warehouse);
    }

    public List<OrderDtos> getOrdersByPharmacy(Integer pharmacyId) {
        List<Order> orders = orderRepo.findByPharmacyId(pharmacyId);
        return orders.stream()
                .map(this::toDto)
                .toList();
    }

    private OrderDtos toDto(Order order) {
        return new OrderDtos(
                order.getPharmacy().getPharmacyName(),
                order.getPharmacy().getPharmacyAddress(),
                order.getDrugName(),
                order.getCount(),
                order.getUser().getId(),
                order.getUser().getName(),
                order.getUser().getSurName(),
                order.getUser().getLastName(),
                order.getCreateOrder()
        );
    }
    @Transactional
    public void orderFulfillment(int orderId,int pharmacyId,int deliveryId) {
        Order order = orderRepo.findById(orderId);
        Pharmacy pharmacy = pharmacyRepo.findById(pharmacyId);
        User delivery = userRepo.findById(deliveryId);
        order.setPharmacy(pharmacy);
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUser(delivery);
        orderRepo.save(order);

        List<CreateDrug> drugList = order.getDrugs().stream()
                .map(this::toDtoDrug)
                .toList();

        String subject = String.format(
                "Мына %s Дәріханаға тапсырыс берілді",
                order.getPharmacy().getPharmacyName()
        );

        String message = String.format(
                "Құрметті курьер сізге мына Аптекадан: %s " +
                        "адресі: %s " +
                        "телефоны: %s " +
                        "орналасқан-орны: %s " +
                        "фотосы: %s." +
                        "Дәрі-дәрмекті: Аты: %s Саны: %d " +
                        "Заказ беруші аптекаға апаруыңызды сұраймын. " +
                        "Төменде апару керек аптека жайлы ақпарат" +
                        "Аптека атауы %s мекенжайы %s Тапсырыс беруші %s %s %s " +
                        "Тапсырыс берген уақыты %s",
                pharmacy.getPharmacyName(),
                pharmacy.getPharmacyAddress(),
                pharmacy.getPhone(),
                pharmacy.getLocation(),
                pharmacy.getPhotoURL(),
                drugList,
                order.getCount(),
                order.getPharmacy().getPharmacyName(),
                order.getPharmacy().getPharmacyAddress(),
                order.getUser().getName(),
                order.getUser().getSurName(),
                order.getUser().getLastName(),
                order.getCreateOrder().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        );

        mailSenderService.send(
                delivery.getEmail(),
                subject,
                message
        );
    }

    private CreateDrug toDtoDrug(Drug drug) {
        return new CreateDrug(
                drug.getName(),
                drug.getDescription(),
                drug.getPhotoURL()
        );
    }

    public void addDrugWarehouseOrder(int orderId) {
        Order order = orderRepo.findById(orderId);
        Pharmacy pharmacy = pharmacyRepo.findById(order.getPharmacy().getId());

        if(order.getOrderStatus() != OrderStatus.READY) {
            throw new RuntimeException("Order not delivered yet");
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setDrugName(order.getDrugName());
        warehouse.setReserveCount(order.getCount());
        warehouse.setCreateData(LocalDateTime.now());
        pharmacy.setWarehouse(warehouse);
        pharmacyRepo.save(pharmacy);

        String message = String.format(
                "Уважаемый пользователь %s Ваш заказ %s количество %d" +
                        "в Аптеке %s адрес %s телефон %s место нахождение %s и фото %s" +
                        "Прошу забрать его в тичени 3 рабочих дней! По истичению 3 рабочих дней ваш заказ автоматически отменится!",
                order.getUser().getName(), order.getDrugName(), order.getCount(),
                pharmacy.getPharmacyName(), pharmacy.getPharmacyAddress(), pharmacy.getPhone(), pharmacy.getLocation(), pharmacy.getPhotoURL()
        );

        mailSenderService.send(
                order.getUser().getEmail(),
                "Можете забрать ваш заказ",
                message

        );
    }

    public void giveOutDrug(int orderId) {
        Order order = orderRepo.findById(orderId);
        order.setDeleteOrder(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.DONE);
        orderRepo.save(order);

        String message = String.format(
          "Спасибо что заказали у нас %s",
          order.getPharmacy().getPharmacyName()
        );

        mailSenderService.send(
                order.getUser().getEmail(),
                "Вам выдан ваш заказ",
                message

        );
    }
}
