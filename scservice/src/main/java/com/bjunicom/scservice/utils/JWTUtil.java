package com.bjunicom.scservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    // 过期时间30分钟
    private static final long EXPIRE_TIME = 30*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 管理员的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String admin_oa, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("admin_oa", admin_oa)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getAdmin_oa(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("admin_oa").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param admin_oa 管理员oa
     * @param secret 管理员的密码
     * @return 加密的token
     */
    public static String sign(String admin_oa, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("admin_oa", admin_oa)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
