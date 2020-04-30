package com.kerry.founder.basic.maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试位运算符
 * @author kerryhe
 * @date 2020/4/18
 */
@Slf4j
public class TestOperation {
    public static void main(String[] args) {
        String key = "abc";
        int h;
        int hCode = (h = key.hashCode()) ^ (h >>> 16);
        log.info("hCode:{}", hCode);

        int j = key.hashCode();
        int jCode = j ^ (j >>> 16);
        log.info("jCode:{}", jCode);

        String[] array = {"123", "456", "789", "012", "abc", "edf", "hij"};
        int n = array.length;
        for (String s : array) {
            log.info("{}.hashCode():{}", s, s.hashCode());
        }
        String s = array[n - 1 & hCode];
        log.info("array[s]:{}", s);
    }
}
