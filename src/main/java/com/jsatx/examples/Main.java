package com.jsatx.examples;

import com.jsatx.core.BasicDataFrame;
import com.jsatx.io.CsvUtils;

public class Main {
    public static void main(String[] args) {
        String path = "sample.csv";
        BasicDataFrame df = CsvUtils.readCSV(path);

        System.out.println("\n📊 Full DataFrame:");
        df.show();

        System.out.println("\n🔍 df.head(2):");
        df.head(2);

        System.out.println("\n🔍 df.tail(2):");
        df.tail(2);

        System.out.println("\nℹ️ df.info():");
        df.info();

        System.out.println("\n📈 df.describe():");
        df.describe();
    }
}
