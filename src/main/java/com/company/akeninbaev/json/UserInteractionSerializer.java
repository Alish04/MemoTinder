package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.UserInteraction;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class UserInteractionSerializer extends StdSerializer<UserInteraction> {
    private final Service<User, Integer> userService;

    protected UserInteractionSerializer(Service<User, Integer> userService) {
        super(UserInteraction.class);
        this.userService = userService;
    }

    @Override
    public void serialize(UserInteraction userInteraction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", userInteraction.getId());
        jsonGenerator.writeStringField("source", String.valueOf(userInteraction.getSource()));
        jsonGenerator.writeStringField("target", String.valueOf(userInteraction.getTarget()));
        jsonGenerator.writeBooleanField("reaction", userInteraction.getReaction());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        jsonGenerator.writeStringField("date", userInteraction.getDate().format(formatter));
        jsonGenerator.writeEndObject();
    }
}
