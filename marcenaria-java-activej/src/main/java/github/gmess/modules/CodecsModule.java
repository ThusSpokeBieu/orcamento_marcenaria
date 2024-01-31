package github.gmess.modules;

import github.gmess.configs.codecs.EstruturaValorCodecs;
import github.gmess.configs.codecs.MovelCodecs;
import github.gmess.configs.codecs.OrcamentoCodecs;
import github.gmess.configs.codecs.geometrias.CilindroCodecs;
import github.gmess.configs.codecs.geometrias.CuboCodecs;
import github.gmess.configs.codecs.geometrias.EsferaCodecs;
import github.gmess.configs.codecs.geometrias.GeometriaCodecs;
import github.gmess.configs.codecs.geometrias.ParalelepipedoCodecs;
import github.gmess.models.EstruturaValor;
import github.gmess.models.Movel;
import github.gmess.models.Orcamento;
import github.gmess.models.geometrias.Cilindro;
import github.gmess.models.geometrias.Cubo;
import github.gmess.models.geometrias.Esfera;
import github.gmess.models.geometrias.Geometria;
import github.gmess.models.geometrias.GeometriaConst;
import github.gmess.models.geometrias.Paralelepipedo;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.json.JsonCodec;

public class CodecsModule extends AbstractModule {
   @Provides
  public JsonCodec<Esfera> esferaCodec() {
    return EsferaCodecs.create();
  }

  @Provides
  public JsonCodec<Cubo> cuboCodec() {
    return CuboCodecs.create();
  }

  @Provides
  public JsonCodec<Cilindro> cilindroCodec() {
    return CilindroCodecs.create();
  }

  @Provides
  public JsonCodec<Paralelepipedo> paralelepipedoCodec() {
    return ParalelepipedoCodecs.create();
  }

  @Provides
  public JsonCodec<EstruturaValor> estruturaValorCodec() {
    return EstruturaValorCodecs.create();
  }

  @Provides
  public JsonCodec<Orcamento> orcamentoCodec(final JsonCodec<EstruturaValor> estruturaValorCodec) {
    return OrcamentoCodecs.create(estruturaValorCodec);
  }

  @Provides
  public JsonCodec<Geometria> geometriaCodec(
    final JsonCodec<Esfera> esferaJsonCodec,
    final JsonCodec<Cubo> cuboJsonCodec,
    final JsonCodec<Cilindro> cilindroJsonCodec,
    final JsonCodec<Paralelepipedo> paralelepipedoJsonCodec
  ) {
    return GeometriaCodecs.create(esferaJsonCodec, cuboJsonCodec, cilindroJsonCodec, paralelepipedoJsonCodec);
  }

  @Provides
  public JsonCodec<Movel> movelCodec(
    final JsonCodec<Geometria> geometriaCodec
  ) {
    return MovelCodecs.create(geometriaCodec);
  }

  @Provides
  public GeometriaConst geometriaConst(
    final JsonCodec<Geometria> geometriaCodec
  ) {
    return new GeometriaConst(geometriaCodec);
  }

}
