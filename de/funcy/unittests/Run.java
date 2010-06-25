package de.funcy.unittests;

import de.funcy.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Run {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
		System.out.println("List of Strings:   " + list);

		// map: String to Integer
		Collection<Integer> list2 = new Mapping<String, Integer>() {
			public Integer function(String s) {
				return Integer.valueOf(s);
			}
		}.apply(list);
		System.out.println("List of Integers:  " + list2);

		// filter: even numbers
		Collection<Integer> list3 = new Filter<Integer>() {
			public Boolean function(Integer s) {
				return s % 2 == 0;
			}
		}.apply(list2);
		System.out.println("Filtered even:     " + list3);

		// map with closure: divide every value by 2
		final int divisor = 2;
		Collection<Integer> list4 = new Mapping<Integer, Integer>() {
			public Integer function(Integer i) {
				return i / divisor;
			}
		}.apply(list3);
		System.out.println("All divded by two: " + list4);

		// reduce: compute the sum
		Integer sum = new Reduction<Integer>() {
			public Integer function(Integer a, Integer b) {
				return a + b;
			}
		}.apply(list4);
		System.out.println("Sum: " + sum);

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

		// FIXME: is it possible to get the following to work?
		// chaining map (not type safe):
		MapChain2<String, Integer> c = new MapChain2<String, Integer>(Arrays
				.asList(new Mapping<String, Integer>() {
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
				}));

		Collection<Integer> result2 = c.apply(list);

		System.out.println(result2);

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

	}
}
