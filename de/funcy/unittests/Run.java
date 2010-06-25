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
		Collection<Integer> list2 = new Map<String, Integer>() {
			protected Integer map(String s) {
				return Integer.valueOf(s);
			}
		}.apply(list);
		System.out.println("List of Integers:  " + list2);

		// filter: even numbers
		Collection<Integer> list3 = new Filter<Integer>() {
			protected Boolean filter(Integer s) {
				return s % 2 == 0;
			}
		}.apply(list2);
		System.out.println("Filtered even:     " + list3);

		// map with closure: divide every value by 2
		final int divisor = 2;
		Collection<Integer> list4 = new Map<Integer, Integer>() {
			protected Integer map(Integer i) {
				return i / divisor;
			}
		}.apply(list3);
		System.out.println("All divded by two: " + list4);

		// reduce: compute the sum
		Integer sum = new Reduce<Integer>() {
			public Integer reduce(Integer a, Integer b) {
				return a + b;
			}
		}.apply(list4);
		System.out.println("Sum: " + sum);

		// filter and map in one, filter even and divide by 2
		Collection<Integer> mapFilter = new FilterMap<Integer, Integer>() {
			protected Boolean filter(Integer s) {
				return s % 2 == 0;
			}

			protected Integer map(Integer i) {
				return i / divisor;
			}
		}.apply(list2);
		System.out.println("MapFilter: " + mapFilter);

		// filter, map and reduce at once, filter even, divide by 2, sum up
		Integer mapFilterReduce = new FilterMapReduce<Integer, Integer>() {
			protected Boolean filter(Integer s) {
				return s % 2 == 0;
			}

			protected Integer map(Integer i) {
				return i / divisor;
			}

			protected Integer reduce(Integer a, Integer b) {
				return a + b;
			}
		}.apply(list2);
		System.out.println("MapFilterReduce: " + mapFilterReduce);

		// Chained all above into one ReductionChain (not type safe)
		Integer result = new ReductionChain<String, Integer>(Arrays.asList(
				new Map<String, Integer>() {
					protected Integer map(String s) {
						return Integer.valueOf(s);
					}
				}, new Filter<Integer>() {
					protected Boolean filter(Integer s) {
						return s % 2 == 0;
					}
				}, new Map<Integer, Integer>() {
					protected Integer map(Integer i) {
						return i / divisor;
					}
				}, new Reduce<Integer>() {
					public Integer reduce(Integer a, Integer b) {
						return a + b;
					}
				})).apply(list);

		System.out.println("ReductionChain: "
				+ result);

		// Chained all above except Sum into one MapChain (not type safe)
		Collection<Integer> result2 = new MapChain<String, Integer>(Arrays
				.asList(new Map<String, Integer>() {
					protected Integer map(String s) {
						return Integer.valueOf(s);
					}
				}, new Filter<Integer>() {
					protected Boolean filter(Integer s) {
						return s % 2 == 0;
					}
				}, new Map<Integer, Integer>() {
					protected Integer map(Integer i) {
						return i / divisor;
					}
				})).apply(list);

		System.out.println("MapChain: "
				+ result2);

		// chaining run-time type error
		System.out.println("There should be an error:");
		new Chain<Collection<String>, Integer>(Arrays.asList(
				new Map<String, Integer>() {
					protected Integer map(String s) {
						return Integer.valueOf(s);
					}
				}, new Filter<Integer>() {
					protected Boolean filter(Integer s) {
						return s % 2 == 0;
					}
				}, new Filter<String>() {
					protected Boolean filter(String s) {
						return s != "";
					}
				}, new Map<Integer, Integer>() {
					protected Integer map(Integer i) {
						return i / divisor;
					}
				}, new Reduce<Integer>() {
					public Integer reduce(Integer a, Integer b) {
						return a + b;
					}
				})).apply(list);

	}
}
