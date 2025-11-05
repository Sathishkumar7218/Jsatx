package com.jsatx.io;

import com.jsatx.core.BasicDataFrame;
import com.jsatx.core.Row;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUtils {

    // ✅ Users can call this directly without try/catch
    public static BasicDataFrame readCSV(String filePath) {
        try {
            String csvText = new String(Files.readAllBytes(Paths.get(filePath)));
            return BasicDataFrame.fromCSV(csvText);
        } catch (IOException e) {
            System.err.println("❌ Error reading CSV file: " + filePath);
            e.printStackTrace();
            return new BasicDataFrame(List.of(), List.of()); // return empty DataFrame
        }
    }

    // ✅ Same logic for writing CSV (no need for try/catch in user code)
    public static void toCSV(BasicDataFrame df, String outputPath) {
        try {
            List<String> lines = df.getRows().stream()
                    .map(row -> row.getData().stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(",")))
                    .collect(Collectors.toList());

            lines.add(0, String.join(",", df.getColumns()));
            Files.write(Paths.get(outputPath), lines);

            System.out.println("✅ CSV saved to " + outputPath);

        } catch (IOException e) {
            System.err.println("❌ Error writing CSV file: " + outputPath);
            e.printStackTrace();
        }
    }
}
