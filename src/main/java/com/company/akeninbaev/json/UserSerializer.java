package com.company.akeninbaev.json;

import com.company.akeninbaev.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        super(User.class);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("firstName", user.getFirstName());
        gen.writeStringField("lastName", user.getLastName());
        gen.writeStringField("sex", user.getSex());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        gen.writeStringField("birthday", user.getBirthday().format(formatter));
        gen.writeStringField("country", user.getCountry());
        gen.writeStringField("city", user.getCity());
        gen.writeStringField("phone", user.getPhone());
        gen.writeStringField("role", String.valueOf(user.getRole()));
        gen.writeStringField("login", user.getLogin());
        gen.writeEndObject();
    }
}
