package bpn.MockProject.service;

import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.ChangePasswordDto;
import bpn.MockProject.model.dto.LoginDto;
import bpn.MockProject.model.dto.SignUpDto;

public interface LoginService {
	ActionResult login(LoginDto login);
	ActionResult signup(SignUpDto signup);
	ActionResult changePassword(ChangePasswordDto changePassword);
}
