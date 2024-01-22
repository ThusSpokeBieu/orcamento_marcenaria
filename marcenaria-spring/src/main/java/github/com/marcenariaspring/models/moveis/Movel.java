package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.geometrias.Geometria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;
import org.hibernate.validator.constraints.Normalized;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movel {

  @NotEmpty
  @JsonProperty("movel")
  private String movel;

  @Normalized
  @Pattern(regexp = "(?i)(?u)(^ebano$|^pinho$|^carvalho$)",
           flags = { Flag.CASE_INSENSITIVE, Flag.UNICODE_CASE },
           message = "Material inválido")
  @JsonProperty("material")
  private final String material;

  @JsonProperty("geometrias")
  private final Geometria[] geometrias;

  @JsonCreator
  public Movel(
    @JsonProperty("movel") String movel,
    @JsonProperty("material") String material,
    @JsonProperty("geometrias") Geometria[] geometrias) {
    this.movel = movel;
    this.material = material;
    this.geometrias = geometrias;
  }
}
