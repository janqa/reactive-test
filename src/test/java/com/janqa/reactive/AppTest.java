package com.janqa.reactive;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppTest extends TestCase {
    private Random random = new Random();
    private Supplier<Set<UserInfo>> treeFactory = () -> new TreeSet(Comparator.comparing(UserInfo::getName));
    private Collector<UserInfo, ?, Set<UserInfo>> collector = Collectors.toCollection(treeFactory);

    @Test
    public void testMain() {
        IntStream.range(0, 20).map(i -> pow(2, i)).mapToDouble(this::ratio).forEach(System.out::println);
    }

    private float ratio(int n) {
        Collection<UserInfo> list = Collections.unmodifiableCollection(getUsers(n));
        //Map<Long, UserInfo> map = list.stream().collect(Collectors.toMap(UserInfo::getNum, Function.identity()));
        Set<UserInfo> tree = list.stream().collect(Collectors.toCollection(treeFactory));
        long randomLongInBounds = random.nextInt(n);
        // System.out.println(time(v -> list.stream().filter(u -> u.getNum() == randomLongInBounds).findAny().get()));
        //System.out.println(time(v -> map.get(randomLongInBounds)));
        long t1 = time(v -> list.stream().takeWhile(u -> u.getName().equals(getRandomName()))
                .collect(Collectors.toList()));
        long t2 = time(v -> tree.stream().takeWhile(u -> u.getName().equals(getRandomName()))
                .collect(Collectors.toList()));
        return (float) t1 / t2;
    }

    private Collection<UserInfo> getUsers(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new UserInfo(getRandomName(), i)).collect(Collectors.toSet());
    }

    private String getRandomName() {
        return String.valueOf(Math.random()).substring(2, 3);
    }

    long time(Function<Void, ?> f) {
        long t1 = System.nanoTime();
        f.apply(null);
        long t2 = System.nanoTime();
        return t2 - t1;
    }

    int pow(int a, int n) {
        if (n == 0)
            return 1;
        else
            return a * pow(a, n - 1);
    }
}