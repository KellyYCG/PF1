package br.ol.smb.entity.item;

import br.ol.smb.entity.Actor;
import br.ol.smb.entity.Mauro;
import br.ol.smb.infra.Entity;
import br.ol.smb.infra.Tile;


public class Item extends Actor {
    
    protected Mauro mauro;
    protected Tile tile;
    protected int endTileId;
    protected boolean getByMauro;
    protected String extraData;
    
    public Item() {
    }

    public int getEndTileId() {
        return endTileId;
    }

    @Override
    public void start() {
        super.start();
        mauro = game.getEntity(Mauro.class);
        setVisible(true);
    }
    
    public void set(Tile tile, int endTileId, String extraData) {
        tile.convertToWorldPosition(position);
        this.tile = tile;
        this.endTileId = endTileId;
        this.extraData = extraData;
        setGame(tile.getGame());
        setzOrder(0);
        animationTimeScale = 20;
        flipSpriteAccordingToDirection = false;
    }
    
    public void onImpact(boolean lastObject) {
        System.out.println("achou object = " + getClass().getName());
        if (lastObject && endTileId != tile.getId()) {
            map.replaceTile(tile, endTileId);
        }
        tile.nextItem();
        game.addEntity(this);
    }
    
    @Override
    public void onImpactFromGround(ImpactStrength strength) {
    }
    
    public void onCollectedByPlayer() {
    }

    @Override
    public void onHorizontalEntityCollision(Entity otherEntity) {
        if (!getByMauro && otherEntity instanceof Mauro) {
            Mauro player = (Mauro) otherEntity;
            if (!player.isDead()) {
                getByMauro = true;
                onGetByMauro();
            }
        }
    }

    @Override
    public void onVerticalEntityCollision(Entity otherEntity) {
        if (!getByMauro && otherEntity instanceof Mauro) {
            Mauro player = (Mauro) otherEntity;
            if (!player.isDead()) {
                getByMauro = true;
                onGetByMauro();
            }
        }
    }

    protected void onGetByMauro() {
    }    

}
