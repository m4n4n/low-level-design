package test;

public class NotificationService {

    private final MessageService messageService;

    public NotificationService(MessageService messageService){
        // constructor
        this.messageService = messageService;
    }

    public void notify(String message) {
        messageService.send(message);
    }
}
