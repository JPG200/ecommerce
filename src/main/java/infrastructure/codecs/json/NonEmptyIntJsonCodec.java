package infrastructure.codecs.json;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyId;

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
public class NonEmptyIntJsonCodec {

    public static class NonEmptyIntIdEncoder extends JsonSerializer<NonEmptyId> {

        @Override
        public void serialize(NonEmptyId nonEmptyId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(nonEmptyId.getValue());
        }
    }

    public static class NonEmptyIntIdDecoder extends JsonDeserializer<NonEmptyId> {

        @Override
        public NonEmptyId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final int valueAsString = jsonParser.getValueAsInt();
            return new NonEmptyId(valueAsString);
        }
    }

    public static class NonEmptyIntCIEncoder extends JsonSerializer<NonEmptyCI> {

        @Override
        public void serialize(NonEmptyCI NonEmptyCI, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(NonEmptyCI.getValue());
        }
    }

    public static class NonEmptyIntCIDecoder extends JsonDeserializer<NonEmptyCI> {

        @Override
        public NonEmptyCI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final int valueAsString = jsonParser.getValueAsInt();
            return new NonEmptyCI(valueAsString);
        }
    }
}
