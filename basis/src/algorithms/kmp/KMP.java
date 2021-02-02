package algorithms.kmp;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMP {
    public static int kmp(String string, String pattern) {
        int stringLen = string.length();
        int patternLen = pattern.length();

        // 如果主串长度 < 模式串长度，必定无法匹配
        if (stringLen < patternLen) {
            return -1;
        }

        int ptrString = 0;
        int ptrPattern = 0;

        int[] match = new int[patternLen];

        buildMatch(pattern, match);

        System.out.println("match: " + Arrays.toString(match));

        while (ptrString < stringLen && ptrPattern < patternLen) {
            if (string.charAt(ptrString) == pattern.charAt(ptrPattern)) {
                ptrString++;
                ptrPattern++;
            } else if (ptrPattern > 0) {
                // 若主串和模式串的当前字符不相等，则回溯模式串
                ptrPattern = match[ptrPattern - 1] + 1;
            } else {
                // 回溯到了模式串起点
                ptrString++;
            }
        }

        // 如果模式串已扫描完，说明成功匹配，故(主串指针 - 模式串长度)即为模式串在主串中的起始位置
        return ptrPattern == patternLen ? ptrString - patternLen : -1;
    }

    private static void buildMatch(String pattern, int[] match) {
        match[0] = -1;

        for (int j = 1; j < pattern.length(); j++) {
            // 前一个字符的前缀的下标
            int i = match[j - 1];

            /*
             * 如果当前字符不是前一个字符的前缀的下一位，就进入回溯
             * 结束条件有两种情况：
             * 1. 回回溯到某个前缀，其下一位等于当前字符，说明找到了该字符的前缀；
             * 2. 回溯到了没有前缀的字符，且该字符的下一位不等于当前字符，说明根本找不到该字符的前缀.
             */
            while (pattern.charAt(j) != pattern.charAt(i + 1) && i >= 0) {
                i = match[i];
            }

            if (pattern.charAt(j) == pattern.charAt(i + 1)) {
                /*
                 * 两种情况：
                 * 1. 当前字符就是前一个字符的前缀的下一位，不需回溯(没有进入while)；
                 * 2. 回溯到某个前缀的下一位等于当前字符，说明找到了该字符的前缀(while的第1种情况).
                 * 这两种情况下都将当前字符的前缀记为回溯指针的下一位
                 */
                match[j] = i + 1;
            } else {
                /*
                 * 回溯到了没有前缀的字符，且该字符的下一位不等于当前字符，说明根本找不到该字符的前缀(while的第2种情况)
                 */
                match[j] = -1;
            }
        }
    }

    public static void main(String[] args) {
        String string = "abcabxyabcabaexy";
        String pattern = "abcabae";

        int pos = kmp(string, pattern);

        if (pos == -1) {
            System.out.println("Not found.");
        } else {
            System.out.println(pos);
        }
    }
}
