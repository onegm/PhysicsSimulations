package util;

import java.util.LinkedList;
import java.util.List;

public class Property<T>{
    private final List<SimpleChangeListener<T>> listeners;
    private T value;

    public Property(T value) {
        this.value = value;
        this.listeners = new LinkedList<>();
    }
    public Property() {
        this(null);
    }

    public void addListener(SimpleChangeListener<T> listener){
        this.listeners.add(listener);
    }

    public void setValue(T newValue) {
        if(newValue.equals(this.value)){
            return;
        }
        this.value = newValue;
        notifyListeners();
    }

    private void notifyListeners() {
        listeners.forEach(listener -> listener.valueChanged(this.value));
    }

    public T getValue() {
        return value;
    }

    public boolean notNull(){
        return value != null;
    }
}
