package test;

public class SMSService implements MessageService{


    public SMSService() {
    }

    @Override
    public void send(String message) {
        System.out.println("SMS being sent");
    }

}
