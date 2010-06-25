package de.funcy;

import java.util.Collection;

public class Chain<From extends Collection, To> implements
		FunctionalAction<From, To> {
	private Collection<? extends FunctionalAction> actions;

	public Chain(Collection<? extends FunctionalAction> actions) {
		this.actions = actions;
	}

	public To apply_(From c) {
		return (To) actions.iterator().next().apply(c);
	}

	public To apply(final From from) throws ClassCastException {
		return new Reduction2<FunctionalAction, To>() {
			public To function(FunctionalAction action, To to) {
				if (to == null) {
					return (To) action.apply(from);
				}
				return (To) action.apply(to);
			}
		}.apply(this.actions);
	}
}
