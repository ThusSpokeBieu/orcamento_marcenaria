package github.gmess.utils.validators;

import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.Draft;
import io.vertx.json.schema.JsonSchema;
import io.vertx.json.schema.JsonSchemaOptions;
import io.vertx.json.schema.OutputFormat;
import java.io.InputStream;
import java.util.Scanner;
import io.vertx.json.schema.Validator;

public abstract class JsonValidator {

  private static Validator validator;

  public static Validator getValidator() {
    if (validator == null) {
      setValidator();
    }

    return validator;
  }

  public static void setValidator() {
    InputStream inputStream = JsonValidator.class.getResourceAsStream("/schema.json");
    Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
    String schemaString = scanner.hasNext() ? scanner.next() : "";
    JsonObject config = new JsonObject(schemaString);

    validator = Validator.create(
      JsonSchema.of(config),
      new JsonSchemaOptions()
        .setOutputFormat(OutputFormat.Basic)
        .setDraft(Draft.DRAFT7)
        .setBaseUri("http://localhost/")
                                         );

  }
}
