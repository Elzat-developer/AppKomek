package app.komek.appkomek.repository;

import app.komek.appkomek.model.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepo extends JpaRepository<Pharmacy,Integer> {
    Pharmacy findById(int pharmacyId);
    Optional<Pharmacy> findByPharmacyName(String pharmacyName);

    @Query("SELECT u.email FROM Pharmacy p JOIN p.users u WHERE p.id = :pharmacyId")
    List<String> findEmailsByPharmacyId(@Param("pharmacyId") Integer pharmacyId);

}
