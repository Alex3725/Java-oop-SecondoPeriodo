package it.cardgames.model;

import java.util.Collections;
import java.util.Stack;

public abstract class Mazzo<T extends Carta> {
    protected final Stack<T> carte = new Stack<>();

    public void mescola() { Collections.shuffle(carte); }

    public T pesca() { return carte.pop(); }

    public boolean isVuoto() { return carte.isEmpty(); }
}
