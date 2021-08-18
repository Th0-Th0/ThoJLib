package com.tho.thojlib.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

	protected final List<T> observers;

	public Observable() {
		this.observers = new ArrayList<>();
	}

	public int getNbOfObservers() {
		return this.observers.size();
	}

	public final void addObserver(final T obs) {
		if (!this.observers.contains(obs)) {
			this.observers.add(obs);
		}
	}

	public void removeObserver(final T obs) {
		if (this.observers.contains(obs)) {
			this.observers.remove(obs);
		}
	}

	public void removeAllObservers() {
		this.observers.clear();
	}
}