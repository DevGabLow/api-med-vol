package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;

    private String district;

    private String cep;

    private String number;

    private String complement;

    private String city;

    private String uf;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.street();
        this.cep = address.cep();
        this.number = address.number();
        this.complement = address.complement();
        this.city = address.city();
        this.uf = address.uf();
    }

    public void updateInfos(AddressData form) {
        if(form.street() != null){
            this.street = form.street();
        }
        if(form.district() != null){
            this.district = form.district();
        }
        if(form.cep() != null){
            this.cep = form.cep();
        }
        if(form.number() != null){
            this.number = form.number();
        }
        if(form.complement() != null){
            this.complement = form.complement();
        }
        if(form.city() != null){
            this.city = form.city();
        }
        if(form.uf() != null){
            this.uf = form.uf();
        }
    }
}
