/*
Author: Baozhen Chen
2019

 */
package algorithm.miscs.bloomFilter;
/*
An interface for bloomFilter
 */

public interface BloomFilter {
    /**
     * Add a string to the bloom filter
     * @param str the string to add to the bloom filter
     */
    public void addItem(String str);

    /**
     * check whether the dataset may contains the string
     * @param str the string to check
     */
    public boolean mayContains(String str);

    /**
     * get current count of elements
     *
     * @return the number of elements
     */
    public int getCurrentCount();

    /**
     * get the capacity filled ratio
     *
     * @return the filled ratio
     */
    public double getCapacityRatio();

    /*
     * get the size of the bit map
     *
     * @return the bit map size
     */
    public int getBitMapSize();
}
