package de.funcy;

import java.util.Collection;

public class ReductionChain<From, To> extends Chain<Collection<From>, To> {
	public ReductionChain(Collection<? extends FunctionalAction> actions) {
		super(actions);
	}

}
