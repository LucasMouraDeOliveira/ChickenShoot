package fr.chicken_shoot_production;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Hello world!
 *
 */

@ApplicationPath("/")
public class App extends Application
{
  
    @Override
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> s = new HashSet<Class<?>>();
    	s.add(WebSocketTest.class);
    	s.add(EndPoint.class);
    	return s;
	}
}
