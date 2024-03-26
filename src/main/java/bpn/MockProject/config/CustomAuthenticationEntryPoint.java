package bpn.MockProject.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ResponseModel;
import bpn.MockProject.utils.JsonHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ResponseModel responseModel = new ResponseModel(ErrorCodeEnum.FORBIDDEN, null);

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(403);
		response.getWriter().write(JsonHandler.getJsonString(responseModel));
	}

}
