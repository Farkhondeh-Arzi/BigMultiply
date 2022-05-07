package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            BigInt b1 = new BigInt(scanner.next());
            BigInt b2 = new BigInt(scanner.next());

            MultiplyBigInt multiplyBigInt = new MultiplyBigInt(b1, b2);
            System.out.println(multiplyBigInt.getResultArray());
        }

    }
}

class BigInt {

    private ArrayList<Integer> array = new ArrayList<>();

    BigInt(String s) {

        stringToArray(s);
    }

    void stringToArray(String num) {

        for (int i = 0; i < num.length(); i++) {
            array.add(num.charAt(i) - '0');
        }

    }

    public ArrayList<Integer> getArray() {
        return array;
    }
}

class MultiplyBigInt {

    private ArrayList<Integer> resultArray;

    MultiplyBigInt(BigInt Int1, BigInt Int2) {
        resultArray = multiply(Int1.getArray(), Int2.getArray());
    }

    ArrayList<Integer> multiply(ArrayList<Integer> X, ArrayList<Integer> Y) {

        ArrayList<Integer> result = new ArrayList<>();

        if ((X.size() == 1 && X.get(0) == 0) || (Y.size() == 1 && Y.get(0) == 0)) {
            result.add(0);
        } else if (X.size() == 0 || Y.size() == 0) {

            result.add(0);

        } else if (X.size() == 1 && Y.size() == 1) {

            result.add(X.get(0) * Y.get(0));

        } else {

            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            ArrayList<Integer> c = new ArrayList<>();
            ArrayList<Integer> d = new ArrayList<>();

            int aSize = (X.size() + 1) / 2;
            int cSize = (Y.size() + 1) / 2;

            for (int i = 0; i < aSize; i++) {
                a.add(X.get(i));
            }

            for (int i = aSize; i < X.size(); i++) {
                b.add(X.get(i));
            }

            for (int i = 0; i < cSize; i++) {
                c.add(Y.get(i));
            }

            for (int i = cSize; i < Y.size(); i++) {
                d.add(Y.get(i));
            }

            ArrayList<Integer> ac = multiply(a, c);
            int I = 0;
            int l = 1;
            int o = 2;
            for (int i = 0; i < X.size() / 2 + Y.size() / 2; i++) ac.add(0);

            ArrayList<Integer> ad = multiply(a, d);
            for (int i = 0; i < X.size() / 2; i++) ad.add(0);

            ArrayList<Integer> bc = multiply(b, c);
            for (int i = 0; i < Y.size() / 2; i++) bc.add(0);

            result = add(add(add(ac, ad), bc), multiply(b, d));
        }

        return result;

    }

    ArrayList<Integer> add(ArrayList<Integer> x, ArrayList<Integer> y) {

        ArrayList<Integer> sumArray = new ArrayList<>();

        int carryOver = 0, sum;

        Collections.reverse(x);
        Collections.reverse(y);

        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            sum = carryOver + x.get(i) + y.get(i);

            if (sum >= 10) {
                carryOver = sum / 10;
                sum %= 10;
            } else carryOver = 0;

            sumArray.add(sum);
        }

        for (int i = Math.min(x.size(), y.size()); i < Math.max(x.size(), y.size()); ++i) {

            if (x.size() > y.size()) {

                sum = carryOver + x.get(i);

                if (sum >= 10) {
                    carryOver = sum / 10;
                    sum %= 10;
                } else carryOver = 0;

                sumArray.add(sum);

            } else if (x.size() < y.size()) {

                sum = carryOver + y.get(i);

                if (sum >= 10) {
                    carryOver = sum / 10;
                    sum %= 10;
                } else carryOver = 0;

                sumArray.add(sum);
            }

        }

        if (carryOver != 0) {
            sumArray.add(carryOver);
        }

        Collections.reverse(sumArray);
        Collections.reverse(x);
        Collections.reverse(y);

        return sumArray;
    }

    public String getResultArray() {

        StringBuilder s = new StringBuilder();

        for (Integer integer : resultArray) {
            s.append(integer);
        }

        return s.toString();
    }
}