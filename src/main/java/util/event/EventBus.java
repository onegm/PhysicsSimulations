package util.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventBus {

    private Map<Class, List<EventListener>> listeners = new HashMap<>();

    public void emit(Event event){
        Class eventClass = event.getClass();
        List<EventListener> eventListeners = listeners.get(eventClass);
        eventListeners.forEach(listener -> listener.handle(event));
    }
    public <T extends Event> void addListener(Class<T> eventClass, EventListener<T> listener){
        if(!listeners.containsKey(eventClass)){
            listeners.put(eventClass, new LinkedList<>());
        }
        listeners.get(eventClass).add(listener);
    }
}
