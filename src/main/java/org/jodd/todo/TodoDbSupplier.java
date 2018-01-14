package org.jodd.todo;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInitMethod;

import java.util.function.Supplier;

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
		this.todoDb = new TodoDb(basePath);
	}

	@Override
	public TodoDb get() {
		return todoDb;
	}
}
