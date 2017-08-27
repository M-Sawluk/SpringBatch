package com.michal.domain;

public enum HeatOfCombustionType {
	nominalne,
	sredniomiesieczne,
	operatywne,
	zatwierdzone;


	public String getMsg() {
		return HeatOfCombustionType.class.getSimpleName() + "." + name();
	}

}