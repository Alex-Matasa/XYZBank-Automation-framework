package helperMethods;

public class StringUtils {

    public static String trimSpacesToOne(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        return trimmed.replaceAll("\\s+", " ");
    }
}

