package med.voll.api.domain.doctor;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientData;
import med.voll.api.domain.patient.PatientDetailData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void pickFreeRandomDoctorOnDateScenario1() {
        //given ou arrange
        var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = createDoctor("Doctor", "doctor@vall.med", "12345", Specialty.CARDIOLOGIA);
        var patient = createPatient("Patient", "patient@fakemail.com", "00000000000");
        createAppointment(doctor, patient, nextMondayAt10);

        //when ou act
        var doctorFree = repository.pickFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);

        //then ou assert
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void pickFreeRandomDoctorOnDateScenario2() {
        //given ou arrange
        var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = createDoctor("Doctor", "doctor@vall.med", "12345", Specialty.CARDIOLOGIA);

        //when ou act
        var doctorFree = repository.pickFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);

        //then ou assert
        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(dataDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorData dataDoctor(String name, String email, String crm, Specialty specialty) {
        return new DoctorData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private PatientData dataPatient(String name, String email, String cpf) {
        return new PatientData(
                name,
                email,
                "61999999999",
                cpf,
                dataAddress()
        );
    }

    private AddressData dataAddress() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}