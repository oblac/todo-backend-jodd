package org.jodd.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Nothing about Jodd here.
 * Simple in-memory implementation of to do dabase.
 */
public class TodoDb {
	private final String baseUrl;

	public TodoDb(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private Map<Integer, TodoEntry> todos = new HashMap<>();
	private int counter = 0;

	public ArrayList<TodoEntry> all() {
		return new ArrayList<>(todos.values());
	}

	public TodoEntry save(TodoEntry todoEntry) {
		counter++;
		todoEntry.setId(counter);
		todoEntry.setCompleted(false);
		todoEntry.setUrl(baseUrl + todoEntry.getId());
		todos.put(counter, todoEntry);
		return todoEntry;
	}

	public TodoEntry find(int id) {
		for (Map.Entry<Integer, TodoEntry> entry : todos.entrySet()) {
			if (entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public TodoEntry delete(int id) {
		return todos.remove(id);
	}

	public void deleteAll() {
		todos.clear();
	}

	public TodoEntry update(TodoEntry todoEntry) {
		int id = todoEntry.getId();
		delete(id);
		todos.put(id, todoEntry);
		return todoEntry;
	}

	public TodoEntry patch(int id, TodoEntry patch) {
		TodoEntry todoEntry = find(id);
		if (todoEntry == null) {
			return null;
		}

		if (patch.getTitle() != null) {
			todoEntry.setTitle(patch.getTitle());
		}
		if (patch.isCompleted() != null) {
			todoEntry.setCompleted(patch.isCompleted());
		}
		if (patch.getOrder() != null) {
			todoEntry.setOrder(patch.getOrder());
		}

		return todoEntry;
	}
}
