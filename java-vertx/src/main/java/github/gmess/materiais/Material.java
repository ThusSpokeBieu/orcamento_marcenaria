package github.gmess.materiais;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public enum Material {
  PINHO("Pinho", 0.10),
  CARVALHO("Carvalho", 0.30),
  EBANO("Ébano", 5.00);

  private final String nome;
  private final Double preco;
  public static final Buffer json = new JsonArray()
    .add( new JsonObject().put("nome", "Pinho").put("preco", "R$0,10"))
    .add( new JsonObject().put("nome", "Carvalho").put("preco", "R$0,30"))
    .add( new JsonObject().put("nome", "Ébano").put("preco", "R$5,00"))
    .toBuffer();

  Material(final String nome, final Double preco) {
    this.nome = nome;
    this.preco = preco;
  }

}
