package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByIsDeletedFalse(Pageable pagination);

    @Query("SELECT p.isDeleted FROM Patient p where p.id = :id")
    Boolean findIsDeletedById(Long id);
}
