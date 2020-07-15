package com.hope.saas.api.util.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hope.saas.api.util.exception.SecurityCheckException;
import com.hope.saas.common.constants.TokenConstants;
import com.hope.saas.common.table.wms.CustomerInfoTable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/4/15 18:02
 */
public class JwtUtil {

    /**
     * 生成Token
     *
     * @param tenantId
     * @param shipperCode
     * @return
     * @throws Exception
     */
    public static String createToken(String tenantId, String shipperCode) throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, TokenConstants.EXPIRATION);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", TokenConstants.ALG);
        map.put("typ", TokenConstants.TYP);

        String token = JWT.create()
                .withHeader(map)//头
                .withClaim(CustomerInfoTable.TENANT_ID, tenantId)
                .withClaim(CustomerInfoTable.SHIPPER_CODE, shipperCode)
                .withSubject(TokenConstants.SUBJECT)//
                .withIssuedAt(new Date())//签名时间
                .withExpiresAt(expireDate)//过期时间
                .sign(Algorithm.HMAC256(TokenConstants.SECRET));//签名
        return token;
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TokenConstants.SECRET)).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new SecurityCheckException("请求凭证已过期，请重新获取");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }

}
