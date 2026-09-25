import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Parses union expressions separated by commas
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip comma

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Parses concatenation of multiple factors
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next = parseFactor();

            // Cartesian product for concatenation
            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // Parses either a single letter or {...}
    private Set<String> parseFactor() {

        // If current character is '{'
        if (s.charAt(index) == '{') {
            index++; // skip '{'

            Set<String> result = parseExpression();

            index++; // skip '}'

            return result;
        }

        // Otherwise it is a lowercase letter
        Set<String> result = new HashSet<>();
        result.add(String.valueOf(s.charAt(index)));

        index++;

        return result;
    }
}