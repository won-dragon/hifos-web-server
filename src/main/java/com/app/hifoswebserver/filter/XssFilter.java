package com.app.hifoswebserver.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class XssFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        XssCommRequestWrapper xssCommRequestWrapper = new XssCommRequestWrapper(request);
        XssCommResponseWrapper xssCommResponseWrapper = new XssCommResponseWrapper(response);

        System.out.println(request.getRequestURI());
        if(request.getRequestURI().startsWith("/hifos-api/v1/user-todo")){
            filterChain.doFilter(xssCommRequestWrapper, xssCommResponseWrapper);

            String responseMessage = new String(xssCommResponseWrapper.getDataStream(), StandardCharsets.UTF_8);
            try {
                responseMessage = changeEscapes(responseMessage);
            } catch (Exception e){
                e.printStackTrace();
            }

            response.getOutputStream().write(responseMessage.getBytes());
        }else {
            filterChain.doFilter(xssCommRequestWrapper, response);
        }
    }

    private String changeEscapes(String responseMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        responseMessage = objectMapper.writeValueAsString(responseMessage);
        JsonParser jsonParser = new JsonParser();
        responseMessage = jsonParser.parse(responseMessage).getAsString();
        return responseMessage;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
