package com.janqa.reactive;

import com.janqa.algorythms.search.SortedArray;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortedArrayTest  {
    //@Test
    public void testArray() {
        Collection<Integer> a = IntStream.range(0, 1000000).mapToObj(Integer::valueOf).collect(Collectors.toUnmodifiableList());
        SortedArray<Integer> arr = new SortedArray(a, Comparator.comparing(Function.identity()));
        //arr.contains(10);
       // assertTrue(arr.contains(11));
    }

}