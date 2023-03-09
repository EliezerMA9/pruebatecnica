package com.OrionTek.PruebaTecnica.Cliente;

import com.OrionTek.PruebaTecnica.Direccion.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;
    @Autowired
    public ClienteController(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
    }
    @GetMapping
    public List<Map> getClientes(){
        List<Map> collection = new ArrayList <Map>();
        clienteRepository.findAll().forEach(cliente -> {
            collection.add(Map.of("id", cliente.getId(), "nombre", cliente.getNombre(), "direcciones", direccionRepository.findAllByClienteId(cliente.getId())));
        });
        return collection;
    }

    @PostMapping
    public void newCliente(@RequestBody Map body){
        clienteRepository.save(cliente);
    }
}
