package com.smartfocus.test.db;

import java.util.HashMap;
import java.util.Map;

import com.smartfocus.test.model.User;

public class DB {

	private static Map<Long, User> users = new HashMap();

	public static Map<Long, User> getUsersFromDb() {
		return users;
	}
}
