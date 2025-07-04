package io.github.smfmo.msclients.application;

import io.github.smfmo.msclients.application.representation.ClientSaveRequest;
import io.github.smfmo.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ClientSaveRequest request){
        var client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).body(client);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Object> customerData(@RequestParam("cpf") String cpf){
        var client = service.getByCpf(cpf);
        if (client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
