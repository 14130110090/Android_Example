package com.example.boxuegu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 4);
    }

    @Test
    public void testStackTraceElements() {
        int[] m = new int[]{6, 5, 5};

        char[][] mm = new char[][]{{'1', '1', '1', '1', '0'}};

    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    Set<Integer> set = new HashSet<>();

    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return this.size() > 3;
        }
    };
}