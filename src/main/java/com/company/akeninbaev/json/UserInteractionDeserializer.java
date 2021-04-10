package com.company.akeninbaev.json;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.model.UserInteraction;
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

public class UserInteractionDeserializer extends StdDeserializer<UserInteraction> {
    private final Service<User, Integer> userService;

    protected UserInteractionDeserializer(Service<User, Integer> userService) {
        super(UserInteraction.class);
        this.userService = userService;
    }

    @Override
    public UserInteraction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get("id").asInt();
        int source = root.get("source").asInt();
        User user = userService.findById(source);
        int target = root.get("target").asInt();
        User user1 = userService.findById(target);
        boolean reaction = root.get("reaction").asBoolean();
        String date = root.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date1 = LocalDate.parse(date,formatter);
        return new UserInteraction(id, user, user1, reaction, date1);
    }
}
