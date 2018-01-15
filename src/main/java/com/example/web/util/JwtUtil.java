package com.example.web.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * 通过 jwt 生成token 及 验证
 *
 * @author wangchuang
 */
public class JwtUtil {

	private static final String SECRET_KEY = "eerlMujdEc7YZUXw4LCmESldmFyjZLis";
	/**
	 * 签名算法
	 */
	private static final SignatureAlgorithm DEFAULTSIGN = SignatureAlgorithm.HS256;

	/**
	 * 根据 userId 生成 token
	 * 
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws IllegalArgumentException
	 */
	public static String createToken(String userId)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		Claims claim = new DefaultClaims();
		// claim.setAudience("www.bizconf.cn");// 设置接收方
		// 设置 签发人
		long timeStamp = (new Date()).getTime();

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("alg", "HS256");
		hashMap.put("typ", "JWT");

		claim.setIssuer("BPmeLyRm5mybgYguVOlN3TcKysKAfwn5");
		claim.put("username", "zhangsan");
		Key signingKey = getKey(SECRET_KEY, DEFAULTSIGN.getJcaName());

		String token = JWT.create().withHeader(hashMap).withClaim("iss", "BPmeLyRm5mybgYguVOlN3TcKysKAfwn5")
				.withClaim("username", "zhangsan").sign(Algorithm.HMAC256(SECRET_KEY));
		// JwtBuilder builder = Jwts.builder().setClaims(claim).signWith(DEFAULTSIGN,
		// signingKey);
		return token;
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
	public static String parserToken(String token) throws Exception {

		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();

		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Map<String, Claim> map = jwt.getClaims();
		System.out.println(map.get("username").asString());
		return jwt.getClaims().toString();
	}

	public static void main(String[] args) throws Exception {
		String token = createToken("1");
		// eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJndyIsInVzZXJJZCI6IuW8oOS4iSJ9.M94aWta78LxBlGrWtErQfebwns9ZOobdrtz6qZUg-28
		// System.out.println(parserToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCUG1lTHlSbTVteWJnWWd1Vk9sTjNUY0t5c0tBZnduNSIsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.HpHuYBCcFb6xZdRucbB7Qb9GdzgM7u2rJsrwJ1I1n2o"));
		// System.out.println(parserToken(token));
		System.out.println(createToken(""));
		System.out.println(parserToken(createToken("")));
		//
		// eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnb2xkd2luZCIsInVzZXJJZCI6IjEiLCJ0aW1lU3RhbXAiOjE1MTEyNTEwNzgxOTB9.JuZrMTXqZGQKBAMkyXJXSC4SBmyspV8LKfftmdn4jBE

		// System.out.println(parserToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnb2xkd2luZCIsInVzZXJJZCI6IjEiLCJ0aW1lU3RhbXAiOjE1MTEyNTEwNTYwODB9.wOgoyZ2hmUigubKZ4sGDDkhNfU-8jmKOTBZpJWn4Vkw"));
		// System.out.println(parserToken("eyJhbGciOiJIUzI1NiJ9.df.JuZrMTXqZGQKBAMkyXJXSC4SBmyspV8LKfftmdn4jBE"));

		// String s=
		// parserToken("eyJhbGciOiJIUzI1NiJ9.df.JuZrMTXqZGQKBAMkyXJXSC4SBmyspV8LKfftmdn4jBE");
		// System.out.println(s);
	}

}
