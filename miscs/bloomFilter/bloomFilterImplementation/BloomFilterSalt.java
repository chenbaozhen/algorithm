/*
Author: Baozhen Chen
2019

 */
package algorithm.miscs.bloomFilter.bloomFilterImplementation;

import algorithm.miscs.bloomFilter.BloomFilter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
 * An implementation of the bloom filter.
 * The data content is string time.
 *
 */
public class BloomFilterSalt implements BloomFilter {

    /**
     * The byte array storing the contents
     *
     */
    protected byte[] bytesArray;

    /**
     * The size of the bit Map
     */
    protected final int BIT_MAP_SIZE;

    /**
     * The number of Hashing functions
     */
    protected final int NUMBER_OF_HASH_FUNCTIONS;

    /**
     * The random strings used to generate different hashcode
     */
    protected final String[] SALT_LIST;

    /**
     * The number of words added to the bloom filter so far
     */
    protected int currentCount;

    /**
     * Constructs an bloom filter.
     *
     * @param bitMapSize the size of the bit map
     * @param numberOfHashFunctions the number of HashFunctions used for this bloom filter
     */
    public BloomFilterSalt(int bitMapSize, int numberOfHashFunctions) {
        NUMBER_OF_HASH_FUNCTIONS = numberOfHashFunctions;
        BIT_MAP_SIZE = bitMapSize;
        int bytesArraySize = BIT_MAP_SIZE / 8;
        if ( BIT_MAP_SIZE % 8 != 0) bytesArraySize++;
        bytesArray = new byte[bytesArraySize];
        SALT_LIST = generateSalt(NUMBER_OF_HASH_FUNCTIONS);
        currentCount = 0;
    }

    /**
     * Generate a list of strings used as salt to generate different hashcode
     *
     * @param numberOfHashFunctions the number of HashFunctions used for this bloom filter
     */
    private String[] generateSalt(int numberOfHashFunctions) {
        Set<Integer> saltSet = new HashSet<>();
        Random random = new Random();
        String[] saltList = new String[numberOfHashFunctions];
        int index = 0;
        while (saltSet.size() < numberOfHashFunctions) {
            int salt = random.nextInt(Integer.MAX_VALUE);
            if (saltSet.add(salt)) {
                saltList[index++] = Integer.toString(salt);
            };
        }
        return saltList;
    }

    /**
     * Add element to the bloom filter
     *
     * @param str the word to add
     */
    @Override
    public void addItem(String str) {
        if (str == null || str.length() == 0) return;
        str = str.toLowerCase();
        for (String salt : SALT_LIST) {
            int[] pos = getPos(salt + str);
            if ((bytesArray[pos[0]] & (1 << pos[1])) == 0) {
                currentCount++;
                bytesArray[pos[0]] |= (1 << pos[1]);
            }
        }
    }

    /**
     * Add element to the bloom filter
     *
     * @param saltedString the word after adding the salt
     *
     * @return an array the first number is the index in the byte array
     * the second element is the bit number in that bit
     */
    private int[] getPos(String saltedString) {
        int hashCode = saltedString.hashCode() % BIT_MAP_SIZE;
        if (hashCode < 0) hashCode += BIT_MAP_SIZE;
        return new int[]{hashCode /8 , hashCode % 8};
    }

    /**
     * check if the bloomfilter may contain this word
     *
     * @param str the word to test
     *
     * @return the element may contains this word or does not contain this array
     */
    @Override
    public boolean mayContains(String str) {
        if (str == null || str.length() == 0) return false;
        str = str.toLowerCase();
        for (String salt : SALT_LIST) {
            int[] pos = getPos(salt + str);
            if ((bytesArray[pos[0]] & (1 << pos[1])) == 0) return false;
        }
        return true;
    }

    /**
     * get current count of elements
     *
     * @return the number of elements
     */
    @Override
    public int getCurrentCount() {
        return currentCount;
    }

    /**
     * get the capacity filled ratio
     *
     * @return the filled ratio
     */
    @Override
    public double getCapacityRatio() {
        return 1.0 * currentCount / BIT_MAP_SIZE;
    }

    /*
     * get the size of the bit map
     *
     * @return the bit map size
     */
    @Override
    public int getBitMapSize() {
        return BIT_MAP_SIZE;
    }

}
