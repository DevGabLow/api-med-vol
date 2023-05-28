package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
