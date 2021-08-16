package br.com.dio.springwebmvcintermediario.controller;

import br.com.dio.springwebmvcintermediario.controller.request.SoldadoEditRequest;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoListResponse;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoResponse;
import br.com.dio.springwebmvcintermediario.dto.Soldado;
import br.com.dio.springwebmvcintermediario.entity.SoldadoEntity;
import br.com.dio.springwebmvcintermediario.repository.SoldadoRepository;
import br.com.dio.springwebmvcintermediario.resource.ResourceSoldado;
import br.com.dio.springwebmvcintermediario.service.SoldadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {

    @Autowired
    private SoldadoService soldadoService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable() Long id) {
        SoldadoResponse soldadoResponse = soldadoService.buscarSoldado(id);
        return ResponseEntity.status(HttpStatus.OK).body(soldadoResponse);
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody Soldado soldado) {
        soldadoService.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editarSoldado(@PathVariable() Long id,
                                        @RequestBody SoldadoEditRequest soldadoEditRequest) {
        soldadoService.alterarSoldado(id, soldadoEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSoldado(@PathVariable Long id) {
        soldadoService.deletarSoldado(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/frente-castelo/{id}")
    public ResponseEntity frenteCastelo(@PathVariable Long id) {
        soldadoService.deletarSoldado(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SoldadoListResponse>> buscarSoldados() {
        List<SoldadoListResponse> soldadoListResponses = soldadoService.buscarSoldados();
        return ResponseEntity.status(HttpStatus.OK).body(soldadoListResponses);
    }

}
