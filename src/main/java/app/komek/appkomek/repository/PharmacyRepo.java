package app.komek.appkomek.repository;

import app.komek.appkomek.model.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepo extends JpaRepository<Pharmacy,Integer> {
    Pharmacy findById(int pharmacyId);
}
