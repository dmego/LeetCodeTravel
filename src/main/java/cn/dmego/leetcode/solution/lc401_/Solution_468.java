package cn.dmego.leetcode.solution.lc401_;

/**
 * [468] 验证IP地址
 */
public class Solution_468 {
    private static final String IPv4 = "IPv4";
    private static final String IPv6 = "IPv6";
    private static final String Neither = "Neither";

    /**
     根据字符串中是否包含 '.' 或 ':' 来验证是 IPv4 还是 IPv6
     */
    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) {
            return solveIPv4(queryIP);
        } else if (queryIP.contains(":")) {
            return solveIPv6(queryIP);
        } else {
            return Neither;
        }
    }

    /**
     判断字符串是否是 IPv4 地址
     范围为 0 - 255
     地址内的数不会以 0 开头
    */
    public String solveIPv4(String IP) {
        // 以 . 分割，-1 表示如果最后一位是 . 也需要分割
        String[] ips = IP.split("\\.", -1);
        // IPv4 以 . 分割后数组长度为 4，不为 4 不符合
        if (ips.length != 4) return Neither;
        for (int i = 0; i < ips.length; i++) {
            // 每个 IPv4 地址位长度在 [1, 3] 之间
            if (ips[i] == null || ips[i].length() == 0 || ips[i].length() > 3) {
                return Neither;
            } else {
                // 如果长度大于 1 位，但是还是以 0 开头，不符合
                if (ips[i].length() > 1 && ips[i].startsWith("0")) {
                    return Neither;
                }
                // 遍历每个 IP 地址位的每个字符，只能是数字
                for (int j = 0; j < ips[i].length(); j++) {
                    if (ips[i].charAt(j) < '0' || ips[i].charAt(j) > '9') {
                        return Neither;
                    }
                }
                // 转为 int 类型
                int ipo = Integer.parseInt(ips[i]);
                // 范围必须在 [0, 255] 之间
                if (ipo < 0 || ipo > 255) {
                    return Neither;
                }
            }
        }
        return IPv4;
    }

    /**
     判断字符串是否是 IPv6 地址
     由8组16进制的数字来表示, 每组的长度范围为 [1, 4]
     加入一些以 0 开头的数字，字母可以使用大写(A~F)，也可以是小写(a~f)
     我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况
     多余的 0 也是不被允许的
     */
    public String solveIPv6(String IP) {
        // 以 : 分割，-1 表示如果最后一位是 : 也需要分割
        String[] ips = IP.split("\\:", -1);
        // IPv6 以 . 分割后数组长度为 8，不为 8 不符合
        if (ips.length != 8) return Neither;
        for (int i = 0; i < ips.length; i++) {
            // 每个 IPv4 地址位长度在 [1, 4] 之间
            if (ips[i] == null || ips[i].length() == 0 || ips[i].length() > 4) {
                return Neither;
            } else {
                // 遍历每个 IP 地址位的每个字符，字符范围必须在：[0, 9], [a, f], [A, F] 这三个区间
                for (int j = 0; j < ips[i].length(); j++) {
                    if (!(('A' <= ips[i].charAt(j) && ips[i].charAt(j) <= 'F') ||
                            ('a' <= ips[i].charAt(j) && ips[i].charAt(j) <= 'f') ||
                            ('0' <= ips[i].charAt(j) && ips[i].charAt(j) <= '9'))) {
                        return Neither;
                    }
                }
            }
        }
        return IPv6;
    }
}
