package com.OrionTek.PruebaTecnica.Cliente;

import com.OrionTek.PruebaTecnica.Direccion.Direccion;
import com.OrionTek.PruebaTecnica.Direccion.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/")
public class ClienteController {
    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;
    @Autowired
    public ClienteController(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
    }

    //Endpoints de cliente
    @GetMapping("cliente/get")
    public List<Map> getClientes(){
        List<Map> collection = new ArrayList <Map>();
        clienteRepository.findAll().forEach(cliente -> {
            collection.add(Map.of("id", cliente.getId(), "nombre", cliente.getNombre(), "direcciones", direccionRepository.findAllByClienteId(cliente.getId())));
        });
        return collection;
    }

    @DeleteMapping("cliente/delete")
    public ResponseEntity<String> deleteClientById(@RequestParam Long id){
        clienteRepository.deleteById(id);
        direccionRepository.deleteAllByClienteId(id);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("cliente/post")
    public ResponseEntity<String> newCliente(@RequestBody List<Map> body){
        //        Body ejemplo
        //        [{"nombre" : "clientenombre"},
        //        {"1" : "direccion1",
        //         "2" : "direccion2",
        //         "3" : "direccion3"}]

        Cliente cliente = new Cliente(body.get(0).get("nombre").toString());
        clienteRepository.save(cliente);

        body.get(1).forEach((k,v)->{
            direccionRepository.save(new Direccion(v.toString(), cliente.getId()));
        });

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    //Endpoints de direccion
    @GetMapping("/direccion/get")
    public List<Direccion> getDirecciones(){
        return direccionRepository.findAll();
    }
    @GetMapping("/direccion/getByClienteId")
    public List<String> getDireccionByCliente(@RequestParam Long clienteid){
        return direccionRepository.findAllByClienteId(clienteid);
    }

    @PostMapping("/direccion/post")
    public ResponseEntity<String> addDireccionCliente(@RequestBody List<Map> body){
        body.forEach(v -> {
            direccionRepository.save(new Direccion(v.get("direccion").toString(),Long.decode(v.get("clienteid").toString())));
        });
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/direccion/deleteById")
    public ResponseEntity<String> deleteDireccionById(@RequestParam Long clienteid){
        direccionRepository.deleteById(clienteid);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
