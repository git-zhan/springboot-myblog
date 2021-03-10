package com.zxx.blog.util.data;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {
	
	
	public static <T> Boolean isBlank(Iterable<T> iterable) {
		return iterable == null || !iterable.iterator().hasNext();
	}

	public static <T> Boolean isNotBlank(Iterable<T> iterable) {
		return !isBlank(iterable);
	}
	
	public static <T extends Object> Stream<T> filter(List<T> list,Predicate<T> filter) {
		return list.stream().filter(filter);
	}
	
	public static <T extends Object> List<T> filterAsList(List<T> list,Predicate<T> filter) {
		return list.stream().filter(filter).collect(Collectors.toList());
	}
	
	public static <T, R> Set<? extends R> mapAsSet(List<T> list,Function<? super T, ? extends R> mapper) {
		return list.stream().map(mapper).collect(Collectors.toSet());
	}
}
