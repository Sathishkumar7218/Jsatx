package com.jsatx.core;

import java.util.List;

public class Row {
    private final List<String> values;

    public Row(List<String> values) {
        this.values = values;
    }

    public String get(int index) {
        return values.get(index);
    }

    @Override
    public String toString() {
        return String.join(" | ", values);
    }

    public List<String> getData() {
        return values;
    }

}
