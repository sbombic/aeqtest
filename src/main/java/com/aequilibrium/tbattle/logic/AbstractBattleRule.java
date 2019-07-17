package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.List;

public abstract class AbstractBattleRule implements BattleRule {

    private final BattleRule nextRule;

    public AbstractBattleRule() {
        nextRule = null;
    }

    public AbstractBattleRule(BattleRule nextStep) {
        this.nextRule = nextStep;
    }

    protected void nextRule(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
        if(nextRule != null) {
            nextRule.apply(pair, survivors);
        }
    }

    protected Transformer autobot(Pair<Transformer, Transformer> pair) {
        return pair.getFirst();
    }

    protected Transformer decepticon(Pair<Transformer, Transformer> pair) {
        return pair.getSecond();
    }
}
