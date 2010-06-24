package de.funcy;

import java.util.Collection;
import java.util.List;

public class MapChain<From, To> implements
		FunctionalAction<Collection<From>, To> {
	private Collection<FunctionalAction<? extends Collection<?>, ? extends Collection<?>>> actions;

	public MapChain(
			Collection<FunctionalAction<? extends Collection<?>, ? extends Collection<?>>> actions) {
		this.actions = actions;
	}

//	// FIXME: the test somehow fail requiring this constructor, but it is there
//	public MapChain(List<FunctionalAction<? extends Collection<? extends Object&Serializable&Comparable<?>>,Collection<Integer>>> actions)
//		this.actions = actions;
//	}

	public To apply(final Collection<From> from) throws ClassCastException {
		return new Reduction2<FunctionalAction<? extends Collection<?>, ? extends Collection<?>>, To>() {
			public To function(FunctionalAction action, To to) {
				if (to == null) {
					return (To) action.apply(from);
				}
				return (To) action.apply(to);
			}
		}.apply(this.actions);
	}
}
