package algorithm.miscs.bloomFilter.test;

import algorithm.miscs.bloomFilter.BloomFilter;
import algorithm.miscs.bloomFilter.bloomFilterImplementation.BloomFilterSalt;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class BloomFilterSaltUnitTest {
    private ByteArrayOutputStream errStream;
    private PrintStream errOrig;
    private Random random;
    @Before
    public void setUp() throws Exception {
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        errOrig = System.err;
        System.setErr(err);
        random = new Random();
    }
    @After
    public void tearDown() throws Exception {
        System.setErr(errOrig);
    }

    //purpose : check basic operation
    @Test
    public void test1() {
        BloomFilter bf = new BloomFilterSalt(3000000, 6);
        File file = new File("/Users/baozhenchen/Downloads/wordlist.txt");
        int numberOfLines = 0;
        List<String> trueWords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                line = line.trim();
                bf.addItem(line);
                numberOfLines++;

                //randomly collect some words for later test use
                if (random.nextInt(10) == 0) {
                    trueWords.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int falsePositiveCounts = 0;
        for (int i = 0; i < trueWords.size(); i++) {
            Assert.assertTrue(bf.mayContains(trueWords.get(i)));
            if (bf.mayContains(trueWords.get(i) + "mustNonExist")) {
                falsePositiveCounts++;
            }
        }
        System.out.println(numberOfLines + " words added");
        System.out.println("capacity ratio = " + String.format( "%.3f",bf.getCapacityRatio()) + "%\n" );
        System.out.println(trueWords.size() + " true words tested all passed\n");
        System.out.println(trueWords.size() + " FAKE words tested:");
        System.out.println("\tfalse positive count : " + falsePositiveCounts + " out of " + trueWords.size() + " tests ");
        System.out.println("\tfalse positive rate : " + String.format( "%.3f", 100.0 * falsePositiveCounts / trueWords.size()) + "%");
    }
}
