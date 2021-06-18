package infrastructure.codecs.json;
import application.commons.NonEmptyString;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NonEmptyStringJson {

    public static class NonEmptyStringEncoder extends JsonSerializer<NonEmptyString> {

        @Override
        public void serialize(NonEmptyString nonEmptyString, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(nonEmptyString.getValue());
        }
    }

    public static class NonEmptyStringDecoder extends JsonDeserializer<NonEmptyString> {

        @Override
        public NonEmptyString deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String valueAsString = jsonParser.getValueAsString();
            return new NonEmptyString(valueAsString);
        }
    }
}
