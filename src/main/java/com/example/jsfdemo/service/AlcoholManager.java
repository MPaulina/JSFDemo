package com.example.jsfdemo.service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import com.example.jsfdemo.domain.Alcohol;

@ApplicationScoped
public class AlcoholManager {
	private List<Alcohol> db = new ArrayList<Alcohol>();

	public void addAlcohol(Alcohol alko) {
		Alcohol newAlcohol = new Alcohol();

		newAlcohol.setType(alko.getType());
		newAlcohol.setName(alko.getName());
		newAlcohol.setCode(alko.getCode());
		newAlcohol.setQuantun(alko.getQuantun());
		newAlcohol.setCapacity(alko.getCapacity());
		newAlcohol.setYop(alko.getYop());
		newAlcohol.setProcentFree(alko.isProcentFree());
		db.add(newAlcohol);
	}

	public void deleteAlcoholbyName(Alcohol Alcohol) {
		Alcohol AlcoholToRemove = null;
		for (Alcohol c : db) {
			if (Alcohol.getName().equals(c.getName())) {
				AlcoholToRemove = c;
				break;
			}
		}
		if (AlcoholToRemove != null)
			db.remove(AlcoholToRemove);
	}

	public List<Alcohol> getAllAlcohols() {
		return db;
	}

	public List<Alcohol> searchedAlcohols(Alcohol alko) {
		List<Alcohol> searchedAlcohol = new ArrayList<Alcohol>();
		for (Alcohol c : db) {
			if (alko.getType().equalsIgnoreCase(alko.getType())) {
				searchedAlcohol.add(c);
			}
		}
		return searchedAlcohol;
	}
}
