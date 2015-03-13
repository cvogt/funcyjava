The map, filter and reduce functions allow working with lists very conveniently. They are known from functional programming. This library brings them to Java.

Sample code:

```
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
```

output:
```
List of Strings:   [1, 2, 3, 4, 5, 6, 7]
List of Integers:  [1, 2, 3, 4, 5, 6, 7]
Filtered even:     [2, 4, 6]
All divded by two: [1, 2, 3]
Sum: 6
```