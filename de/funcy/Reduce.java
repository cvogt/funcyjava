package de.funcy;

import java.util.Collection;

public abstract class Reduce<Type> implements
		FunctionalAction<Collection<Type>, Type> {
	public abstract Type reduce(Type a, Type b);

	public Type apply(Collection<Type> c) {
		Type value = null;
		for (Type next : c) {
			if (value == null) {
				value = next;
			} else {
				value = this.reduce(value, next);
			}
		}
		return value;
	}
}
