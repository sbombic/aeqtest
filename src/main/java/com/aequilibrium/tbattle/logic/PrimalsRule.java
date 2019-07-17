package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import com.google.common.collect.ImmutableList;
import org.springframework.data.util.Pair;

import java.util.List;

public class PrimalsRule extends AbstractBattleRule {

    private static final List<String> SPECIAL_BOTS = ImmutableList.of("Optimus Prime", "Predaking");

    public PrimalsRule(BattleRule nextStep) {
        super(nextStep);
    }

    @Override
    public void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
        Transformer autobot = autobot(pair);
        Transformer decepticon = decepticon(pair);
        boolean autobotSpecial = SPECIAL_BOTS.contains(autobot.getName());
        boolean decepticonSpecial = SPECIAL_BOTS.contains(decepticon.getName());

        if(autobotSpecial && decepticonSpecial) {
            throw new TotalAnnihilationException();
        }

        if(autobotSpecial) {
            survivors.getFirst().add(autobot);
            return;
        }

        if(decepticonSpecial) {
            survivors.getSecond().add(decepticon);
            return;
        }

        nextRule(pair, survivors);
    }
}
