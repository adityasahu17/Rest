package com.smartfocus.test.service;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.smartfocus.test.model.User;

public class UserRestServiceTest extends JerseyTest {
	
	public Application configure(){
		return new ResourceConfig(UserRestService.class);
	}
	
	@Test
	public void getAllUsers() throws Exception{
		Response output = target("/users").request().get();
		assertEquals(200, output.getStatus());
		
	}
	
	@Test
	public void getUser() throws Exception{
		Response output = target("/users/1").request().get();
		assertEquals(200, output.getStatus());

	}
	
	@Test
	public void createUser() throws Exception{
		User user = new User(1L,"No Name");
		Response output = target("/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals(200, output.getStatus());

	}
	
	@Test
	public void updateUserPost() throws Exception{
		User user = new User(1L,"No Name");
		Response output = target("/users/1").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals(200, output.getStatus());

	}
	
	@Test
	public void updateUserPut() throws Exception{
		User user = new User(1L,"Radhika");
		Response output = target("/users/1").request().put(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals(200, output.getStatus());

	}
	
	@Test
	public void deleteUser() throws Exception{
		Response output = target("/users/1").request().delete();
		assertEquals(200, output.getStatus());

	}
	
	
}
