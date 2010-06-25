package de.funcy;

import java.util.Collection;

abstract public class FilterMapReduceInitial<From, Intermediate, To> implements FunctionalAction<Collection<From>, To>{
	protected abstract Boolean filter(From from);
	protected abstract Intermediate map(From from);
	protected abstract To reduce(Intermediate a, To b);

	public To apply(Collection<From> from) {
		return this.apply(from, null);
	}
	public To apply(Collection<From> from, To initial) {
		final FilterMapReduceInitial<From, Intermediate, To> c = this;
		return new ReduceInitial<Intermediate,To>() {
			public To reduce(Intermediate a, To b) {
				return c.reduce( a, b );
			}
		}.apply(
			new Map<From, Intermediate>() {
				protected Intermediate map(From from) {
					return c.map( from );
				}
			}.apply(
					new Filter<From>() {
						protected Boolean filter(From from) {
							return c.filter( from );
						}
					}.apply(from)
			),
			initial
		);
	}
}