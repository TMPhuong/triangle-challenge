import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import controller.*;
import message.LocalizeMessageService;
import message.MessageService;
import model.Console;
import validator.TriangleSideLengthFromStringValidator;
import validator.Validator;


public class ConsoleCodingTestModule extends AbstractModule {

    @Provides
    @Singleton
    @Console
    public TriangleProcessor providesTriangleProcessor() {
        MessageService messageService = new LocalizeMessageService();
        Validator<String[]> validator = new TriangleSideLengthFromStringValidator(messageService);
        TriangleFactory factory = new TriangleWithSideLengthFactory();
        ErrorService errorService = new SimpleConsoleErrorService(messageService);
        TriangleProcessor processor = new ConsoleTriangleProcessor(factory, validator, messageService, errorService);
        return processor;
    }


    @Override
    protected void configure() {

    }
}
