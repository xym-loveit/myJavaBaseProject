package com.xym.myJava;

/**
 * @author xym
 */
public class TestConvert {
    public static void main(String[] args) {

        Converter<String, Integer> converter = f -> Integer.valueOf(f);

        Converter<String, Integer> converter2 = Integer::valueOf;

//        Integer convert = converter.convert("123");
//        System.out.println(convert);


        Something something = new Something();
        Converter<String, String> converter1 = something::startsWith;
        System.out.println(converter1.convert("Java"));
    }

    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }
}
