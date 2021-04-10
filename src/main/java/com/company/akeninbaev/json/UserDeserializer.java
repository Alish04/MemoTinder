package com.company.akeninbaev.json;

import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.UserRole;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDeserializer extends StdDeserializer<User> {
    public UserDeserializer() {
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get("id").asInt();
        String firstName = root.get("firstName").asText();
        String lastName = root.get("lastName").asText();
        String login = root.get("login").asText();
        String sex = root.get("sex").asText();
        String country = root.get("country").asText();
        String city = root.get("city").asText();
        String phone = root.get("phone").asText();
        String birthday = root.get("birthday").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate bday = LocalDate.parse(birthday,formatter);
        String password = root.get("password").asText();
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        String role = root.get("role").asText();
        role = role.toUpperCase();
        UserRole userRole = UserRole.valueOf(role);
        return new User(id, firstName, lastName, sex, bday, country, city, phone, userRole, login, hashed);
    }
}
