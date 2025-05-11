package validation;
import java.util.List;

public class ValidationUtils {

    public static boolean alertMessageContainsText(String expected) {
        boolean contains;
        contains = ActualMessages.getActualMessage().contains(expected);
        ActualMessages.clear();
        return contains;
    }

    public static boolean alertMessageEqualsText(String expected) {
        boolean isEqual;
        isEqual = ActualMessages.getActualMessage().equals(expected);
        ActualMessages.clear();
        return isEqual;
    }

    public static boolean isStringInList(String stringToCheck, List<String> stringList) {
        for (String actual : stringList) {
            if (actual.equals(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    public static boolean textContainsString(String stringToCheck, List<String> stringList) {
        for (String actual : stringList) {
            if (actual.contains(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    public static boolean allTheElementsContainText(String stringToCheck, List<String> stringList) {
        for (String actual : stringList) {
            if (!actual.contains(stringToCheck)) {
                return false;
            }
        }
        return true;
    }

}
