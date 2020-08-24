package com.burda.barnacle;

import com.burda.barnacle.types.ImportantData;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ImpDataDeserializer extends StdDeserializer<ImportantData> {
    public ImpDataDeserializer() {
        this(null);
    }

    public ImpDataDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public ImportantData deserialize(JsonParser parser, DeserializationContext deserializer) {
        ObjectCodec codec = parser.getCodec();
        ImportantData importantData = null;
        try {
            JsonNode node = codec.readTree(parser);

            importantData = new ImportantData(node.get("key").asText(), node.get("binaryData").asText());

        } catch (IOException ex) {
            System.out.println("Error during parsing, who would have said?" + ex);
        }
        return importantData;
    }
}
