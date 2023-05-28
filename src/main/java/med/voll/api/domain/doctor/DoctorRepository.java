package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByIsDeletedFalse(Pageable pagination);

    @Query("SELECT d FROM Doctor  d WHERE d.isDeleted = false AND d.specialty = :SPECIALTY AND d.id NOT IN ( SELECT a.doctor.id FROM Appointment a WHERE a.date = :DATE_FREE and a.reason IS NULL) ORDER BY rand() LIMIT 1")
    Doctor pickFreeRandomDoctorOnDate(@Param("SPECIALTY") Specialty specialty,@Param("DATE_FREE") LocalDateTime date);

    @Query("SELECT d.isDeleted FROM Doctor d where d.id = :id")
    Boolean findIsDeletedById(Long id);
}
