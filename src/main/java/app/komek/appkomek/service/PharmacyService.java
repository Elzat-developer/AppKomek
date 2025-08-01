package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.WarehouseDto;
import app.komek.appkomek.model.entity.Order;
import app.komek.appkomek.model.entity.Warehouse;
import app.komek.appkomek.repository.OrderRepo;
import app.komek.appkomek.repository.WarehouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final WarehouseRepo warehouseRepo;
    private final OrderRepo orderRepo;
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
                order.getUser().getLastName()
        );
    }
}
