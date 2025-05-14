package helperMethods;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String trimSpacesToOne(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        return trimmed.replaceAll("\\s+", " ");
    }

    public static List<String> extractSublist(List<String> list, int index) {
        List<String> sublist = new ArrayList<>();

        for (String customer : list) {
            String[] parts = customer.split(" ");
            if (index < parts.length) {
                sublist.add(parts[index]);
            }
        }

        return sublist;
    }
}

