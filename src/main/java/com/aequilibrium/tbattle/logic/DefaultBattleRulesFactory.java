package com.aequilibrium.tbattle.logic;

import org.springframework.stereotype.Component;

@Component
public class DefaultBattleRulesFactory implements BattleRulesFactory {

    private final BattleRule battleRules;

    public DefaultBattleRulesFactory() {
        // Chain the rules in the correct order: primals -> face-off -> skill -> overall
        BattleRule overallRatingRule = new OverallRatingRule();
        BattleRule skillCheckRule = new SkillCheckRule(overallRatingRule);
        BattleRule faceOffRule = new FaceOffRule(skillCheckRule);
        battleRules = new PrimalsRule(faceOffRule);
    }

    @Override
    public BattleRule getBattleRules() {
        return battleRules;
    }
}
