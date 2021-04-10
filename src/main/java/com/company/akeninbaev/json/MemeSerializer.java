package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MemeSerializer extends StdSerializer<Meme> {
    public MemeSerializer() {
        super(Meme.class);
    }

    @Override
    public void serialize(Meme meme, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", meme.getId());
        jsonGenerator.writeStringField("link", meme.getLink());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        jsonGenerator.writeStringField("date", meme.getDate().format(formatter));
        jsonGenerator.writeEndObject();
    }
}
