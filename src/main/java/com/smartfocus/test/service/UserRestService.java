package com.smartfocus.test.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartfocus.test.db.DB;
import com.smartfocus.test.exception.UserNotFoundException;
import com.smartfocus.test.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestService {

	private Map<Long, User> usersDb = DB.getUsersFromDb();

	public UserRestService() {
		usersDb.put(1L, new User(1, "Radhika"));
		usersDb.put(2L, new User(2, "Nitesh"));
	}

	@GET
	public Collection<User> getAllUsers() {
		List<User> users = new ArrayList<User>(usersDb.values());
		return users;
	}

	@Path("/{id}")
	@GET
	public User getUser(@PathParam("id") long id) {
		List<User> users = new ArrayList<User>(usersDb.values());
		for (User user : users) {
			long getId = user.getUserId();
			if (getId == id) {
				return user;
			}
		}
		throw new UserNotFoundException("Try with different Id");
	}

	/*
	 * http://localhost:8080/test-smartfocus/users 
	 * RequestBody -application/json
	 * { "id": 12345, "Name": "Radhika"} by @Consume we are restricting it to
	 * only consume application/json
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		usersDb.put(user.getUserId(), user);
		return user;
	}

	/*
	 * Creating new user through GET by passing parameters on URI
	 * http://localhost:8080/test-smartfocus/users/createCustomer?id=1234&name=
	 * CreateMe
	 */
	@Path("/createCustomer")
	@GET
	public User createUser1(@QueryParam("id") long id, @QueryParam("name") String name) {
		User user = new User();
		user.setUserId(id);
		user.setName(name);
		usersDb.put(user.getUserId(), user);
		return user;
	}

	/*
	 * Updating the user through PUT by passing parameter on URI
	 * http://localhost:8080/test-smartfocus/users/1234
	 */
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUserPut(@PathParam("id") long id, User user) {
		List<User> users = new ArrayList<User>(usersDb.values());
		for (User u : users) {
			if (u.getUserId() == id) {
				usersDb.put(id, user);
				return user;
			}
		}
		throw new UserNotFoundException("This userId is not found for updatation");
	}

	/*
	 * Updating user through POST will behave same as PUT, Idially we should use
	 *  PUT for updation becoz it is idempotent
	 */
	@Path("/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUserPost(@PathParam("id") long id, User user) {
		List<User> users = new ArrayList<User>(usersDb.values());
		for (User u : users) {
			if (u.getUserId() == id) {
				usersDb.put(id, user);
				return user;
			}
		}
		throw new UserNotFoundException("This userId is not found for updatation");
	}

	/*
	 * Delete operation through DELETE
	 * http://localhost:8080/test-smartfocus/users/1234
	 */
	@Path("/{id}")
	@DELETE
	public boolean deleteUser(@PathParam("id") long id) {
		List<User> users = new ArrayList<User>(usersDb.values());
		for (User u : users) {
			long getId = u.getUserId();
			if (getId == id) {
				usersDb.remove(getId);
				return true;
			}
		}
		throw new UserNotFoundException("The UserId is not found.");
	}

}
