class Solution {
    public int countCommas(int n) {
        int count = 0;

        // 1000 to n: each number has at least 1 comma
        if (n >= 1000) {
            count += n - 999;
        }

        // From 100000, numbers have one additional comma
        if (n >= 100000) {
            count += n - 100000;
        }

        return count;
    }
}