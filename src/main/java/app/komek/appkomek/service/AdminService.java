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
    public void createPharmacy(CreatePharmacy createPharmacy) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPharmacyName(createPharmacy.pharmacyName());
        pharmacy.setPharmacyAddress(createPharmacy.pharmacyAddress());
        pharmacy.setLocation(createPharmacy.pharmacyLocation());
        pharmacy.setPhone(createPharmacy.pharmacyPhone());
        pharmacy.setDescription(createPharmacy.pharmacyDescription());
        pharmacy.setPhotoURL(createPharmacy.pharmacyPhotoUrl());
        pharmacyRepo.save(pharmacy);
    }

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
        User user = new User();
        user.setName(createDelivery.firstName());
        user.setSurName(createDelivery.surName());
        user.setLastName(createDelivery.lastName());
        user.setEmail(createDelivery.email());
        user.setPassword(passwordEncoder.encode(createDelivery.password()));
        user.setPasswordTemporary(true);
        user.setAuthorities(Authorities.DELIVERY);
        user.setPhone(createDelivery.phone());
        user.setPhotoURL(createDelivery.photoUrl());
        userRepo.save(user);
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
}
