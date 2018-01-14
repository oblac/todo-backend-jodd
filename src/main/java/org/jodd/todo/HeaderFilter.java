package org.jodd.todo;

import jodd.madvoc.ActionRequest;
import jodd.madvoc.filter.ActionFilter;

public class HeaderFilter implements ActionFilter {

	@Override
	public Object filter(ActionRequest actionRequest) throws Exception {
		actionRequest.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		actionRequest.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS,HEAD,PATCH");
		actionRequest.getHttpServletResponse().setHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Accept");

		return actionRequest.invoke();
	}
}