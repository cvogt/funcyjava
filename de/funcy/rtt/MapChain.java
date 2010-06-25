package de.funcy.rtt;

import java.util.Collection;

import de.funcy.FunctionalAction;

public class MapChain<From, To> extends Chain<Collection<From>, Collection<To>> {
	public MapChain(Collection<? extends FunctionalAction> actions) {
		super(actions);
	}

}
