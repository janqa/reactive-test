package com.janqa.reactive;

import java.util.*;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String... args) throws InterruptedException {
        new Searcher().testMain();
    }

}
