package util.math;

import java.util.ArrayList;
import java.util.List;

public class BigInteger {
    private List<Byte> pointer;

    public  BigInteger(List<Byte> p) {
        pointer = p;
    }

    public static BigInteger stringToBigInteger(String data) {
        List<Byte> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                byte num = Byte.parseByte(data.substring(i,i+1));
                result.add(num);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return new BigInteger(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte num: pointer) {
            sb.append(num);
        }
        return sb.toString();
    }

    public List<Byte> getPointer() {
        return pointer;
    }
}
