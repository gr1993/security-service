package com.example.normal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HttpUtil {

    public static void setJsonResponse(HttpServletResponse response, Object data) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(data);
            response.getWriter().write(json);
        } catch (JsonProcessingException ex) {

        } catch (IOException ex) {

        }
    }
}
