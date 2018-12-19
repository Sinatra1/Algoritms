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

    }
}
