package br.com.dio.springwebmvcintermediario.service;

import br.com.dio.springwebmvcintermediario.controller.request.SoldadoEditRequest;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoListResponse;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoResponse;
import br.com.dio.springwebmvcintermediario.dto.Soldado;
import br.com.dio.springwebmvcintermediario.entity.SoldadoEntity;
import br.com.dio.springwebmvcintermediario.repository.SoldadoRepository;
import br.com.dio.springwebmvcintermediario.resource.ResourceSoldado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoldadoService {

    @Autowired
    private SoldadoRepository soldadoRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceSoldado resourceSoldado;

    /*
    public SoldadoService(SoldadoRepository soldadoRepository, ObjectMapper objectMapper) {
        this.soldadoRepository = soldadoRepository;
        this.objectMapper = objectMapper;
    }
    */

    public SoldadoResponse buscarSoldado(Long id) {
        SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
        SoldadoResponse soldadoResponse = resourceSoldado.criarLinkDetalhe(soldado);
        return soldadoResponse;
    }

    public void criarSoldado(Soldado soldado){
        SoldadoEntity soldadoEntity = objectMapper.convertValue(soldado, SoldadoEntity.class);
        soldadoRepository.save(soldadoEntity);
    }

    public void alterarSoldado(Long id, SoldadoEditRequest soldadoEditRequest) {
        SoldadoEntity soldadoEntity = objectMapper.convertValue(soldadoEditRequest, SoldadoEntity.class);
        soldadoEntity.setId(id);
        soldadoRepository.save(soldadoEntity);
    }

    public void deletarSoldado(Long id) {
        SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
        soldadoRepository.delete(soldado);
    }

    public List<SoldadoListResponse> buscarSoldados() {
        List<SoldadoEntity> all = soldadoRepository.findAll();
        List<SoldadoListResponse> soldadoStream = all.stream()
                .map(it -> resourceSoldado.criarLink(it))
                .collect(Collectors.toList());
        return soldadoStream;
    }

}
