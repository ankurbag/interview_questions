package ctci.utils;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 */
public class StreamUtil<T> implements Spliterator<T> {
	private final Spliterator<T> source;
	private final Predicate<T> condition;
	private boolean conditionHolds = true;
	private StreamUtil(Spliterator<T> source, Predicate<T> condition) {
		this.source = source;
		this.condition = condition;
	}

	public static <T> Stream<T> takeWhile(final T seed, final UnaryOperator<T> f, Predicate<T> condition) {
		Stream<T> source = Stream.iterate(seed, f);
		return StreamSupport.stream(StreamUtil.over(source.spliterator(), condition), false);
	}

	public static <T> StreamUtil<T> over(Spliterator<T> source, Predicate<T> condition) {
		return new StreamUtil<>(source, condition);
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		return conditionHolds && source.tryAdvance(e -> {
			if (conditionHolds = condition.test(e)) {
				action.accept(e);
			}
		});
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return conditionHolds ? source.estimateSize() : 0;
	}

	@Override
	public int characteristics() {
		return source.characteristics() & ~Spliterator.SIZED;
	}

	@Override
	public Comparator<? super T> getComparator() {
		return source.getComparator();
	}
}

