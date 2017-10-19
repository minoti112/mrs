package mrs.app.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateForm {

	@NotNull
	@Size(min = 4, max = 255)
	private String loginId;
	@NotNull
	@Size(min = 8, max = 255)
	private String password;
	@NotNull
	@Size(max = 255)
	private String firstName;
	@NotNull
	@Size(max = 255)
	private String lastName;
	
}
