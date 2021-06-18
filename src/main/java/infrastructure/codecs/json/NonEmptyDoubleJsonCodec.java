package infrastructure.codecs.json;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyId;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyTax;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class NonEmptyDoubleJsonCodec {
    public static class NonEmptyPriceEncoder extends JsonSerializer<NonEmptyPrice> {

        @Override
        public void serialize(NonEmptyPrice NonEmptyPrice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(NonEmptyPrice.getValue());
        }
    }

    public static class NonEmptyPriceDecoder extends JsonDeserializer<NonEmptyPrice> {

        @Override
        public NonEmptyPrice deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final double valueAsString = jsonParser.getValueAsDouble();
            return new NonEmptyPrice(valueAsString);
        }
    }

    public static class NonEmptyTaxEncoder extends JsonSerializer<NonEmptyTax> {

        @Override
        public void serialize(NonEmptyTax NonEmptyTax, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(NonEmptyTax.getValue());
        }
    }

    public static class NonEmptyIntCIDecoder extends JsonDeserializer<NonEmptyTax> {

        @Override
        public NonEmptyTax deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final double valueAsString = jsonParser.getValueAsDouble();
            return new NonEmptyTax(valueAsString);
        }
    }
}
