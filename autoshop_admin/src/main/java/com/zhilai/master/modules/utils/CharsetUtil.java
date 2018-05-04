package com.zhilai.master.modules.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharsetUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(java.nio.charset.Charset.forName("GB2312").newEncoder().canEncode("张三"));
		System.out.println(java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode("张三"));
		String a=null;
		try {
			a=new String("张三".getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
System.out.println(getCharset("张三"));
System.out.println(getCharset(a));   
    System.out.println(isMessyCode("Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));  
    System.out.println(isMessyCode("你好"));  
}  

	
	public static String getCharset(String s){
		if(null==s||"".equals(s.trim())){
		  return "utf-8";	
		}
		if(java.nio.charset.Charset.forName("GB2312").newEncoder().canEncode(s)){
			return "GB2312";
		}
		if(java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(s)){
			return "ISO-8859-1";
		}
		if(java.nio.charset.Charset.forName("utf-8").newEncoder().canEncode(s)){
			return "utf-8";
		}
		return "utf-8";
	}
	
	public static String guessEncoding(byte[] bytes) {
	    String DEFAULT_ENCODING = "UTF-8";
	    org.mozilla.universalchardet.UniversalDetector detector =
	        new org.mozilla.universalchardet.UniversalDetector(null);
	    detector.handleData(bytes, 0, bytes.length);
	    detector.dataEnd();
	    String encoding = detector.getDetectedCharset();
	    detector.reset();
	    if (encoding == null) {
	        encoding = DEFAULT_ENCODING;
	    }
	    return encoding;
	}
	
	/**
	 * 是否是中文
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
	
	/**
	 * 是否是乱码
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {  
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");  
        Matcher m = p.matcher(strName);  
        String after = m.replaceAll("");  
        String temp = after.replaceAll("\\p{P}", "");  
        char[] ch = temp.trim().toCharArray();  
        float chLength = ch.length;  
        float count = 0;  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (!Character.isLetterOrDigit(c)) {  
                if (!isChinese(c)) {  
                    count = count + 1;  
                }  
            }  
        }  
        float result = count / chLength;  
        if (result > 0.4) {  
            return true;  
        } else {  
            return false;  
        }  
   
    }  
}
