package de.funcy.rtt;

import java.util.Collection;

import de.funcy.FunctionalAction;

public class ReductionChain<From, To> extends Chain<Collection<From>, To> {
	public ReductionChain(Collection<? extends FunctionalAction> actions) {
		super(actions);
	}

}
