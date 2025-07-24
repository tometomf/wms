package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse reesponse) throws Exception {
		reesponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
