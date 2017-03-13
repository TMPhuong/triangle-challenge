package message;

import java.util.ResourceBundle;

/**
 * Service to get string based on default locale.<br/>
 * Multiple languages can be supported by adding more properties into resources
 * such as messages_fr.properties, messages_de.properties, etc.<br/>
 * English is supported by default
 */
public class LocalizeMessageService implements MessageService {

    private final ResourceBundle messageResources;

    public LocalizeMessageService() {
        messageResources = ResourceBundle.getBundle("messages");
    }

    @Override
    public String getString(String messageKey) {
        return messageResources.getString(messageKey);
    }

}
