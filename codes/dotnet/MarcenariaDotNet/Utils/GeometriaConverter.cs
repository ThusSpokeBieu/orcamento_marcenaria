using System.Text.Json;
using System.Text.Json.Serialization;
using MarcenariaDotNet.Dtos.Geometrias;

namespace MarcenariaDotNet.Utils;

public class GeometriaConverter : JsonConverter<Geometria>
{
    public override Geometria Read(ref Utf8JsonReader reader,
                                   Type typeToConvert,
                                   JsonSerializerOptions options)
    {
        using (var jsonDoc = JsonDocument.ParseValue(ref reader))
        {
            var jsonObject = jsonDoc.RootElement.Clone();
            var typeDiscriminator = jsonObject.GetProperty("geometria")
                .GetString()?
                .ToLowerInvariant();

            Geometria geometria = typeDiscriminator switch
            {
                "esfera" => JsonSerializer.Deserialize<Esfera>(jsonObject.GetRawText()),
                "cubo" => JsonSerializer.Deserialize<Cubo>(jsonObject.GetRawText()),
                "cilindro" => JsonSerializer.Deserialize<Cilindro>(jsonObject.GetRawText()),
                "paralelepipedo" => JsonSerializer.Deserialize<Paralelepipedo>(jsonObject.GetRawText()),
                _ => throw new JsonException("O tipo de geometria é inválido"),
            };

            return geometria;
        }
    }

   
  public override void Write(Utf8JsonWriter writer, Geometria value, JsonSerializerOptions options)
  {
    if (value is Esfera esfera) JsonSerializer.Serialize(writer, esfera, options);
    else if (value is Cubo cubo) JsonSerializer.Serialize(writer, cubo, options);
    else if (value is Cilindro cilindro) JsonSerializer.Serialize(writer, cilindro, options);
    else if (value is Paralelepipedo paralelepipedo) JsonSerializer.Serialize(writer, paralelepipedo, options);
    else throw new JsonException("O tipo de geometria é inválido");
  }

}
