package org.green.hr.exception;


import static org.green.core.constant.Constant.BAD_REQUEST;
import static org.green.core.constant.Constant.INTERNAL_SERVER_ERROR;
import static org.green.core.constant.Constant.NOT_FOUND;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  DEPARTMENT_EXIST(BAD_REQUEST, "Departement already exists", HttpStatus.BAD_REQUEST),
  DEPARTMENT_NOT_FOUND(NOT_FOUND, "Departement not found", HttpStatus.NOT_FOUND),
  DEPARTMENT_NAME_INVAILD(BAD_REQUEST,
      "Department name must be less than or equal to 50 characters", HttpStatus.BAD_REQUEST),
  DEPARTMENT_BLANK(BAD_REQUEST, "Department can not be blank", HttpStatus.BAD_REQUEST),

  DESCRIPTION_INVAILD(BAD_REQUEST, "Description must be less than or equal to 50 characters",
      HttpStatus.BAD_REQUEST),
  DESCRIPTION_BLANK(BAD_REQUEST, "Description cannot be blank", HttpStatus.BAD_REQUEST),

  STATUS_NULL(BAD_REQUEST, "Status cannot be null", HttpStatus.BAD_REQUEST),

  UNCATEGORIZED_EXCEOPTION(INTERNAL_SERVER_ERROR, "Uncategorized error",
      HttpStatus.INTERNAL_SERVER_ERROR);

  ErrorCode(int code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;

}