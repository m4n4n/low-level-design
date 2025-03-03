package test;

public class Test <T extends MessageService> {

        T variable;
        public Test(T messageService) {
            variable = messageService;
        }

//        public MessageService smsService = new SMSService();
//
//        public NotificationService notificationService = new NotificationService(smsService);
//
//        notificationService.notify();
        public void printTest(){
            System.out.println("I am of type " + variable.getClass().getName());
        }

}
