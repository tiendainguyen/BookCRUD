package com.book.model;

import com.book.Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthResponse {
  private int status;
  private String timestamp;
  private Object data;
  public static AuthResponse of(int status, Object data){
    return AuthResponse.of(status, DateUtils.getCurrenDateTimeStr(), data);
  }
  public static AuthResponse of(int status){
    return AuthResponse.of(status, DateUtils.getCurrenDateTimeStr());
  }
}
