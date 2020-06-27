package it.polito.tdp.food.model;

public class Adiacenza implements Comparable<Adiacenza>{

	private Food food;
	private Double calorie;
	
	public Adiacenza(Food food, Double calorie) {
		super();
		this.food = food;
		this.calorie = calorie;
	}
	
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Double getCalorie() {
		return calorie;
	}
	public void setCalorie(Double calorie) {
		this.calorie = calorie;
	}
	
	@Override
	public int compareTo(Adiacenza o) {
		
		
		
		
		return o.getCalorie().compareTo(this.getCalorie());
	}
	
	
}
