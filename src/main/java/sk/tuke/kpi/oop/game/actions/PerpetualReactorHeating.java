package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int incr;

    public PerpetualReactorHeating(int incr)
    {
        this.setIncr(incr);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        this.getActor().increaseTemperature(getIncr());
    }

    public int getIncr()
    {
        return incr;
    }

    public void setIncr(int incr)
    {
        this.incr = incr;
    }
}
