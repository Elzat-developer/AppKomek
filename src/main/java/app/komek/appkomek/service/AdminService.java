package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.*;
import app.komek.appkomek.model.entity.Drug;
import app.komek.appkomek.model.entity.Pharmacy;
import app.komek.appkomek.model.entity.User;
import app.komek.appkomek.model.role.Authorities;
import app.komek.appkomek.repository.DrugRepo;
import app.komek.appkomek.repository.PharmacyRepo;
import app.komek.appkomek.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PharmacyRepo pharmacyRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final DrugRepo drugRepo;
    public void createAccountPharmacy(CreateAccountPharmacy createAccountPharmacy) {
        User user = new User();
        user.setName(createAccountPharmacy.firstName());
        user.setSurName(createAccountPharmacy.surName());
        user.setLastName(createAccountPharmacy.lastName());
        user.setEmail(createAccountPharmacy.email());
        user.setPassword(passwordEncoder.encode(createAccountPharmacy.password()));
        user.setPasswordTemporary(true);
        user.setAuthorities(Authorities.PHARMACY);
        user.setPhone(createAccountPharmacy.phone());
        user.setPhotoURL(createAccountPharmacy.photoUrl());

        Pharmacy pharmacy = pharmacyRepo.findByPharmacyName(createAccountPharmacy.pharmacyName())
                .orElseGet(() -> {
                    Pharmacy p = new Pharmacy();
                    p.setPharmacyName(createAccountPharmacy.pharmacyName());
                    p.setPharmacyAddress(createAccountPharmacy.pharmacyAddress());
                    p.setLocation(createAccountPharmacy.pharmacyLocation());
                    p.setPhone(createAccountPharmacy.pharmacyPhone());
                    p.setDescription(createAccountPharmacy.pharmacyDescription());
                    p.setPhotoURL(createAccountPharmacy.pharmacyPhotoUrl());
                    return pharmacyRepo.save(p);
                });

        // Связываем аптеку с пользователем
        user.setPharmacy(pharmacy);

        userRepo.save(user);
    }

    public void createDrug(CreateDrug createDrug) {
        Drug drug = new Drug();
        drug.setName(createDrug.name());
        drug.setDescription(createDrug.description());
        drug.setPhotoURL(createDrug.photoUrl());
        drugRepo.save(drug);
    }

    public void createDelivery(CreateDelivery createDelivery) {
        User delivery = new User();
        delivery.setName(createDelivery.firstName());
        delivery.setSurName(createDelivery.surName());
        delivery.setLastName(createDelivery.lastName());
        delivery.setEmail(createDelivery.email());
        delivery.setPassword(passwordEncoder.encode(createDelivery.password()));
        delivery.setPasswordTemporary(true);
        delivery.setAuthorities(Authorities.DELIVERY);
        delivery.setPhone(createDelivery.phone());
        delivery.setPhotoURL(createDelivery.photoUrl());
        userRepo.save(delivery);
    }

    public List<UsersListDto> getUsersByRole(Authorities role) {
        return userRepo.findAll().stream()
                .filter(user -> user.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals(role.name())))
                .map(this::toDto)
                .toList();
    }
    private UsersListDto toDto(User user){
        return new UsersListDto(
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public List<DrugListDto> getDrugs() {
        List<Drug> drugs = drugRepo.findAll();
        return toDtoListDrug(drugs);
    }

    private List<DrugListDto> toDtoListDrug(List<Drug> drugs) {
        return drugs.stream()
                .map(this::toDtoDrug)
                .toList();
    }

    private DrugListDto toDtoDrug(Drug drug) {
        return new DrugListDto(
                drug.getName(),
                drug.getDescription(),
                drug.getPhotoURL()
        );
    }

    public List<PharmacyDto> getPharmacyList() {
        List<Pharmacy> pharmacies = pharmacyRepo.findAll();
        return pharmacies.stream().map(this::toDtoPharmacy)
                .toList();
    }

    private PharmacyDto toDtoPharmacy(Pharmacy pharmacy) {
        return new PharmacyDto(
                pharmacy.getPharmacyName(),
                pharmacy.getPharmacyAddress(),
                pharmacy.getPhone(),
                pharmacy.getDescription(),
                pharmacy.getLocation(),
                pharmacy.getPhotoURL()
        );
    }
}
