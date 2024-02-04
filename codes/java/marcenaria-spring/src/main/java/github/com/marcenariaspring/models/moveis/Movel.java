package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.geometrias.Geometria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Movel(
    @NotEmpty
        @JsonProperty("movel")
        @Schema(
            description = "nome do movel",
            defaultValue = "cadeira",
            minLength = 0,
            nullable = false)
        String movel,
    @JsonProperty("geometrias") Geometria[] geometrias) {

  @JsonCreator
  public Movel(
      @JsonProperty("movel") String movel, @JsonProperty("geometrias") Geometria[] geometrias) {
    this.movel = movel;
    this.geometrias = geometrias;
  }
}
