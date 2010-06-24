package de.funcy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Mapping<From, To> implements
		FunctionalAction<Collection<From>, Collection<To>> {
	public abstract To function(From a);

	public Collection<To> apply(Collection<From> from) {
		return this.apply(from, new ArrayList<To>());
	}

	public Collection<To> apply(Collection<From> from, Collection<To> to) {
		for (From o : from) {
			to.add(this.function(o));
		}
		return to;
	}
}