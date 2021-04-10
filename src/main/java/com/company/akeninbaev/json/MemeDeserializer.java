package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemeDeserializer extends StdDeserializer<Meme> {
    public MemeDeserializer() {
        super(Meme.class);
    }

    @Override
    public Meme deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get("id").asInt();
        String link = root.get("link").asText();
        String date = root.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date1 = LocalDate.parse(date,formatter);
        return new Meme(id, link, date1);
    }
}
