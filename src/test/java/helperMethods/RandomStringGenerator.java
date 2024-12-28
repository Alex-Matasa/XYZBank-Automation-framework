package helperMethods;
import java.util.Random;

public class RandomStringGenerator {

    public static String generateRandomString(int length, String type) {
        String characters = "";
        switch (type.toLowerCase()) {
            case "alphanumeric":
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                break;
            case "alphabetic":
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;
            case "numeric":
                characters = "0123456789";
                break;
            case "ascii":
                characters = generateAsciiCharacters();
                break;
            case "nonascii":
                characters = generateNonAsciiCharacters();
                break;
            default:
                throw new IllegalArgumentException("Invalid type specified. Choose from 'alphanumeric', 'alphabetic', 'numeric', 'ascii', or 'nonascii'.");
        }

        return getRandomStringFromCharacters(length, characters);
    }

    private static String getRandomStringFromCharacters(int length, String characters) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

    private static String generateAsciiCharacters() {
        StringBuilder ascii = new StringBuilder();
        for (int i = 32; i <= 126; i++) { // Printable ASCII range
            ascii.append((char) i);
        }
        return ascii.toString();
    }

    private static String generateNonAsciiCharacters() {
        // Generate a range of sample non-ASCII characters, e.g., Latin Extended, Greek, etc.
        StringBuilder nonAscii = new StringBuilder();
        for (int i = 128; i <= 255; i++) { // Basic Extended ASCII range
            nonAscii.append((char) i);
        }
        return nonAscii.toString();
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(generateRandomString(10, "alphanumeric"));
        System.out.println(generateRandomString(5, "ascii"));
        System.out.println(generateRandomString(5, "nonascii"));
    }
}
