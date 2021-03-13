package com.janqa.algorythms.search;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortedArray<T> {

    private final T[] a;
    private Comparator<T> comparator;

    public SortedArray(Collection<T> a, Comparator<T> comparator) {
        this.a = (T[]) a.stream().sorted(comparator).collect(Collectors.toUnmodifiableList()).toArray();
        this.comparator = comparator;
    }

    public boolean contains(T o) {
        return search(o, a, 0, a.length - 1, comparator) != -1;
    }

    private SearchResult search(T o) {
        SearchResult searchResult = new SearchResult();
        int res = search(o, a, 0, a.length - 1, this::compare);
        searchResult.setAny(res != -1);
        return searchResult;
    }

    private static <S> int search(S o, S[] a, int i, int j, Comparator<S> comparator) {
        if (i == j) {
            return comparator.compare(o, a[i]) == 0 ? i : -1;
        }
        int p = (j + i) / 2;
        System.out.println(p);
        int res = comparator.compare(o, a[p]);
        if (res < 0) {
            return search(o, a, i, p - 1, comparator);
        } else if (res > 0) {
            return search(o, a, p + 1, j, comparator);
        } else {
            return p;
        }
    }

    private int compare(T o1, T o2) {
        return comparator.compare(o1, o2);
    }

    public static class SearchResult {
        private int compares;
        private boolean isAny;

        public int getCompares() {
            return compares;
        }

        public void setCompares(int compares) {
            this.compares = compares;
        }

        public boolean isAny() {
            return isAny;
        }

        public void setAny(boolean any) {
            isAny = any;
        }
    }
}
