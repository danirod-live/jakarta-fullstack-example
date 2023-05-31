package es.makigas.ejemplos.fullstack.services;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author danirod
 */
public class ParameterServiceTest {
    
    private HttpServletRequest getFakeRequest(String value) {
        return new AbstractHttpServletRequest() {
            @Override
            public String getParameter(String name) {
                return value;
            }
        };
    }
    
    @Test
    public void testGetParameterWhenNull() {
        var req = getFakeRequest(null);
        var value = ParameterService.instance().getParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
    
    @Test
    public void testGetParameterWhenNotANumber() {
        var req = getFakeRequest("k");
        var value = ParameterService.instance().getParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
 
    @Test
    public void testGetParameterWhenValid() {
        var req = getFakeRequest("1234");
        var value = ParameterService.instance().getParameter(req, "null");
        Assertions.assertTrue(value.isPresent());
        Assertions.assertEquals(1234, value.get());
    }
    
    @Test
    public void testGetPositiveParameterWhenNull() {
        var req = getFakeRequest(null);
        var value = ParameterService.instance().getPositiveParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
    
    @Test
    public void testGetPositiveParameterWhenNotNumber() {
        var req = getFakeRequest("k");
        var value = ParameterService.instance().getPositiveParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
    
    @Test
    public void testGetPositiveParameterWhenNegative() {
        var req = getFakeRequest("-4");
        var value = ParameterService.instance().getPositiveParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
    
    @Test
    public void testGetPositiveParameterWhenZero() {
        var req = getFakeRequest("0");
        var value = ParameterService.instance().getPositiveParameter(req, "null");
        Assertions.assertTrue(value.isEmpty());
    }
    
    @Test
    public void testGetPositiveParameterWhenPositive() {
        var req = getFakeRequest("3");
        var value = ParameterService.instance().getPositiveParameter(req, "null");
        Assertions.assertTrue(value.isPresent());
        Assertions.assertEquals(3, value.get());
    }
}
