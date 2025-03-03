package concert;

import java.util.HashMap;

public class NotificationFactory {

    private final HashMap<NotificationType, NotificationService> services = new HashMap<>();

    public NotificationFactory() {
        services.put(NotificationType.EMAIL, new EmailService());
        services.put(NotificationType.SMS, new SMSService());
    }

    public NotificationService getNotificationService(NotificationType type) {
        return services.get(type);
    }
}
