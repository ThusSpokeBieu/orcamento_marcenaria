package github.com.marcenariaspring.controllers;

import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.models.geometrias.ListaGeometrias;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.models.moveis.Movel;
import github.com.marcenariaspring.models.moveis.Orcamento;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MarcenariaController implements MarcenariaApi {

  @Cacheable("geometrias")
  public Mono<ResponseEntity<Geometria[]>> getGeometrias() {
    return Mono.just(ResponseEntity.ok(ListaGeometrias.getArray()));
  }

  @Cacheable("materiais")
  public Mono<ResponseEntity<Material[]>> getMateriais() {
    return Mono.just(ResponseEntity.ok(Material.values()));
  }


  @Cacheable("orcamento")
  public Mono<ResponseEntity<Orcamento>> postOrcamento( @Valid
                                                @RequestBody
                                               final Mono<Movel> movel) {
    return movel.map(Orcamento::from).map(o -> ResponseEntity.ok().body(o));
  }
}
