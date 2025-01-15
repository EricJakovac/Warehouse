package com.backend.warehouse.service;

import com.backend.warehouse.model.Warehouse;
import com.backend.warehouse.repository.WarehouseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long warehouseId){
        return warehouseRepository.findById(warehouseId);
    }

    public Warehouse addWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long warehouseId, @NotNull Warehouse warehouseDetails){
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();

        warehouse.setWarehouseName(Objects.requireNonNull(warehouseDetails).getWarehouseName());
        warehouse.setLocation(Objects.requireNonNull(warehouseDetails).getLocation());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long warehouseId){
        warehouseRepository.deleteById(warehouseId);
    }
}
