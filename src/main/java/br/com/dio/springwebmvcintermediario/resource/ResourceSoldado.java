package br.com.dio.springwebmvcintermediario.resource;

import br.com.dio.springwebmvcintermediario.controller.SoldadoController;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoListResponse;
import br.com.dio.springwebmvcintermediario.controller.response.SoldadoResponse;
import br.com.dio.springwebmvcintermediario.entity.SoldadoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceSoldado {

    @Autowired
    private ObjectMapper objectMapper;

    public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity) {
        SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoListResponse.class);
        Link link = linkTo(methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId())).withSelfRel();
        soldadoListResponse.add(link);
        return soldadoListResponse;
    }

    public SoldadoResponse criarLinkDetalhe(SoldadoEntity soldadoEntity) {
        SoldadoResponse soldadoResponse = objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);

        if(soldadoEntity.getStatus().equalsIgnoreCase("morto")) {
            Link link = linkTo(methodOn(SoldadoController.class).deletarSoldado(soldadoEntity.getId()))
                    .withRel("remover")
                    .withTitle("Deletar soldado")
                    .withType("delete");

            soldadoResponse.add(link);
        } else if(soldadoEntity.getStatus().equalsIgnoreCase("vivo")) {
            Link link = linkTo(methodOn(SoldadoController.class).frenteCastelo(soldadoEntity.getId()))
                    .withRel("batalhar")
                    .withTitle("Ir para a frente do castelo")
                    .withType("put");

            soldadoResponse.add(link);
        }

        return soldadoResponse;
    }

}
