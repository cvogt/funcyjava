package de.funcy;

import java.util.Collection;

public abstract class ReduceInitial<From, To> implements
		FunctionalAction<Collection<? extends From>, To> {
	public abstract To reduce(From a, To b);

	public To apply(Collection<? extends From> c, To initial) {
		for (From next : c) {
			initial = this.reduce(next, initial);
		}
		return initial;
	}

	public To apply(Collection<? extends From> c) {
		return this.apply(c, null);
	}
}
	