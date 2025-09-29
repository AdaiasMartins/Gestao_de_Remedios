package ufpb.dsc.gestao_de_remedios.Customer.Mappers;

import ufpb.dsc.gestao_de_remedios.Customer.DTOs.*;
import ufpb.dsc.gestao_de_remedios.Customer.Models.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerCreateDTO dto) {
        var c = new Customer();
        c.setName(dto.name());
        c.setDocument(dto.document());
        c.setPhone(dto.phone());
        return c;
    }

    public static void updateEntity(Customer c, CustomerUpdateDTO dto) {
        c.setName(dto.name());
        c.setDocument(dto.document());
        c.setPhone(dto.phone());
    }

    public static CustomerResponseDTO toDto(Customer c) {
        return new CustomerResponseDTO(c.getId(), c.getName(), c.getDocument(), c.getPhone());
    }
}
