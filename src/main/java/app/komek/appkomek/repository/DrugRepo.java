package app.komek.appkomek.repository;

import app.komek.appkomek.model.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepo extends JpaRepository<Drug,Integer> {
}
