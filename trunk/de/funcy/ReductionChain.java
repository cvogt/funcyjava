package de.funcy;

import java.util.Collection;

public class ReductionChain<From, To> implements
		FunctionalAction<Collection<From>, To> {
	private Collection<FunctionalAction<?, ?>> actions;

	public ReductionChain(Collection<FunctionalAction<?, ?>> actions) {
		this.actions = actions;
	}

	public To apply(final Collection<From> from) throws ClassCastException {
		return new Reduction2<FunctionalAction<?, ?>, To>() {
			public To function(FunctionalAction action, To to) {
				if (to == null) {
					return (To) action.apply(from);
				}
				return (To) action.apply(to);
			}
		}.apply(this.actions);
	}
}
