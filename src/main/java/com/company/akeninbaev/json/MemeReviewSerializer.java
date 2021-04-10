package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MemeReviewSerializer extends StdSerializer<MemeReview> {
    private final Service<User, Integer> userService;
    private final Service<Meme, Integer> memeService;

    protected MemeReviewSerializer(Service<User, Integer> userService, Service<Meme, Integer> memeService) {
        super(MemeReview.class);
        this.userService = userService;
        this.memeService = memeService;
    }

    @Override
    public void serialize(MemeReview memeReview, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", memeReview.getId());
        jsonGenerator.writeStringField("user", String.valueOf(memeReview.getUser()));
        jsonGenerator.writeStringField("meme", String.valueOf(memeReview.getMeme()));
        jsonGenerator.writeStringField("rating", memeReview.getRating());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        jsonGenerator.writeStringField("date", memeReview.getDate().format(formatter));
        jsonGenerator.writeEndObject();
    }
}