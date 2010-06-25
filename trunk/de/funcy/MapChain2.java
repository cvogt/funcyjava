package de.funcy;

import java.util.Collection;

public class MapChain2<From, To> implements
		FunctionalAction<Collection<From>, Collection<To>> {
	private Collection<? extends FunctionalAction> actions;

	public MapChain2(Collection<? extends FunctionalAction> actions) {
		this.actions = actions;
	}

	public Collection<To> apply(Collection<From> c) {
		return (Collection<To>) actions.iterator().next().apply(c);
	}
}
