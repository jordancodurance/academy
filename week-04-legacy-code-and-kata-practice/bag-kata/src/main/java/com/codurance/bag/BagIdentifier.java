package com.codurance.bag;

import java.util.Objects;

public class BagIdentifier {

    private final int value;

    public BagIdentifier(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagIdentifier that = (BagIdentifier) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
