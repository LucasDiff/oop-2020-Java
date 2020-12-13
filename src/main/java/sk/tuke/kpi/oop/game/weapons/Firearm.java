package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int ammo;
    private int maxAmmo;
    private boolean active;

    public Firearm(int ammo) {
        this(ammo, ammo);
        active = true;
    }

    public Firearm(int ammo, int maxAmmo) {
        this.ammo = ammo;
        this.maxAmmo = maxAmmo;
        active = true;
    }

    public void reload(int newAmmo) {
        ammo = (ammo + newAmmo <= maxAmmo) ? ammo + newAmmo : maxAmmo;
    }

    protected abstract Fireable createBullet();

    public Fireable fire() {
        if (ammo <= 0) {
            return null;
        }
        if (!active) {
            return null;
        }

        ammo = ammo - 1;

        return createBullet();
    }

    public int getAmmo() {
        return ammo;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
