package com.OrionTek.PruebaTecnica.Direccion;
import com.OrionTek.PruebaTecnica.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    @Query("SELECT d.direccion FROM Direccion d where d.clienteid = ?1")
    List<String> findAllByClienteId(Long clienteid);
}
