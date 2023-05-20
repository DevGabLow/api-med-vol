package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String cellPhone;

    private String crm;

    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorData form) {
        this.name = form.name();
        this.email = form.email();
        this.cellPhone = form.cellPhone();
        this.crm = form.crm();
        this.specialty = form.specialty();
        this.address = new Address(form.address());
        this.isDeleted = false;
    }

    public void updateInfos(DoctorUpdateData form) {
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
