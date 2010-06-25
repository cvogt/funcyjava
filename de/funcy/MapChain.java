package de.funcy;

import java.util.Collection;

public class MapChain<From, To> extends Chain<Collection<From>, Collection<To>> {
	public MapChain(Collection<? extends FunctionalAction> actions) {
		super(actions);
	}

}
