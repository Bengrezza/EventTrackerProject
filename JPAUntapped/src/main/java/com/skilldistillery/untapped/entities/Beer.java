package com.skilldistillery.untapped.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Beer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String ingredients;
	private boolean alcoholic;
	private Double alcohol;
	@Column(name = "contains_alcohol")
	private boolean containsAlcohol;
	private Integer calories;
	private Double volume;
	private boolean active;
	@Column(name = "created_at")
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public Beer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}

	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	public Double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(Double alcohol) {
		this.alcohol = alcohol;
	}

	public boolean isContainsAlcohol() {
		return containsAlcohol;
	}

	public void setContainsAlcohol(boolean containsAlcohol) {
		this.containsAlcohol = containsAlcohol;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Beer [id=" + id + ", name=" + name + ", description=" + description + ", ingredients=" + ingredients
				+ ", alcoholic=" + alcoholic + ", alcohol=" + alcohol + ", containsAlcohol=" + containsAlcohol
				+ ", calories=" + calories + ", volume=" + volume + ", active=" + active + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id != other.id)
			return false;
		return true;
	}
}