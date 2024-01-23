package github.com.marcenariaspring.controllers;

import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.models.moveis.Movel;
import github.com.marcenariaspring.models.moveis.Orcamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface MarcenariaApi {

  @Tag(name = "Geometrias")
  @GetMapping(
    value = "/geometrias",
    produces = APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Recebe uma lista com todas geometrias disponíveis")
  public Mono<ResponseEntity<Geometria[]>> getGeometrias();

  @Tag(name = "Materiais")
  @GetMapping(
    value = "/materiais",
    produces = APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Recebe uma lista com todos materiais disponíveis")
  public Mono<ResponseEntity<Material[]>> getMateriais();


  @Tag(name = "Orcamento")
  @PostMapping(value = "/orcamento",
               consumes = APPLICATION_JSON_VALUE,
               produces = APPLICATION_JSON_VALUE)
  @Operation(summary = "Faz o orçamento do móvel")
  public Mono<ResponseEntity<Orcamento>> postOrcamento(@Valid @RequestBody
    final Movel movel);
}
