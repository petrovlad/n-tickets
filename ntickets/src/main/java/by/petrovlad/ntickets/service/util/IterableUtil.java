package by.petrovlad.ntickets.service.util;

import by.petrovlad.ntickets.model.dto.TicketDTO;
import by.petrovlad.ntickets.model.entity.Ticket;
import by.petrovlad.ntickets.model.mapper.TicketMapper;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IterableUtil {

    private IterableUtil() {}

    public static <T> List<T> asList(Iterable<T> iterable) {
        return asStream(iterable)
                .collect(Collectors.toList());
    }

    public static <T, R> List<T> asList(Iterable<T> iterable, Predicate<T> filter) {
        return asStream(iterable)
                .filter(filter)
                .collect(Collectors.toList());
    }

    public static <T, R> List<R> asList(Iterable<T> iterable, Predicate<T> filter, Function<T, R> mapper) {
        return asStream(iterable)
                .filter(filter)
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static <T> Stream<T> asStream(Iterable<T> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), false);
    }
}
