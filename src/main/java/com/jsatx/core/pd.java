package com.jsatx.core;

import com.jsatx.io.CsvUtils;
import java.io.IOException;

public class pd {

    // ✅ Reads a CSV file and returns a DFrame
    public static DFrame read_csv(String path) {
        try {
            return CsvUtils.readCSV(path);
        } catch (IOException e) {
            System.err.println("❌ Error reading CSV file: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // ✅ Writes a DFrame to CSV
    public static void to_csv(DFrame df, String outputPath) {
        CsvUtils.toCSV(df, outputPath);
    }
}
