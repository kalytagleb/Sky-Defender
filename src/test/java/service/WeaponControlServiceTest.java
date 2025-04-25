package service;

import core.game_loop.GameContext;
import factory.weapon.RocketFactory;
import input.Key;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.WeaponControlService;
import service.game_state.GameStateManager;
import service.waves.WaveManager;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeaponControlServiceTest {

    private WeaponControlService service;
    private GameContext context;
    private Key key;

    @BeforeEach
    void setUp() {
        service = new WeaponControlService();

        key = new Key();
        Player player = new Player();
        player.changeLocation(100, 100);

        context = new GameContext(
                player,
                new CopyOnWriteArrayList<>(),
                new CopyOnWriteArrayList<>(),
                key,
                new WaveManager(new CopyOnWriteArrayList<>(), 800, 600),
                new GameStateManager(),
                800, 600
        );

        context.setWeaponFactory(new RocketFactory());
    }

    @Test
    void fireWeaponWhenKeyPressed() {
        key.setKey_j(true);

        service.handleWeaponFire(context);
        assertEquals(1, context.getWeapons().size(), "Weapon should be added when key is pressed");
    }

    @Test
    void doesNotFireWeaponBeforeCooldown() {
        key.setKey_j(true);

        service.handleWeaponFire(context);
        for (int i=0; i<10; i++) {
            service.handleWeaponFire(context);
        }

        assertEquals(1, context.getWeapons().size(), "Only one weapon should be fired before cooldown");
    }

    @Test
    void cooldownResetsWhenNoKeyPressed() {
        key.setKey_j(true);
        service.handleWeaponFire(context);

        key.setKey_j(false);
        service.handleWeaponFire(context);

        key.setKey_j(true);
        service.handleWeaponFire(context);

        assertEquals(2, context.getWeapons().size(), "Cooldown should reset when key is released");
    }
}