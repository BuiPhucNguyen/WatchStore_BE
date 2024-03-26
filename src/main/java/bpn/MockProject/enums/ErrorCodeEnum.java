package bpn.MockProject.enums;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodeEnum {
	OK(200, 200, "OK"), NO_CONTENT(204, 20401, "Not have data"),
	NO_HAVE_ID_BRAND(204, 20402, "Not have data for brand"),
	NO_ENOUGH_PRODUCT_STOCK(204, 20403, "Not enough stock for product"), 
	BAD_REQUEST(400, 40000, "Bad request"),

	WRONG_STAR(400, 40000, "WRONG type or number star"),
	INVALID_ENTITY(400, 40001, "Entity not exist"), 
	INVALID_CREATE(400, 40002, "Create not success"),
	INVALID_DELETE(400, 40004, "Delete not success, entity is used"), 
	INVALID_UPDATE(400, 40003, "Update not success"),
	INVALID_ENTITY_ACTIVE(400, 40005, "Entity not active"),
	EXISTED_EMAIL_ACCOUNT(400, 40006, "This email is registered"),
	EXISTED_USERNAME_ACCOUNT(400, 40007, "This username is registered"),
	CANT_CANCEL_COMPLETED_ODER(400, 40008, "Can't cancel completed order"),
	CANT_COMPLETE_CANCELED_ODER(400, 40009, "Can't complete canceled order"),
	INVALID_NUMBER_PRODUCT_STOCK(400, 40010, "The number of stock product must be >= 0"),
	FILE_PATH_EXPORT_EXISTED(400, 40011, "Filepath is existed"),
	NOT_CREATED_BY_ACTIVE_ACCOUNT(400, 40012, "Not created by active account"),
	INTERNAL_SERVER_ERROR(500, 50000, "Internal Server Error"), 
	FORBIDDEN(403, 403, "Access is denied"),
	UNAUTHORIZED(401, 401, "Not authenticated"),
	INVALID_USERNAME_OR_PASSWORD(401, 402, "Username or password is incorrect"),
	INVALID_OLD_PASSWORD(401, 403, "Old password is incorrect");

	private static Map<Integer, ErrorCodeEnum> map = new HashMap<>();

	static {
		for (ErrorCodeEnum returnCode : ErrorCodeEnum.values()) {
			map.put(returnCode.code, returnCode);
		}
	}

	private int status;
	private int code;
	private String message;

	private ErrorCodeEnum(int status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public static ErrorCodeEnum valueOf(int code) {
		return map.get(code);
	}

	public int getStatus() {
		return status;
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.valueOf(status);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}