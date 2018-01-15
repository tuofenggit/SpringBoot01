package com.example.web.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 *
 *
 *jsonwebtoken 和 auth0 是两种方式
 *
 * @author wangchuang
 *
 */
public class Jwt_JsonWebToken {

    private static final String SECRET_KEY = "_MeD5CiNov0_TecH";
    /**
     * 签名算法
     */
    private static final SignatureAlgorithm DEFAULTSIGN = SignatureAlgorithm.HS256;

    /**
     *  根据 userId 生成 token
     * @param userId
     * @return
     */
    public static String createToken(String userId) {
        Claims claim = new DefaultClaims();
        // claim.setAudience("www.bizconf.cn");// 设置接收方
        // 设置 签发人
        long timeStamp = (new Date()).getTime();
        claim.setIssuer("goldwind");
        claim.put("userId", userId);
        claim.put("timeStamp",timeStamp);
        Key signingKey = getKey(SECRET_KEY, DEFAULTSIGN.getJcaName());
        JwtBuilder builder = Jwts.builder().setClaims(claim).signWith(DEFAULTSIGN, signingKey);
        return builder.compact();
    }

    /**
     * 该方法使用 HS256 算法和 Secret: 生成signKey
     *
     * @param key
     * @param JcaName
     * @return
     */
    private static Key getKey(String key, String jcaName) {
        byte[] keyb = null;
        if ("".equals(key) || key == null) {
            keyb = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        } else {
            keyb = DatatypeConverter.parseBase64Binary(key);
        }
        String jcaName1 = null;
        if ("".equals(jcaName) || jcaName == null) {
            jcaName1 = DEFAULTSIGN.getJcaName();
        } else {
            jcaName1 = jcaName;
        }

        Key signKey = new SecretKeySpec(keyb, jcaName1);
        return signKey;
    }

    /**
     * 解析 token
     *
     * @param token
     * @return
     */
    public static String parserToken(String token) throws Exception{

        Claims claims = null;
        String userid="-1";
            claims = Jwts.parser().setSigningKey(getKey(SECRET_KEY, DEFAULTSIGN.getJcaName())).parseClaimsJws(token)
                    .getBody();
            userid=(String)claims.get("userId");
          return userid;
    }
    
    /**
     * 获得userId 如果解析错误返回 -1
     * @param token
     * @return
     */
    public static String getUserIdByToken(String token) {

        Claims claims = null;
        String userid="-1";
        try {
            claims = Jwts.parser().setSigningKey(getKey(SECRET_KEY, DEFAULTSIGN.getJcaName())).parseClaimsJws(token)
                    .getBody();
            userid=(String)claims.get("userId");
        } catch (Exception e) {
            e.printStackTrace();
            return userid;
        }
        return userid;
    }

    public static void main(String[] args) {
        String token = createToken("1");
        System.out.println(token);
    }

}
