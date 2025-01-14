package helperMethods;

public class UtilityMethods {

    public static String modifyString(String input) {
        if (input == null) {
            return "";
        }

        String trimmed = input.trim();
        String modified = trimmed.replaceAll("\\s+", " ");

        return modified;
    }

    public static int parseStringToInt(String input) {
        try {
            // Parse the string into an integer
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Handle the exception for invalid input
            System.err.println("Invalid input: " + input);
            throw e; // Rethrow or handle as per your need
        }
    }

}