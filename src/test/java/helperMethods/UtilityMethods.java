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

}