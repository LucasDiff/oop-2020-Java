package sk.tuke.kpi.oop.game.builder;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.gamelib.graphics.Font;
import sk.tuke.kpi.gamelib.graphics.Overlay;

public class Message {
    private String text;
    private boolean removeNewLines;
    private boolean addNewLines;


    private Message(MessageBuilder builder) {
        this.text = builder.text;
        this.removeNewLines = builder.removeNewLines;
        this.addNewLines = builder.addNewLines;
    }

    public String getText() {
        if (removeNewLines) {
            text = text.replace("\n", " ");
        }
        if (addNewLines) {
            text = text.replace(". ", ".\n");
        }
        return text;
    }

    public boolean isRemoveNewLines() {
        return removeNewLines;
    }

    public boolean isAddNewLines() {
        return addNewLines;
    }

    public void display(Scene scene) {
        int dialogWidth = 288;
        int dialogHeight = 128;
        int winWidth = scene.getGame().getWindowSetup().getWidth();
        int winHeight = scene.getGame().getWindowSetup().getHeight();
        new Loop<>(
            new Invoke<>(() -> {
                Overlay overlay = scene.getGame().getOverlay();
                overlay.drawAnimation(new Animation("sprites/popup_rectangle.png", dialogWidth, dialogHeight, 2), (winWidth - dialogWidth) / 2, (winHeight - dialogHeight) / 2 + winHeight / 3);
                Font font = new Font(20, Color.WHITE, Font.Style.BOLD, 2);
                int textWidth = 15 * 15;
                int textHeight = 50;
                overlay.drawText(getText(), (winWidth - textWidth) / 2, (winHeight - textHeight) / 2 + 2 * textHeight / 3 + winHeight / 3, font);
            })
        ).scheduleOn(scene);
    }


    public static class MessageBuilder {
        private String text;
        private boolean removeNewLines;
        private boolean addNewLines;

        public MessageBuilder(String text) {
            this.text = text;
        }

        public MessageBuilder setRemoveNewLines(boolean removeNewLines) {
            this.removeNewLines = removeNewLines;
            return this;
        }

        public MessageBuilder setAddNewLines(boolean addNewLines) {
            this.addNewLines = addNewLines;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
