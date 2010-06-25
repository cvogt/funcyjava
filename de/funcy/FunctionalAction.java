package de.funcy;

public interface FunctionalAction<From, To> { // FIXME: replace From by
	// Collection<From>
	public To apply(From a);
}
