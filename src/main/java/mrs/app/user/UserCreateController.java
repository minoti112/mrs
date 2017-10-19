package mrs.app.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserCreateController {

	@RequestMapping(value = "/user/create")
	public String createForm(UserCreateForm userCreateForm) {
		return "user/create/userForm";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST, params = "redo")
	public String redoCreateForm(UserCreateForm userCreateForm) {
		return "user/create/userForm";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST, params = "confirm")
	public String confirmCreateForm(
			@Valid UserCreateForm userCreateForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return createForm(userCreateForm);
		}
		model.addAttribute("userCreateForm", userCreateForm);
		return "user/create/userConfirm";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST, params = "finish")
	public String finishCreateForm(
			@Valid UserCreateForm userCreateForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return createForm(userCreateForm);
		}
		return "room/listRooms";
	}
}

