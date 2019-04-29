package vlad.shumilov;

import com.sun.istack.internal.NotNull;

public class Anagram {

    protected char[] strArray;

    public Anagram(@NotNull String str) {
        setStrArray(str);
    }

    public void setStrArray(@NotNull String str) {
        strArray = str.toCharArray();
        doAnagram(strArray.length);
    }

    public void doAnagram(@NotNull int n) {
        if (n == 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            doAnagram(n - 1);

            swap(n);

            if (n == 2) {
                System.out.println(String.valueOf(strArray) + "\n");
            }
        }
    }

    protected void swap(@NotNull int n) {
        int start = strArray.length - n;
        char first = strArray[start];
        start++;

        for (int i = start; i < strArray.length; i++) {
            strArray[i - 1] = strArray[i];
        }

        strArray[strArray.length - 1] = first;
    }
}
