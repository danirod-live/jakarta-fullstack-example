package es.makigas.ejemplos.fullstack.services;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ParameterService {
    
    private static ParameterService instance;
    public static ParameterService instance() {
        if (instance == null) {
            instance = new ParameterService();
        }
        return instance;
    }
    
    public Optional<Long> getParameter(HttpServletRequest req, String param) {
        Optional<String> value = Optional.ofNullable(req.getParameter(param));
        try {
            return value.map(Long::parseLong);
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
    
    public Optional<Long> getPositiveParameter(HttpServletRequest req, String param) {
        return getParameter(req, param).filter(value -> value > 0);
    }
}
