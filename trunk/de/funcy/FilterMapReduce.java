package de.funcy;

import java.util.Collection;

abstract public class FilterMapReduce<From, To> implements FunctionalAction<Collection<From>, To>{
	protected abstract Boolean filter(From from);
	protected abstract To map(From from);
	protected abstract To reduce(To a, To b);
	public To apply(Collection<From> from) {
		final FilterMapReduce<From, To> c = this;
		return new Reduction<To>() {
			public To function(To a, To b) {
				return c.reduce( a, b );
			}
		}.apply(
			new Mapping<From, To>() {
				public To function(From from) {
					return c.map( from );
				}
			}.apply(
					new Filter<From>() {
						public Boolean function(From from) {
							return c.filter( from );
						}
					}.apply(from)
			)
		);
	}
}