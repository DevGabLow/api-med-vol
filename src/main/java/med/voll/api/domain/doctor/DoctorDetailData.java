package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailData(Long id, String name, String email, String crm,String cellPhone, Specialty specialty, Address address) {
    public DoctorDetailData(Doctor doctor){
        this(doctor.getId(),doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getCellPhone(),doctor.getSpecialty(), doctor.getAddress());
    }
}
