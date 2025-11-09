package com.jsatx.io;

import com.jsatx.core.DFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUtils {

    // ✅ Users can call this directly without try/catch
    public static DFrame readCSV(String filePath) throws IOException {
        String csvText = Files.readString(Paths.get(filePath));
        return DFrame.fromCSV(csvText);
    }


    // ✅ Same logic for writing CSV (no need for try/catch in user code)
    public static void toCSV(DFrame df, String outputPath) {
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
