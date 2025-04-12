package service;

import core.game_loop.GameContext;
import input.Key;
import model.weapon.AbstractWeapon;

public class WeaponControlService {
    private int shotCooldown = 0;
    private static final int MAX_COOLDOWN = 15;

    public void handleWeaponFire(GameContext context) {
        Key key = context.getKey();

        if (key.isKey_j() || key.isKey_k() || key.isKey_l()) {
            if (shotCooldown == 0) {
                AbstractWeapon weapon = (AbstractWeapon) context.getWeaponFactory().create(
                        context.getPlayer().getCenterX(),
                        context.getPlayer().getCenterY(),
                        context.getPlayer().getAngle()
                );
                context.getWeapons().add(weapon);
            }

            shotCooldown++;
            if (shotCooldown >= MAX_COOLDOWN) {
                shotCooldown = 0;
            }
        } else {
            shotCooldown = 0;
        }
    }
}