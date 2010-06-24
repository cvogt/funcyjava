package de.funcy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Filter<Type> implements
		FunctionalAction<Collection<Type>, Collection<Type>> {
	public abstract Boolean function(Type a);

	public Collection<Type> apply(Collection<Type> from) {
		return this.apply(from, new ArrayList<Type>());
	}

	public Collection<Type> apply(Collection<Type> from, Collection<Type> to) {
		for (Type o : from) {
			if (this.function(o)) {
				to.add(o);
			}
		}
		return to;
	}
}