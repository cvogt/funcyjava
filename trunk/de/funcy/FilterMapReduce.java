package de.funcy;

import java.util.Collection;

abstract public class FilterMapReduce<From, To> implements FunctionalAction<Collection<From>, To>{
	protected abstract Boolean filter(From from);
	protected abstract To map(From from);
	protected abstract To reduce(To a, To b);
	public To apply(Collection<From> from) {
		final FilterMapReduce<From, To> c = this;
		return new Reduce<To>() {
			public To reduce(To a, To b) {
				return c.reduce( a, b );
			}
		}.apply(
			new Map<From, To>() {
				protected To map(From from) {
					return c.map( from );
				}
			}.apply(
					new Filter<From>() {
						protected Boolean filter(From from) {
							return c.filter( from );
						}
					}.apply(from)
			)
		);
	}
}