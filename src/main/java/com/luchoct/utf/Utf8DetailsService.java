package com.luchoct.utf;

import com.luchoct.utf.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class Utf8DetailsService {

    private boolean isUtf8Character(final String utf8Character) {
        return ((utf8Character.length() == 2) && Character.isHighSurrogate(utf8Character.charAt(0)))
                || ((utf8Character.length() == 1) && !Character.isHighSurrogate(utf8Character.charAt(0)));
    }

    private int getCodePoint(final String utf8Character) {
        if (utf8Character.length() > 1) {
            return Character.toCodePoint(utf8Character.charAt(0), utf8Character.charAt(1));
        } else {
            return Character.codePointAt(utf8Character, 0);
        }
    }

    private String getUnicodeValue(final int codePoint) {
        return String.format("U+%04x", codePoint).toUpperCase();
    }

    private String getDecimalValue(final String hexadecimalValue) {
        final StringBuilder decimalValue = new StringBuilder();

        final String[] hexadecimalPairs = StringUtils.getInstance().split(hexadecimalValue, 2);
        for (int i = 0; i < hexadecimalPairs.length; i++) {
            decimalValue.append(String.valueOf(Long.parseLong(hexadecimalPairs[i], 16))).append(" ");
        }
        return decimalValue.toString().trim();
    }

    private String getHexadecimalValue(final String binaryValue) {
        return Long.toHexString(Long.parseLong(binaryValue, 2)).toUpperCase();
    }

    private String getHexadecimalValue(final int codePoint) {
        final String binaryUTF8Value = getBinaryValue(codePoint);
        return getHexadecimalValue(binaryUTF8Value);
    }

    private String getBinaryValue(final int codePoint) {
        final char padding = '0';
        final StringUtils utilities = StringUtils.getInstance();
        if (codePoint < 128) {
            //000000-00007F

            //Padding until we have got 8 binary characters.
            return utilities.padToLength(Integer.toBinaryString(codePoint), padding, 8);

        } else if (codePoint < 2048) {
            //000080-0007FF

            //Padding until we have got 16 binary characters.
            final String binaryCodePoint = utilities.padToLength(Integer.toBinaryString(codePoint), padding, 16);

            return "110" + binaryCodePoint.substring(5, 10) + "10" + binaryCodePoint.substring(10, 16);

        } else if (codePoint < 65536) {
            //000800-00FFFF

            //Padding until we have got 16 binary characters.
            final String binaryCodePoint = utilities.padToLength(Integer.toBinaryString(codePoint), padding, 16);

            return "1110" + binaryCodePoint.substring(0, 4) + "10" + binaryCodePoint.substring(5, 10) + "10"
                    + binaryCodePoint.substring(10, 16);

        } else {
            //010000-10FFFF

            //Padding until we have got 24 binary characters.
            final String binaryCodePoint = utilities.padToLength(Integer.toBinaryString(codePoint), padding, 24);

            return "11110" + binaryCodePoint.substring(3, 6) + "10" + binaryCodePoint.substring(6, 12) + "10"
                    + binaryCodePoint.substring(12, 18) + "10" + binaryCodePoint.substring(18, 24);
        }
    }

    public Utf8Details getDetails(String utf8Character) {
        if (isUtf8Character(utf8Character)) {
            final int codePoint = getCodePoint(utf8Character);
            final String hexadecimalValue = getHexadecimalValue(codePoint);
            return new Utf8Details(getUnicodeValue(codePoint), getDecimalValue(hexadecimalValue), hexadecimalValue);
        } else {
            throw new WrongUtf8CharacterException(utf8Character);
        }
    }
}
