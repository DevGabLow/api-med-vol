package med.voll.api.doctor;

import med.voll.api.address.AddressData;

public record DoctorData(String name, String email, String crm, Specialty specialty, AddressData address) {

}
