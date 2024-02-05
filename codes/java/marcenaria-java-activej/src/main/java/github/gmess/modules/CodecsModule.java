package github.gmess.modules;

import github.gmess.configs.codecs.EstruturaValorCodecs;
import github.gmess.configs.codecs.MovelCodecs;
import github.gmess.configs.codecs.MovelListCodecs;
import github.gmess.configs.codecs.OrcamentoCodecs;
import github.gmess.configs.codecs.OrcamentoListCodecs;
import github.gmess.configs.codecs.geometrias.GeometriaCodecs;
import github.gmess.models.EstruturaValor;
import github.gmess.models.Movel;
import github.gmess.models.MovelList;
import github.gmess.models.Orcamento;
import github.gmess.models.OrcamentoList;
import github.gmess.models.geometrias.Geometria;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import io.activej.json.JsonCodec;
import io.activej.worker.annotation.Worker;

public class CodecsModule extends AbstractModule {

  @Provides
  @Worker
  public JsonCodec<EstruturaValor> estruturaValorCodec() {
    return EstruturaValorCodecs.create();
  }

  @Provides
  @Worker
  public JsonCodec<Orcamento> orcamentoCodec(final JsonCodec<EstruturaValor> estruturaValorCodec) {
    return OrcamentoCodecs.create(estruturaValorCodec);
  }

  @Provides
  @Worker
  public JsonCodec<Geometria> geometriaCodec() {
    return GeometriaCodecs.create();
  }

  @Provides
  @Worker
  public JsonCodec<Movel> movelCodec(final JsonCodec<Geometria> geometriaCodec) {
    return MovelCodecs.create(geometriaCodec);
  }

  /*
   * @Provides // @Worker public GeometriaConst geometriaConst(final JsonCodec<Geometria>
   * geometriaCodec) { return new GeometriaConst(geometriaCodec); }
   */

  @Provides
  @Worker
  public JsonCodec<MovelList> movelListCodec(final JsonCodec<Movel> movelCodec) {
    return MovelListCodecs.create(movelCodec);
  }

  @Provides
  @Worker
  public JsonCodec<OrcamentoList> orcamentoListCodec(final JsonCodec<Orcamento> orcamentoCodec) {
    return OrcamentoListCodecs.create(orcamentoCodec);
  }
}
