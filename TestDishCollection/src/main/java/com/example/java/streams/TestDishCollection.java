package com.example.java.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.java.streams.Dish.Type;

// All method names describe the requirement. You need to use serial and parallel streams to solve.

public class TestDishCollection {

	public static void getDishesSortedForCaloriesLessThan400(List<Dish> menu) {
		menu.stream()
		.filter(calories -> calories.getCalories() < 400)
		.sorted(Comparator.comparingInt(Dish::getCalories))
		.forEach(System.out::println);
	}

	public static void getDishWithMaximumCalories(List<Dish> menu) {

		Optional<Dish> dishWithMaxCalories = menu.stream().max(Comparator.comparingInt(Dish::getCalories));

		dishWithMaxCalories.ifPresent(System.out::println);

	}

	public static void Skip2AndLimit3Dishes(List<Dish> menu) {

		menu.stream().skip(2).limit(3).forEach(System.out::println);
	}

	public static void getTwoMeatDishes(List<Dish> menu) {

		menu.stream().filter(type -> type.getType() == Type.MEAT).limit(2).forEach(System.out::println);
	}

	public static void getVegetrianDishes(List<Dish> menu) {
		menu.stream().filter(type -> type.isVegetarian() == true).forEach(System.out::println);
	}

	public static void printAllDishTypes() {

		List<Dish> dishes = Dish.getDishes();

		for (Dish dish : dishes) {
			System.out.println(dish);
		}
	}

	/*
	 * Provide two solutions for this requirements
	 */
	public static void printSumofCalories(List<Dish> menu) {
	int sum = menu.stream()
		.mapToInt(Dish::getCalories)
		.sum();

		System.out.println("Sum of calories: " + sum);
		
		//solution 2
		int sum2 = menu.stream()
				.mapToInt(Dish::getCalories)
				.reduce(0, (a,b)->a+b);

				System.out.println("Sum of calories: " + sum2);
	}

	public static void anyDishwithCaloriesLess400(List<Dish> menu) {

				menu.stream()
		.filter(dish->dish.getCalories() < 400).distinct()
		.findAny()
		.ifPresent(dish->System.out.println("AnyDish :" + dish.getName()));
	
				System.out.println("================================");
		//solution 2
				menu.parallelStream()
				.filter(dishs->dishs.getCalories() < 400)
				.findFirst()
				.ifPresent(dishs->System.out.println("AnyDish: " + dishs.getName()));
			
	   
	}

	public static void printTotalCaloriesofEveryDish(List<Dish> menu) {
		
		
		 menu.stream()
	        .collect(Collectors.groupingBy(Dish::getName, Collectors.summingInt(Dish::getCalories)))
	        .forEach((dishName, totalCalories) ->
	            System.out.println("Total calories of " + dishName + ": " + totalCalories)
	        );
		 System.out.println("=============================================");
		 
		 menu.parallelStream()
	        .collect(Collectors.groupingBy(Dish::getName, Collectors.summingInt(Dish::getCalories)))
	        .forEach((dishName, totalCalories) ->
	            System.out.println("Total calories of " + dishName + ": " + totalCalories)
	        );

	}

	public static void main(String[] args) {
		List<Dish> menu = Dish.getDishes();
		System.out.println("Menues"+menu);
		
		
		System.out.println("====GetDishesSortedForCaloriesLessThan400 :");
		 getDishesSortedForCaloriesLessThan400(menu);

		 System.out.println("====PrintTotalCaloriesofEveryDish :");
		 printTotalCaloriesofEveryDish(menu);
		
		System.out.println("====PrintSumofCalories"); 
		 printSumofCalories(menu);
		
		 System.out.println("====GetVegetrianDishes");
		  getVegetrianDishes(menu);
		
		  System.out.println("===AnyDishwithCaloriesLess400");
		anyDishwithCaloriesLess400(menu);
		
		System.out.println("===GetVegetrianDishes");
		 getVegetrianDishes(menu);
		
		 System.out.println("====GetDishWithMaximumCalories");
		 getDishWithMaximumCalories(menu);
		
		 System.out.println("====GetDishesSortedForCaloriesLessThan400");
		 getDishesSortedForCaloriesLessThan400(menu);
		System.out.println("====GetDishWithMaximumCalories");
		 getDishWithMaximumCalories(menu);
		System.out.println("====Skip2AndLimit3Dishes");
		 Skip2AndLimit3Dishes(menu);
		System.out.println("====GetTwoMeatDishes");
	   getTwoMeatDishes(menu);
	}

}
