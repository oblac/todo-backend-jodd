package org.jodd.todo;

import jodd.madvoc.filter.DefaultWebAppFilters;
import jodd.madvoc.meta.DELETE;
import jodd.madvoc.meta.FilteredBy;
import jodd.madvoc.meta.GET;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.PATCH;
import jodd.madvoc.meta.POST;
import jodd.madvoc.meta.RestAction;
import jodd.madvoc.meta.Scope;
import jodd.madvoc.result.JsonResult;
import jodd.petite.meta.PetiteInject;

import java.util.ArrayList;

import static jodd.madvoc.ScopeType.BODY;
import static jodd.util.net.HttpStatus.error404;
import static jodd.util.net.HttpStatus.ok;

@MadvocAction
@FilteredBy({DefaultWebAppFilters.class, HeaderFilter.class})
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
	public TodoEntry newEntry(@In @Scope(BODY) TodoEntry todoEntry) {
		return todoDb.get().save(todoEntry);
	}

	@GET @RestAction("/{id:.+}")
	public JsonResult item(@In int id) {
		TodoEntry todoEntry = todoDb.get().find(id);

		if (todoEntry == null) {
			return JsonResult.of(error404().notFound());
		}
		return JsonResult.of(todoEntry);
	}

	@DELETE @RestAction("/{id:.+}")
	public JsonResult deleteItem(@In int id) {
		TodoEntry todoEntry = todoDb.get().delete(id);

		if (todoEntry == null) {
			return JsonResult.of(error404().notFound());
		}
		return JsonResult.of(todoEntry);
	}

	@PATCH @RestAction("/{id}")
	public TodoEntry update(@In int id, @In @Scope(BODY) TodoEntry todoEntryPatch) {
		return todoDb.get().patch(id, todoEntryPatch);
	}
}
