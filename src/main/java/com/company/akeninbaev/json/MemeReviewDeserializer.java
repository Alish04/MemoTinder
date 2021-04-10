package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemeReviewDeserializer extends StdDeserializer<MemeReview> {
    private final Service<User, Integer> userService;
    private final Service<Meme, Integer> memeService;

    protected MemeReviewDeserializer(Service<User, Integer> userService, Service<Meme, Integer> memeService) {
        super(MemeReview.class);
        this.userService = userService;
        this.memeService = memeService;
    }

    @Override
    public MemeReview deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get("id").asInt();
        int userId = root.get("user").asInt();
        User user = userService.findById(userId);
        int memeId = root.get("meme").asInt();
        Meme meme = memeService.findById(memeId);
        String rating = root.get("rating").asText();
        String date = root.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date1 = LocalDate.parse(date,formatter);
        return new MemeReview(id, user, meme, rating, date1);
    }
}
