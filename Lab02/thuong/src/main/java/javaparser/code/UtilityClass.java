package javaparser.code;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class UtilityClass {

    // Phương thức kiểm tra một số nguyên có phải là số nguyên tố hay không
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Phương thức chuyển một số nguyên sang dạng chuỗi nhị phân
    public static String toBinary(int number) {
        return Integer.toBinaryString(number);
    }

    // Phương thức chia một chuỗi thành các từ
    public static String[] splitWords(String text) {
        return text.split("\\s+");
    }

    // Phương thức loại bỏ các phần tử trùng lặp từ một danh sách
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
}