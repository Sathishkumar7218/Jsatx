package com.jsatx.core;

import java.util.*;
import java.util.stream.Collectors;

public class DFrame {
    private final List<String> columns;
    private final List<Row> rows;

    public DFrame(List<String> columns, List<Row> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public static DFrame fromCSV(String csvText) {
        String[] lines = csvText.split("\\r?\\n");
        List<String> headers = Arrays.asList(lines[0].split(","));
        List<Row> rows = new ArrayList<>();

        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            List<String> fixed = new ArrayList<>(Arrays.asList(values));

            // pad missing columns
            while (fixed.size() < headers.size()) {
                fixed.add("");
            }

            // trim extra columns
            if (fixed.size() > headers.size()) {
                fixed = fixed.subList(0, headers.size());
            }

            rows.add(new Row(fixed));
        }

        return new DFrame(headers, rows);
    }


    public DFrame filter(String column, String value) {
        int colIndex = columns.indexOf(column);
        List<Row> filtered = rows.stream()
                .filter(r -> r.get(colIndex).equals(value))
                .collect(Collectors.toList());
        return new DFrame(columns, filtered);
    }

    public void show() {
        System.out.println(String.join(" | ", columns));
        System.out.println("----------------------------");
        for (Row row : rows) {
            System.out.println(row);
        }
    }

    // 👇 Add these two getters below show(), before the final }
    public List<String> getColumns() {
        return columns;
    }

    public List<Row> getRows() {
        return rows;
    }
    // === Pandas-like inspection methods ===

    // show first n rows
    public void head(int n) {
        System.out.println("Head (" + n + " rows)");
        System.out.println(String.join(" | ", columns));
        System.out.println("----------------------------");
        rows.stream().limit(n).forEach(System.out::println);
    }

    // show last n rows
    public void tail(int n) {
        System.out.println("Tail (" + n + " rows)");
        System.out.println(String.join(" | ", columns));
        System.out.println("----------------------------");
        rows.stream()
                .skip(Math.max(0, rows.size() - n))
                .forEach(System.out::println);
    }

    // return shape (rows, cols)
    public String shape() {
        return "(" + rows.size() + ", " + columns.size() + ")";
    }

    // print basic info
    public void info() {
        System.out.println("DataFrame Info:");
        System.out.println("Shape: " + shape());
        for (int i = 0; i < columns.size(); i++) {
            String col = columns.get(i);
            Object firstVal = rows.isEmpty() ? null : rows.get(0).get(i);
            String type = firstVal != null ? firstVal.getClass().getSimpleName() : "Unknown";
            System.out.printf("  %s (%s)%n", col, type);
        }
    }


    // describe numeric columns
    public void describe() {
        System.out.println("Descriptive statistics (numeric columns):");
        for (int i = 0; i < columns.size(); i++) {
            try {
                List<Double> values = new ArrayList<>();
                for (Row row : rows) {
                    Object val = row.get(i);
                    if (val != null && !val.toString().isBlank()) {
                        values.add(Double.parseDouble(val.toString()));
                    }
                }
                if (values.isEmpty()) continue;

                double sum = values.stream().mapToDouble(Double::doubleValue).sum();
                double mean = sum / values.size();
                double min = values.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
                double max = values.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);

                System.out.printf("%s → count=%d, mean=%.2f, min=%.2f, max=%.2f%n",
                        columns.get(i), values.size(), mean, min, max);
            } catch (NumberFormatException e) {
                // skip non-numeric columns
            }
        }
    }

}
