package validation;

public class ActualMessages {

    private static final ThreadLocal<String> actualMessage = new ThreadLocal<>();

    public static void setActualMessage(String message) {
        actualMessage.set(message);
    }

    public static String getActualMessage() {
        return actualMessage.get();
    }

    public static void clear() {
        actualMessage.remove();
    }
}
