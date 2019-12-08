package com.bjunicom.scservice.utils;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/3
 * @Description: com.bjunicom.scservice.utils
 * @version: 1.0
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
