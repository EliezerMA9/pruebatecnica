package com.OrionTek.PruebaTecnica.Cliente;

import com.OrionTek.PruebaTecnica.Direccion.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//    Map<k,v>saveClienteYDireccion();
}
