package com.ccclubs.test;

public class RegexTest {

    public static void main(String[] args) {
        extractDigital("CsOrderCluster@5639");
    }

    public static String extractDigital(String str) {
        if(str==null) {
            return null;
        }
        str = str.trim();
        str = str.replaceAll("\\d", "");
        System.out.println(str);
        return str;
    }
}
