package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;

class WordGeneratorTest {
    private WordGenerator wg;

    @BeforeEach
    void runBefore() {
        this.wg = new WordGenerator();
    }

    @Test
    void getRandomWordListTestSameNumber() {
        List<String> newRandomList1 = this.wg.getRandomWordList(5);
        List<String> newRandomList2 = this.wg.getRandomWordList(5);
        assertFalse(newRandomList1.equals(newRandomList2));
        }

    @Test
    void getRandomWordListTestDifferentNumber() {
        List<String> newRandomList1 = this.wg.getRandomWordList(5);
        List<String> newRandomList2 = this.wg.getRandomWordList(2);
        assertFalse(newRandomList1.equals(newRandomList2));
    }
}
