package bpn.MockProject.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseBuild {

	public ResponseModel build(ActionResult actionResult) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setStatus(actionResult.getErrorCodeEnum().getHttpStatus());
		responseModel.setMessage(actionResult.getErrorCodeEnum().getMessage());
		responseModel.setCode(actionResult.getErrorCodeEnum().getCode());
		responseModel.setData(actionResult.getData());
		return responseModel;
	}
}
