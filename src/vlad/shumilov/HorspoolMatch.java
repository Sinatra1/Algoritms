package vlad.shumilov;

import java.util.HashMap;

public class HorspoolMatch {
    protected String substring;
    protected HashMap<String, Integer> table = new HashMap<>();

    public HorspoolMatch(String substring) {
        if (substring == null || substring.isEmpty()) {
            throw new RuntimeException("substring is empty");
        }

        this.substring = substring;

        createTable();
    }

    protected void createTable() {
        int length = substring.length() - 1;

        for (int i = 0; i < length; i++) {
            table.put(substring.substring(i, i + 1), length - i);
        }
    }

    public int match(String text) {
        if (text == null || text.isEmpty()) {
            throw new RuntimeException("text is empty");
        }

        int m = substring.length();
        int n = text.length();

        for (int i = m-1; i < n; i += getShift(text.substring(i, i + 1))) {
            int k = 0;
            while (k < m && substring.substring(m-k-1, m-k).equals(text.substring(i-k, i-k+1))) {
                k++;

                if (k == m) {
                    return i - m + 1;
                }
            }
        }

        return -1;
    }

    protected int getShift(String key) {
        if (table.containsKey(key)) {
            return table.get(key);
        }

        return substring.length();
    }
}
