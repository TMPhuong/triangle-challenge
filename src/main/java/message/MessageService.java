package message;

public interface MessageService {

    /**
     * Get string by a given key. This should be used for string to be displayed in UI
     * @param key
     * @return String to be displayed in UI
     */
    String getString(String key);

    /**
     * Default function to get string by given key and additional objects.
     * @param key should point to a string which can be used in {@link String#format(String, Object...)} with additional objects
     * @param objects additional objects to be inserted into the returned string
     * @return String to be displayed in UI
     * @see #getString(String)
     */
    default String getString(String key, Object... objects) {
        return String.format(getString(key), objects);
    }
}
