package com.yunmu.core.exception;

/**
 * 文件异常 ClassName:FileStorageException
 *
 * @author ligy-008494
 * @create 2019-09-19 17:34
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

