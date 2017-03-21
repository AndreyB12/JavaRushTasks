import java.util.ArrayList;
import java.util.List;

/**
 * Created by butkoav on 21.03.2017.
 */
public class TestStreams {

    public static void main(String...args)
    {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strs.add(String.valueOf(i));
        }

        strs.parallelStream().forEach(System.out::println);
     //   strs.stream().forEach(System.out::println);
    }
}
