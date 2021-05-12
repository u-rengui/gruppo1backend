package com.generation.gruppo1.noPlayGestionale.model;

import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;

public class Ruolo implements IMappablePro{

	private int id;
	private String mansione;
	
	public Ruolo(int id, String mansione) {
		super();
		this.id = id;
		this.mansione = mansione;
	}

	public Ruolo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMansione() {
		return mansione;
	}

	public void setMansione(String mansione) {
		this.mansione = mansione;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", mansione:" + mansione + "}";
	}

}