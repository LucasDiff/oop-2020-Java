package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int ammo;
    private int maxAmmo;

    public void reload(int newAmmo)
    {
        ammo = (ammo + newAmmo <= maxAmmo) ? ammo + newAmmo : maxAmmo;

    }

    public Firearm(int ammo)
    {
        this(ammo, ammo);
    }

    public Firearm(int ammo, int maxAmmo)
    {
        this.ammo = ammo;
        this.maxAmmo = maxAmmo;
    }

    protected abstract Fireable createBullet();

    public Fireable fire()
    {
        if (ammo <= 0) {
            return null;
        }

        ammo = ammo - 1;

        return createBullet();
    }

    public int getAmmo()
    {
        return ammo;
    }
}
