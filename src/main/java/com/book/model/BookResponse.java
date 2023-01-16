package com.book.model;

import com.book.Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BookResponse {
    private int status;
    private String timestamp;
    private Object data;
    public static BookResponse of(int status, Object data){
        return BookResponse.of(status, DateUtils.getCurrenDateTimeStr(), data);
    }
    public static BookResponse of(int status){
        return BookResponse.of(status, DateUtils.getCurrenDateTimeStr());
    }
}
