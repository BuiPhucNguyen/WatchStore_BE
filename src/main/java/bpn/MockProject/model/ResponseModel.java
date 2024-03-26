package bpn.MockProject.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import bpn.MockProject.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class ResponseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private int code;
	private String message;
	private final long time = System.currentTimeMillis();
	private Object data;

	public ResponseModel() {
		this(ErrorCodeEnum.OK);
	}

	public ResponseModel(final ErrorCodeEnum returnCode) {
		this.status = HttpStatus.valueOf(returnCode.getStatus());
		this.code = returnCode.getCode();
		this.message = returnCode.getMessage();
	}

	public ResponseModel(final ErrorCodeEnum returnCode, Object data) {
		this(returnCode);
		this.data = data;
	}

	public ResponseModel(final HttpStatus status, final int code, final String message, Object data) {
		this.status = status;
		this.code = code;
		this.message = message == null ? "" : message;
		this.data = data;
	}

	public ResponseModel setResponse(final Object data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "ResponseModel{" + "status=" + status + ", code=" + code + ", message='" + message + '\'' + ", time="
				+ time + ", data=" + data + '}';
	}
}
