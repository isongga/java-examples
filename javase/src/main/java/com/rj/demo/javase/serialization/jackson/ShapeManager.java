package com.rj.demo.javase.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    public List<Shape> shapes;


    public static void main(String[] args) throws JsonProcessingException {
        ShapeManager sm = new ShapeManager();
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Square());
        sm.shapes = shapes;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //序列化没有问题
        String str = objectMapper.writeValueAsString(sm);
        System.out.println(str);
        //反序列化有问题
//        objectMapper.readValue(str, ShapeManager.class);
        //增加以下注解在Shape上
//        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//        @JsonSubTypes({
//                @JsonSubTypes.Type(value = Circle.class, name = "circle"),
//                @JsonSubTypes.Type(value = Square.class, name = "square"),
//        })
        objectMapper.readValue(str, ShapeManager.class);

    }
}
