package com.yunmu.core.exception;

/**
 * 文件没找到异常 ClassName:MyFileNotFoundException
 *
 * @author ligy-008494
 * @create 2019-09-19 17:34
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

