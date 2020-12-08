package sk.tuke.kpi.oop.game;

import java.util.HashMap;
import java.util.Map;

public class RipleyState {
    private static RipleyState instance;
    private Map<String, String> files;

    private RipleyState() {
        this.files = new HashMap<>();
    }

    public static RipleyState getInstance() {
        if (instance == null)
            instance = new RipleyState();
        return instance;
    }

    public void saveFile(String name, String content) {
        this.files.put(name, content);
    }

    public String loadFile(String name) {
        return this.files.get(name);
    }
}
