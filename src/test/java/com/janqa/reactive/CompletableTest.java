package com.janqa.reactive;

import org.junit.Assert;
import org.junit.Test;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompletableTest {
    List<Integer> services = List.of(200, 500, 1000, 300, 400, 1000, 980, 1000, 900, 800, 400, 700);

    @Test public void testCompletable() {
        long time = time(v -> services.stream().map(this::request).forEach(System.out::println));
        System.out.println(1e-6 * time);
        Assert.assertTrue(true);
    }

    @Test public void testCompletable2() {
        Set<CompletableFuture<Void>> futures = services.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> request((i))).thenAccept(System.out::println))
                .collect(Collectors.toSet());
        CompletableFuture all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[] {}));
        long time = time(v -> all.join());
        System.out.println(1e-6 * time);
        Assert.assertTrue(true);
    }

    public String request(Integer n) {
        timeout(n, ChronoUnit.MILLIS);
        return "Success";
    }

    private void timeout(int i, ChronoUnit millis) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    long time(Consumer<?> f) {
        long t1 = System.nanoTime();
        f.accept(null);
        long t2 = System.nanoTime();
        return t2 - t1;
    }
}
