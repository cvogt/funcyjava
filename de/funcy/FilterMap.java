package de.funcy;

import java.util.Collection;

abstract public class FilterMap<From, To> implements
		FunctionalAction<Collection<From>, Collection<To>> {
	protected abstract Boolean filter(From from);

	protected abstract To map(From from);

	public Collection<To> apply(Collection<From> from) {
		final FilterMap<From, To> c = this;

		return new Map<From, To>() {
			protected To map(From from) {
				return c.map(from);
			}
		}.apply(new Filter<From>() {
			protected Boolean filter(From from) {
				return c.filter(from);
			}
		}.apply(from));
	}
}