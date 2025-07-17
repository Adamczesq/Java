package solutions.runlenghtencoding;

public class RunLengthEncodingService {
    public static String compressRle(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder encodedString = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            char currentChar = text.charAt(i);
            int count = 0;

            while (i < text.length() && text.charAt(i) == currentChar) {
                count++;
                i++;
            }

            encodedString.append(count);
            encodedString.append(currentChar);
        }

        return encodedString.toString();
    }

    public static void main(String[] args) {
        System.out.println("'oooosssddqww' -> '" + compressRle("oooosssddqww") + "'");
        System.out.println("'dddcadt' -> '" + compressRle("dddcadt") + "'");
        System.out.println("'a' -> '" + compressRle("a") + "'");
        System.out.println("'' -> '" + compressRle("") + "'");
    }
}
