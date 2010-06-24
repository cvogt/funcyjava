package de.funcy.unittests;

import de.funcy.*;

import java.util.Arrays;
import java.util.Collection;

public class Run {

	public static void main(String[] args) {
		Collection<String> list = Arrays.asList("1", "2", "3", "4", "5", "6",
				"7");
		System.out.println(list);

		// map: String to Integer
		Collection<Integer> l3 = new Mapping<String, Integer>() {
			public Integer function(String s) {
				return Integer.valueOf(s);
			}
		}.apply(list);
		System.out.println(l3);

		// filter: even numbers
		Collection<Integer> l4 = new Filter<Integer>() {
			public Boolean function(Integer s) {
				return s % 2 == 0;
			}
		}.apply(l3);

		System.out.println(l4);

		// map with closure: divide every value by 2
		final int divisor = 2;
		Collection<Integer> l5 = new Mapping<Integer, Integer>() {
			public Integer function(Integer i) {
				return i / divisor;
			}
		}.apply(l4);

		System.out.println(l5);

		// reduce: compute the sum
		Integer sum = new Reduction<Integer>() {
			public Integer function(Integer a, Integer b) {
				return a + b;
			}
		}.apply(l5);
		System.out.println(sum);

		// chaining map and filter with final reduce (not type safe): do all at
		// once
		Integer result = new ReductionChain<String, Integer>(Arrays.asList(
				new Mapping<String, Integer>() {
					public Integer function(String s) {
						return Integer.valueOf(s);
					}
				}, new Filter<Integer>() {
					public Boolean function(Integer s) {
						return s % 2 == 0;
					}
				}, new Mapping<Integer, Integer>() {
					public Integer function(Integer i) {
						return i / divisor;
					}
				}, new Reduction<Integer>() {
					public Integer function(Integer a, Integer b) {
						return a + b;
					}
				})).apply(list);

		System.out.println(result);

		// chaining type error
		System.out.println("There should be an error:");
		Integer result3 = new ReductionChain<String, Integer>(Arrays.asList(
				new Mapping<String, Integer>() {
					public Integer function(String s) {
						return Integer.valueOf(s);
					}
				}, new Filter<Integer>() {
					public Boolean function(Integer s) {
						return s % 2 == 0;
					}
				}, new Filter<String>() {
					public Boolean function(String s) {
						return s != "";
					}
				}, new Mapping<Integer, Integer>() {
					public Integer function(Integer i) {
						return i / divisor;
					}
				}, new Reduction<Integer>() {
					public Integer function(Integer a, Integer b) {
						return a + b;
					}
				})).apply(list);

		// FIXME: is it possible to get the following to work?
		// // chaining map (not type safe):
		// Integer result2= new MapChain<String, Integer>(Arrays.asList(
		// new Mapping<String, Integer>() {
		// public Integer function(String s) {
		// return Integer.valueOf(s);
		// }
		// }, new Filter<Integer>() {
		// public Boolean function(Integer s) {
		// return s % 2 == 0;
		// }
		// }, new Mapping<Integer, Integer>() {
		// public Integer function(Integer i) {
		// return i / divisor;
		// }
		// })).apply(list);
		//
		// System.out.println(result2);
	}
}
