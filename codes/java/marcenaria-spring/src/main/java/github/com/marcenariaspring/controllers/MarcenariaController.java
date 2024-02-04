package github.com.marcenariaspring.controllers;

import github.com.marcenariaspring.models.ListaMoveis;
import github.com.marcenariaspring.models.ListaOrcamento;
import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.models.geometrias.ListaGeometrias;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.models.moveis.Movel;
import github.com.marcenariaspring.models.moveis.Orcamento;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MarcenariaController implements MarcenariaApi {

  public Mono<ResponseEntity<Geometria[]>> getGeometrias() {
    return Mono.just(ResponseEntity.ok(ListaGeometrias.getArray()));
  }

  public Mono<ResponseEntity<Material[]>> getMateriais() {
    return Mono.just(ResponseEntity.ok(Material.values()));
  }

  public Mono<ResponseEntity<Orcamento>> postOrcamento(@Valid @RequestBody Movel movel) {
    return Mono.fromSupplier(() -> Orcamento.from(movel)).map(o -> ResponseEntity.ok().body(o));
  }

  public Mono<ResponseEntity<ListaOrcamento>> postOrcamentos(
      @Valid @RequestBody ListaMoveis moveis) {
    return Mono.fromSupplier(() -> ListaOrcamento.from(moveis))
        .map(o -> ResponseEntity.ok().body(o));
  }
}
