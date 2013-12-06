package com.example.jsfdemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jsfdemo.domain.Alcohol;
import com.example.jsfdemo.service.AlcoholManager;

@SessionScoped
@Named("alcoholBean")
public class AlcoholFormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Alcohol alkohol = new Alcohol();
	private ListDataModel<Alcohol> alcohols = new ListDataModel<Alcohol>();
	private ListDataModel<Alcohol> searchedAlcohols = new ListDataModel<Alcohol>();

	@Inject
	private AlcoholManager alkoholManager;

	public Alcohol getAlcohol() {
		return alkohol;
	}

	public void setAlcohol(Alcohol alko) {
		this.alkohol = alko;
	}

	// Actions
	public String addAlcohol() {
		alkoholManager.addAlcohol(alkohol);
		return "showAlcohols";
	}

	public ListDataModel<Alcohol> getSearchAlcohols() {
		searchedAlcohols.setWrappedData(alkoholManager.searchedAlcohols(alkohol));
		return searchedAlcohols;
	}

	public ListDataModel<Alcohol> getAllAlcohols() {
		alcohols.setWrappedData(alkoholManager.getAllAlcohols());
		return alcohols;
	}

	public String deleteAlcohol() {
		Alcohol alcoholToDelete = alcohols.getRowData();
		alkoholManager.deleteAlcoholbyName(alcoholToDelete);
		return null;
	}

	// Validators
	public void uniqueCode(FacesContext context, UIComponent component,
			Object value) {

		String code = (String) value;

		for (Alcohol alko : alkoholManager.getAllAlcohols()) {
			if (alko.getCode().equalsIgnoreCase(code)) {
				FacesMessage message = new FacesMessage(
						"Alcohol with this code already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	public void validateCodeYop(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput code = (UIInput) form.findComponent("code");
		UIInput yop = (UIInput) form.findComponent("calendar");

		if (code.getValue() != null && yop.getValue() != null
				&& code.getValue().toString().length() >= 2) {
			String twoDigitsOfcode = code.getValue().toString().substring(0, 2);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(((Date) yop.getValue()));

			String lastDigitsOfYob = ((Integer) calendar.get(Calendar.YEAR))
					.toString().substring(2);

			if (!twoDigitsOfcode.equals(lastDigitsOfYob)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"Code doesn't match date of production"));
				context.renderResponse();
			}
		}
	}
}
