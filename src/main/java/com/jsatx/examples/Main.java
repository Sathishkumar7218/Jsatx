package com.jsatx.examples;

import com.jsatx.core.pd;
import com.jsatx.core.DFrame;

public class Main {
    public static void main(String[] args) {
        DFrame df = pd.read_csv("sample.csv");

        System.out.println("\n✅ Full DataFrame:");
        df.show();

        System.out.println("\n🔹 df.head(2):");
        df.head(2);

        System.out.println("\n🔹 df.tail(2):");
        df.tail(2);

        System.out.println("\n🔹 df.info():");
        df.info();

        System.out.println("\n🔹 df.describe():");
        df.describe();

        pd.to_csv(df, "output.csv"); // test saving
    }
}
