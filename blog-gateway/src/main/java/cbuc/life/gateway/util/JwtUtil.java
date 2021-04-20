package cbuc.life.gateway.util;

import cbuc.life.common.entity.enums.ResponseEnum;
import cbuc.life.common.exception.Assert;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: cwx
 * @Date: Create in 20:44 2021/4/20
 * @Description: jwt工具类
 */
public class JwtUtil {


    /**
     * 过期时间30分钟
     */
    private static final long EXPIRE_TIME = 30L * 24 * 60 * 60 * 1000;

    /**
     * 密钥
     */
    private static final String SECRET = "blog-secret";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(username).asString().equals(username);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        Date now = new Date();
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(now)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 获取请求中的token,首先从请求头中获取,如果没有,则尝试从请求参数中获取
     *
     * @param request 请求
     * @return token
     */
    public static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Assert.isNotBlank(token, ResponseEnum.TOKEN_IS_NULL);
        return token;
    }


}
