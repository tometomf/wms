package user.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import user.model.User;
import user.service.UserListService;

public class UserHandler implements CommandHandler{

	@Override
	public String process (HttpServletRequest request, HttpServletResponse response) {
		UserListService userListService = new UserListService();
		
		List<User> userList = userListService.getUserList();
        request.setAttribute("userList", userList);
        return "/WEB-INF/view/userList.jsp";
	}
}