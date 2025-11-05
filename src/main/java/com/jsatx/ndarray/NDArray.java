package com.jsatx.ndarray;


import java.util.Arrays;


public class NDArray {
    private final double[] data;
    private final int[] shape;
    private final int size;


    public NDArray(int... shape) {
        this.shape = shape.clone();
        int s = 1;
        for (int v : shape) s *= v;
        this.size = s;
        this.data = new double[s];
    }


    public NDArray(double[] flat, int... shape) {
        this.shape = shape.clone();
        int s = 1;
        for (int v : shape) s *= v;
        if (s != flat.length) throw new IllegalArgumentException("Shape mismatch");
        this.size = s;
        this.data = flat.clone();
    }


    public int[] shape() { return shape.clone(); }
    public int size() { return size; }


    public double get(int index) { return data[index]; }
    public void set(int index, double value) { data[index] = value; }


    // simple element-wise add
    public NDArray add(NDArray other) {
        if (this.size != other.size) throw new IllegalArgumentException("Size mismatch");
        double[] out = new double[size];
        for (int i = 0; i < size; i++) out[i] = this.data[i] + other.data[i];
        return new NDArray(out, shape);
    }


    public NDArray add(double scalar) {
        double[] out = new double[size];
        for (int i = 0; i < size; i++) out[i] = this.data[i] + scalar;
        return new NDArray(out, shape);
    }


    public double[] toFlatArray() { return data.clone(); }


    @Override public String toString() {
        return "NDArray(shape=" + Arrays.toString(shape) + ", data=" + Arrays.toString(data) + ")";
    }
}