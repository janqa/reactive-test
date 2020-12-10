package com.janqa.reactive;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Searcher {
    private Random random = new Random();
    private Supplier<Set<UserInfo>> treeFactory = () -> new TreeSet(Comparator.comparing(UserInfo::getName));
    private Collector<UserInfo, ?, Set<UserInfo>> collector = Collectors.toCollection(treeFactory);

    public void testMain() {
        int bound = 10000000;
        Collection<UserInfo> list = Collections.unmodifiableCollection(getUsers(bound));
        Map<Long, UserInfo> map = list.stream().collect(Collectors.toMap(UserInfo::getNum, Function.identity()));
        Set<UserInfo> tree = list.stream().collect(Collectors.toCollection(treeFactory));
        long randomLongInBounds = random.nextInt(bound);
        System.out.println(time(v -> list.stream().filter(u -> u.getNum() == randomLongInBounds).findAny().get()));
        System.out.println(time(v -> map.get(randomLongInBounds)));
        System.out.println(time(v -> list.stream().takeWhile(u -> u.getName().equals(map.get(randomLongInBounds).getName()))));
        System.out.println(time(v -> tree.stream().takeWhile(u -> u.getName().equals(map.get(randomLongInBounds).getName()))));
    }

    private Collection<UserInfo> getUsers(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new UserInfo(Math.random() + "", i)).collect(Collectors.toSet());
    }

    long time(Function<Void, ?> f) {
        long t1 = System.nanoTime();
        f.apply(null);
        long t2 = System.nanoTime();
        return t2 - t1;
    }
}
