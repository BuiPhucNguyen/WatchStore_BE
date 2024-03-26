package bpn.MockProject.model;

import bpn.MockProject.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class ActionResult {
	private ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.OK;
	private Object data;
}
