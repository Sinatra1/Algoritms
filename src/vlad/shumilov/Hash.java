package vlad.shumilov;

import java.util.concurrent.ThreadLocalRandom;

public class Hash {
    public static final int hashTableSize = 2048;
    protected static final int isFreeCell = -1;
    protected static final int isRemovedCell = -2;
    protected Integer[] hashTable = new Integer[hashTableSize];

    public Hash() {
        initHashTable();
    }

    public void add(Integer value) {
        checkValue(value);

        int j = getNewIndexByValue(value);

        hashTable[j] = value;
    }

    public int get(Integer index) {
        if (index == null || index > hashTableSize) {
            throw new RuntimeException("index is null or index > hashTableSize in Hash");
        }

        return hashTable[index];
    }

    public void remove(Integer value) {
        checkValue(value);

        int j = getIndexByValue(value);

        hashTable[j] = isRemovedCell;
    }

    public Integer findIndex(Integer value) {
        checkValue(value);

        int j = getIndexByValue(value);

        if (hashTable[j] > 0) {
            return j;
        }

        return hashTable[j];
    }

    protected void initHashTable() {
        for (int i = 0; i < hashTableSize; i++) {
            hashTable[i] = isFreeCell;
        }
    }

    protected int getIndexByValue(int value) {
        int j = hashFunction(value);
        int step = hashFunctionTwo(value);

        while (hashTable[j] >= 0 && hashTable[j] != value) {
            j = (j + step) % hashTableSize;
        }

        return j;
    }

    protected int getNewIndexByValue(int value) {
        int j = hashFunction(value);
        int step = hashFunctionTwo(value);

        while (hashTable[j] >= 0) {
            j = (j + step) % hashTableSize;
        }

        return j;
    }

    protected int hashFunction(int key) {
        return (5 * (key >> 3) + 7) % hashTableSize;
    }

    protected int hashFunctionTwo(int key) {
        return key % 97 + 1;
    }

    protected void checkValue(Integer value) {
        if (value == null || value < 0) {
            throw new RuntimeException("value is null in Hash");
        }
    }
}
