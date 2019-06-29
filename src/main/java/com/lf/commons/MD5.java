package com.lf.commons;

import java.security.MessageDigest;

/*
MD5的作用
	①一致性检验
	②数字签名。只是把md5看成了一个指纹，按了个手印说明独一无二了。
	③安全访问认证
	在用户注册时，会将密码进行md5加密，存到数据库中。这样可以防止那些可以看到数据库数据的人，恶意操作了。

md5不能破解吗？
	md5是不可逆的，也就是没有对应的算法，从生产的md5值逆向得到原始数据。
	但是如果使用暴力破解，那就另说了。

*/
public class MD5 {

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 测试
	public static void main(String[] args) {
		// System.out.println("caidao的MD5加密后：\n"+Tddd.MD5("caidao"));
		// System.out.println("http://www.baidu.com/的MD5加密后：\n"+Tddd.MD5("http://www.baidu.com/"));
		System.out.println(MD5.md5("admin"));
		//System.out.println(MD5.md5("1"));

	}
}
