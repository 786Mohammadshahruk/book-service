package com.book.service.books.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class TokenDecoder {

    public static String decodeToken(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        Token decryptToken = objectMapper.readValue(payload, Token.class);
        return decryptToken.getSub();
    }
}
