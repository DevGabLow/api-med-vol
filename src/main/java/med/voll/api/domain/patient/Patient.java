package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@Table(name = "patient")
@Entity(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String cellPhone;

    private String cpf;

    private Boolean isDeleted;

    @Embedded
    private Address address;

    public Patient(PatientData form) {
        this.isDeleted = false;
        this.name = form.name();
        this.email = form.email();
        this.cellPhone = form.cellPhone();
        this.cpf = form.cpf();
        this.address = new Address(form.address());
    }

    public void updateInfos(PatientUpdateData form) {
        if(form.name() != null){
            this.name = form.name();
        }
        if(form.cellPhone() != null){
            this.cellPhone = form.cellPhone();
        }
        if(form.address() != null){
            this.address.updateInfos(form.address());
        }
    }

    public void delete() {
        this.isDeleted = true;
    }
}
