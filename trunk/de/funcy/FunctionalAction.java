package de.funcy;

public interface FunctionalAction<From, To> {
	public To apply(From from);
}
