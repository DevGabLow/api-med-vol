package med.voll.api.doctor;

import med.voll.api.address.AddressData;

public record DoctorData(String name, String mail, String crm, Specialty specialty, AddressData addressData) {
}
