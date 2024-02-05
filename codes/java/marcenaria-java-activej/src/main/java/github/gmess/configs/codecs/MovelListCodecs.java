package github.gmess.configs.codecs;

import github.gmess.models.Movel;
import github.gmess.models.MovelList;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class MovelListCodecs {
  public static JsonCodec<MovelList> create(final JsonCodec<Movel> movelJsonCodec) {
    return JsonCodecs.ofObject(
        MovelList::new, "moveis", MovelList::moveis, JsonCodecs.ofList(movelJsonCodec));
  }
}
