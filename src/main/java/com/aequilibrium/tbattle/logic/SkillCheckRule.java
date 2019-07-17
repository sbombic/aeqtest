package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.List;

public class SkillCheckRule extends AbstractBattleRule {

    public SkillCheckRule(BattleRule nextRule) {
        super(nextRule);
    }

    @Override
    public void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
        Transformer autobot = autobot(pair);
        Transformer decepticon = decepticon(pair);
        int skillDifference = autobot.getSkill() - decepticon.getSkill();
        if(skillDifference >= 3) {
            survivors.getFirst().add(autobot);
            return;
        }
        if (skillDifference <= -3){
            survivors.getSecond().add(decepticon);
            return;
        }
        nextRule(pair, survivors);
    }
}
