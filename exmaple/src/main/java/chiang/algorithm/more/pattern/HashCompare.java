package chiang.algorithm.more.pattern;

/**
 * 字符串匹配
 *
 * 输入：text = "antaprezatepzapreanta"
 * 输出：11
 * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
 * <a href="https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/">题目地址</a>
 */
@SuppressWarnings("all")
class HashCompare {

    private final long MOD = (long)(1e9 + 7);
    private long[] pow26;

    /**
     * 预处理，根据字符串的长度先把幂运算计算好
     */
    public int longestDecomposition(String text) {

        pow26 = new long[text.length()];
        pow26[0] = 1;
        for(int i = 1; i < pow26.length; i ++)
            pow26[i] = pow26[i - 1] * 26 % MOD;

        return solve(text, 0, text.length() - 1);
    }

    private int solve(String s, int left, int right){

        if(left > right) return 0;

        long preHash = 0, postHash = 0;
        for(int i = left, j = right; i < j; i ++, j --){

            // 利用hash对比前后字母是否能成为一段文，避免的每次都要去equal循环比较
            // 但是hash不能保证100%的一样，所以hash相同还是要做一次对比
            preHash = (preHash * 26 + (s.charAt(i) - 'a')) % MOD;
            postHash = ((s.charAt(j) - 'a') * pow26[right - j] + postHash) % MOD;

            if(preHash == postHash && equal(s, left, i, j, right)){
                // 递归查还为比较的字符串
                return 2 + solve(s, i + 1, j - 1);
            }

        }
        return 1;
    }

    /**
     * 对比字符串中有没有一段，是一对的。我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
     *
     * @param s 字符串
     * @param left 上层左边的起点
     * @param pi 根据上层循环的i，不断+和pj对比
     * @param pj 根据上层循环的j，不断+和pi对比
     * @param right 上层右边的起点
     * @return
     */
    private boolean equal(String s, int left, int pi, int pj, int right){

        for(int i = left, j = pj; i <= pi && j <= right; i ++, j ++){
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int i = new HashCompare().longestDecomposition("antaprezatepzapreanta");
        System.out.println(i);
    }
}