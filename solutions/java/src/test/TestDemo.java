package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestDemo {
    public static void run() {

        MessageService smsService = new SMSService();

        NotificationService notificationService = new NotificationService(smsService);

        notificationService.notify("Hello!");
        Collection<Integer> c = null;

        Consumer<Integer> c1 = System.out::println;

        // Takes an integer and returns bool
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println("Testing predicate comes out to be " + isEven.test(3));


        // Takes a string, returns an integer
        Function<String, Integer> someFunction = x -> x.length();
        System.out.println("Testing function comes out to be " + someFunction.apply("Hello!"));



        List<String> list = Arrays.asList("My", "name", "is", "Manan");

        List<String> modifiedList = list.stream()
                .map(x -> x.toUpperCase())
                .toList();

        List<String> filteredList = list.stream()
                        .filter(s -> s.startsWith("M"))
                .toList();

        list.forEach(System.out::println);
        modifiedList.forEach(System.out::println);
        filteredList.forEach(System.out::println);

        Test<SMSService> smsTesting = new Test<>(new SMSService());
        smsTesting.printTest();
        Object obj = new Object();
    }
}
