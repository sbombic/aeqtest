package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.List;

public class OverallRatingRule extends AbstractBattleRule {

    public OverallRatingRule() {
        super();
    }

    @Override
    public void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
        Transformer autobot = autobot(pair);
        Transformer decepticon = decepticon(pair);
        if(autobot.getOverallRating() > decepticon.getOverallRating()) {
            survivors.getFirst().add(autobot);
        }
        else if(autobot.getOverallRating() < decepticon.getOverallRating()) {
            survivors.getSecond().add(decepticon);
        }
    }
}
