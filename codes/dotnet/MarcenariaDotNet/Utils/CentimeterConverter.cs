using System.Globalization;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace MarcenariaDotNet.Utils;

public class CentimeterConverter : JsonConverter<double>
{
    public override double Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        if (reader.TokenType == JsonTokenType.Number)
        {
            if (reader.TryGetDouble(out double value))
            {
                return value;
            }
        }
        if (reader.TokenType == JsonTokenType.String)
        {
          return NumberUtils.StrDouble(reader.GetString() ?? "312");
        }

        return 0;
    }

    public override void Write(Utf8JsonWriter writer, double value, JsonSerializerOptions options)
    {
      writer.WriteStringValue($"{NumberUtils.DoubleToStr(value)}cm");
    }
}

