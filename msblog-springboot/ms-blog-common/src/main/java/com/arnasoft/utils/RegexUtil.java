package com.arnasoft.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 判断输入的邮箱格式是否正确
     * @param str 输入的邮箱地址
     * @return 返回邮箱地址是否正确
     */
    public static boolean isMail(String str) {
        boolean flag = false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(str);
        if(m.matches())
            flag = true;
        else
            System.out.println("输入邮箱格式错误......");
        return flag;
    }

    /**
     * 验证是否是正确合法的手机号码
     *
     * @param telephone
     * @return 合法返回true，不合法返回false
     */
    public static boolean isCellPhoneNo(String telephone) {
        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,4,5,6,7,8,9]\\d{9}$");
        Matcher matcher = pattern.matcher(telephone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为有效身份证号
     * @param str
     * @return
     */
    public static boolean isCheckIdNumber(String str) {
        String patt="";

        if(str.length()==15)
            patt="^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$";
        else if(str.length()==18)
            patt="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        else
            return false;

        Pattern r = Pattern.compile(patt);
        Matcher matcher = r.matcher(str);
        return matcher.find();
    }
}
