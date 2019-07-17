package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Face-off:
 * If any fighter is down 4 or more points of courage and 3 or more points of
 * strength compared to their opponent, the opponent automatically wins the
 * face-off regardless of overall rating (opponent has ran away)
 */
public class FaceOffRule extends AbstractBattleRule {

    public FaceOffRule(BattleRule nextStep) {
        super(nextStep);
    }

    @Override
    public void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
        Transformer autobot = autobot(pair);
        Transformer decepticon = decepticon(pair);
        int courageDifference = autobot.getCourage() - decepticon.getCourage();
        int strengthDifference = autobot.getStrength() - decepticon.getStrength();
        if(courageDifference >= 4 && strengthDifference >= 3) {
            survivors.getFirst().add(autobot);
            return;
        }
        else if(courageDifference <= -4 && strengthDifference <= -3) {
            survivors.getSecond().add(decepticon);
            return;
        }
        nextRule(pair, survivors);
    }
}
