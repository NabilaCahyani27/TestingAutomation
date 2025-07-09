package selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStream {
    public static void main(String[] args) {
        // This is a placeholder for the actual implementation
        // You can add your Java Stream logic here
        System.out.println("Java Stream example is running.");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> resultMoreThanFive = new ArrayList<>();


        // insert number > 5 into result
        for (Integer number : numbers) {
            if (number > 5) {
                resultMoreThanFive.add(number);
            }
        }


        resultMoreThanFive = numbers.stream().filter(integer -> integer > 5).collect(Collectors.toList());
        System.out.println("Numbers greater than 5: " + resultMoreThanFive);
    }
}
