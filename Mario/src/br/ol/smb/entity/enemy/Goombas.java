package br.ol.smb.entity.enemy;

public class Goombas extends Enemy {
    
    public Goombas() {
    }

    @Override
    public void start() {
        super.start();
        setAnimation(2225, 2226);
        killPoint = 100;
    }

    @Override
    protected void killedByMauroStamp() {
        tileId = 2227; // dead 
        animation = null;
        super.killedByMauroStamp();
    }

    @Override
    public void onImpactFromGround(ImpactStrength strength) {
        killedFromGround();
    }
    
    @Override
    protected void killedFromGround() {
        super.killedFromGround();
    }
}
