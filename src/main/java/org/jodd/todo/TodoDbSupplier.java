package org.jodd.todo;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInitMethod;

import java.util.function.Supplier;

/**
 * This suppliers provides the base URL required in the {@link TodoDb}.
 * This class is a Petite Bean. It is also set from `joy.props` where it
 * gets the default configuration from.
 * For the sake of simplicity, we hardcoded base values here.
 */
@PetiteBean
public class TodoDbSupplier implements Supplier<TodoDb> {

	private TodoDb todoDb;

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@PetiteInitMethod
	public void init() {
		if (basePath == null) {
			basePath = "http://localhost:8080/";
		}

		// detect Heroku
		if (System.getenv("DYNO") != null) {
			basePath = "https://todo-backend-jodd.herokuapp.com/";
			System.out.println("=== HEROKU DETECTED ===");
		}
		// detect WeDeploy
		else if (System.getenv("WEDEPLOY_PROJECT_ID") != null) {
			basePath = "https://backend-todo.wedeploy.io/";
			System.out.println("=== WEDEPLOY DETECTED ===");
		}
		this.todoDb = new TodoDb(basePath);
	}

	@Override
	public TodoDb get() {
		return todoDb;
	}
}
