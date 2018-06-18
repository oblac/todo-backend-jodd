package org.jodd.todo;

import jodd.madvoc.meta.FilteredBy;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.RestAction;
import jodd.madvoc.meta.method.DELETE;
import jodd.madvoc.meta.method.GET;
import jodd.madvoc.meta.method.PATCH;
import jodd.madvoc.meta.method.POST;
import jodd.madvoc.meta.scope.JsonBody;
import jodd.madvoc.result.JsonResult;
import jodd.petite.meta.PetiteInject;

import java.util.ArrayList;

import static jodd.net.HttpStatus.error404;
import static jodd.net.HttpStatus.ok;

/**
 * Madvoc action defines action mappings i.e. routes.
 * For this simple example, we can define all routes here, in one class.
 * Routes can be defined also manually, w/o the annotations.
 */
@MadvocAction
@FilteredBy({HeaderFilter.class})
public class TodoAction {

	@PetiteInject
	TodoDbSupplier todoDb;

	@RestAction("/{any:.*}")
	public String options() {
		return "";
	}

	@RestAction("/")
	public ArrayList<TodoEntry> getAll() {
		return todoDb.get().all();
	}

	@RestAction("/")
	public JsonResult deleteAll() {
		todoDb.get().deleteAll();
		return JsonResult.of(ok());
	}

	// ---------------------------------------------------------------- items

	@POST
	@RestAction("/")
	public TodoEntry newEntry(@In @JsonBody TodoEntry todoEntry) {
		return todoDb.get().save(todoEntry);
	}

	@GET
	@RestAction("/{id:[0-9]+}")
	public JsonResult item(@In int id) {
		TodoEntry todoEntry = todoDb.get().find(id);

		if (todoEntry == null) {
			return JsonResult.of(error404().notFound());
		}
		return JsonResult.of(todoEntry);
	}

	@DELETE
	@RestAction("/{id:.+}")
	public JsonResult deleteItem(@In int id) {
		TodoEntry todoEntry = todoDb.get().delete(id);

		if (todoEntry == null) {
			return JsonResult.of(error404().notFound());
		}
		return JsonResult.of(todoEntry);
	}

	@PATCH
	@RestAction("/{id}")
	public TodoEntry update(@In int id, @In @JsonBody TodoEntry todoEntryPatch) {
		return todoDb.get().patch(id, todoEntryPatch);
	}
}
