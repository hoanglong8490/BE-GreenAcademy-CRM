package org.green.hr.exception;

import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException {

  private final ErrorCode errorCode;

  public DepartmentException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public DepartmentException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
