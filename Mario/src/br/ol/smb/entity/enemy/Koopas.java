package br.ol.smb.entity.enemy;

import br.ol.smb.infra.Entity;
import br.ol.smb.infra.Map;


public class Koopas extends Enemy {

    private static enum KoopasState { NORMAL, WAITING, BOWLING }
    private KoopasState koopasState = KoopasState.NORMAL;
    private static final double SPEED = 2.5;
    private static final int WAITING_POINT = 100;
    private static final int BOWLING_POINT = 400;
    
    public Koopas() {
    }

    @Override
    public void start() {
        super.start();
        tileId = 2231;
        setAnimation(2231, 2232);
        setColliderSize(16, 16);
        spriteHeight = 24;
        flipSpriteAccordingToDirection = true;
        killPoint = 200;
    }

    @Override
    protected void onStampedByMauro() {
        switch (koopasState) {
            case NORMAL:
            case BOWLING:
                tileId = 2235;
                animation = null;
                dangerous = false;
                canKillOtherEnemies = false;
                direction = 0;
                mauro.getVelocity().setY(-7);
                while (getCollider().intersects(mauro.getCollider())) {
                    mauro.getPosition().translate(0, -1);
                }
                game.spawnPoint(WAITING_POINT, position.getX(), position.getY() - Map.TILE_SIZE);
                koopasState = KoopasState.WAITING;
                break;
            case WAITING:
                direction = mauro.getPosition().getX() > getPosition().getX() ? -SPEED : SPEED;
                dangerous = true;
                canKillOtherEnemies = true;
                mauro.getVelocity().setY(-7);
                while (getCollider().intersects(mauro.getCollider())) {
                    mauro.getPosition().translate(0, -1);
                }
                mauro.getPosition().translate(2, -32);
                koopasState = KoopasState.BOWLING;
                game.spawnPoint(BOWLING_POINT, position.getX(), position.getY() - Map.TILE_SIZE);
                break;
        }
    }

    @Override
    public void onHorizontalEntityCollision(Entity otherEntity) {
        super.onHorizontalEntityCollision(otherEntity); 
        switch (koopasState) {
            case NORMAL:
                break;
            case WAITING:
                direction = mauro.getPosition().getX() > getPosition().getX() ? -SPEED : SPEED;
                while (mauro.getCollider().intersects(getCollider())) {
                    mauro.getPosition().translate(-direction, 0);
                }
                dangerous = true;
                canKillOtherEnemies = true;
                game.spawnPoint(BOWLING_POINT, position.getX(), position.getY() - Map.TILE_SIZE);
                koopasState = KoopasState.BOWLING;
                break;
            case BOWLING:
                break;
        }
    }

}
