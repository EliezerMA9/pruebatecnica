package com.OrionTek.PruebaTecnica.Direccion;

import com.OrionTek.PruebaTecnica.Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Direccion {
    @Id
    @SequenceGenerator(
            name = "direccion_sequence",
            sequenceName = "direccion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "direccion_sequence"
    )
    private Long id;
    private String direccion;

    private Long clienteid;

    public Direccion() {
    }

    public Direccion(String direccion, Long cliente) {
        this.direccion = direccion;
        this.clienteid = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCliente() {
        return clienteid;
    }

    public void setCliente(Long cliente) {
        this.clienteid = cliente;
    }
}
