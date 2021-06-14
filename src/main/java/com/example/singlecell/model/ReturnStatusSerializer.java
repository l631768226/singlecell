package com.example.singlecell.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ReturnStatusSerializer extends JsonSerializer<ReturnStatus> {
    @Override
    public void serialize(ReturnStatus value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("code", value.getCode());
        jgen.writeStringField("desc", value.getDesc());
        jgen.writeEndObject();
    }
}
