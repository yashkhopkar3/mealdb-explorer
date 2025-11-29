package com.mealdb.explorer.model;

import java.util.List;

public class Meal {
	
	private String id;
	 private String name;
	 private String category;
	 private String area;
	 private String instructions;
	 private List<String> ingredients;
	 private String youtube;
	 
	 public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getInstructions() {
			return instructions;
		}
		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}
		public List<String> getIngredients() {
			return ingredients;
		}
		public void setIngredients(List<String> ingredients) {
			this.ingredients = ingredients;
		}
		public String getYoutube() {
			return youtube;
		}
		public void setYoutube(String youtube) {
			this.youtube = youtube;
		}
}
