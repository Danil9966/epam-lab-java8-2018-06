package streams;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class ToPropertiesCollector implements Collector<Map.Entry<String, String>, Properties, Properties> {

    @Override
    public Supplier<Properties> supplier() {
        return Properties::new;
    }

    @Override
    public BiConsumer<Properties, Map.Entry<String, String>> accumulator() {
        return (properties, entry) -> properties.put(entry.getKey(), entry.getValue());
    }

    @Override
    public BinaryOperator<Properties> combiner() {
        return (left, right) -> {
            left.putAll(right);
            return left;
        };
    }

    @Override
    public Function<Properties, Properties> finisher() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
    }
}


class Launcher {

    public static void main(String[] args) {
        Stream<AbstractMap.SimpleEntry<String, String>> stream = Stream.of(new HashMap.SimpleEntry<>("key1", "value1"),
                                                                           new HashMap.SimpleEntry<>("key2", "value2"));
        Properties result = stream.collect(new ToPropertiesCollector());
        System.out.println(result.getProperty("key1"));
        System.out.println(result.getProperty("key2"));
    }
}