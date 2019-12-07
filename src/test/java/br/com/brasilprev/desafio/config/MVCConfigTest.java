package br.com.brasilprev.desafio.config;

import org.junit.Test;
import static org.junit.Assert.*;

public class MVCConfigTest {
  
    @Test
    public void testRoot() {
        
        MVCConfig mvc = new MVCConfig();
        
        assertEquals("index.html", mvc.root());
    }

    @Test
    public void testError() {
        
        MVCConfig mvc = new MVCConfig();
        
        assertEquals("error.html", mvc.error());
    }

    @Test
    public void testGetErrorPath() {
        
        MVCConfig mvc = new MVCConfig();
        
        assertEquals("/error", mvc.getErrorPath());
    }
}
