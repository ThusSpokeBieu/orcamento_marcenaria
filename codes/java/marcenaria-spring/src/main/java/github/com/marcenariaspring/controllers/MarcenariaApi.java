package github.com.marcenariaspring.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import github.com.marcenariaspring.models.ListaMoveis;
import github.com.marcenariaspring.models.ListaOrcamento;
import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.models.moveis.Movel;
import github.com.marcenariaspring.models.moveis.Orcamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface MarcenariaApi {

  @Tag(name = "Geometrias")
  @GetMapping(value = "/geometrias", produces = APPLICATION_JSON_VALUE)
  @Operation(summary = "Recebe uma lista com todas geometrias disponíveis")
  public Mono<ResponseEntity<Geometria[]>> getGeometrias();

  @Tag(name = "Materiais")
  @GetMapping(value = "/materiais", produces = APPLICATION_JSON_VALUE)
  @Operation(summary = "Recebe uma lista com todos materiais disponíveis")
  public Mono<ResponseEntity<Material[]>> getMateriais();

  @Tag(name = "Orcamento")
  @PostMapping(
      value = "/orcamento-unitario",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  @Operation(summary = "Faz o orçamento de um único móvel")
  public Mono<ResponseEntity<Orcamento>> postOrcamento(@Valid @RequestBody final Movel movel);

  @Tag(name = "Orcamento")
  @PostMapping(
      value = "/orcamentos",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  @Operation(summary = "Faz o orçamento de vários móveis")
  public Mono<ResponseEntity<ListaOrcamento>> postOrcamentos(
      @Valid @RequestBody final ListaMoveis movel);
}
