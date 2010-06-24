package de.funcy;

import java.util.Collection;

public abstract class Reduction2<From, To> implements
		FunctionalAction<Collection<From>, To> {
	public abstract To function(From a, To b);

	public To apply(Collection<From> c, To initial) {
		for (From next : c) {
			initial = this.function(next, initial);
		}
		return initial;
	}

	public To apply(Collection<From> c) {
		return this.apply(c, null);
	}
}
