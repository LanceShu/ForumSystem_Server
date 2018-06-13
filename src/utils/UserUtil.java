package utils;

public class UserUtil {
    private UserUtil(){}
    private static String author;
    private static final String lock = "lock";
    public static void setUserInstance(String username) {
        if (author == null) {
            synchronized (lock) {
                if (author == null) {
                    author = username;
                    lock.notifyAll();
                }
            }
        }
    }

    public static String getUserInstance() {
        if (author == null) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return author;
    }

    public static void setInstanceNull() {
        if (author != null) {
            author = null;
        }
    }
}
