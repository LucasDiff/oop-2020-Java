package sk.tuke.kpi.oop.game;

import java.util.HashMap;
import java.util.Map;

public class Ripley_state {

    private static Ripley_state instance;

    private Map<String, String> files;

    private Ripley_state() {
        this.files = new HashMap<>();
    }

    public static Ripley_state getInstance() {
        if (instance == null)
            instance = new Ripley_state();
        return instance;
    }

    public void saveFile(String name, String content) {
        this.files.put(name, content);
    }

    public String loadFile(String name) {
        return this.files.get(name);
    }
}
