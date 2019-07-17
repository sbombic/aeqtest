package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.BattleOutcome;
import com.aequilibrium.tbattle.model.Transformer;

import java.util.List;

public interface BattleOutcomeRule {
    BattleOutcome calculateOutcome(int battles, List<Transformer> autobots, List<Transformer> decepticons, List<Transformer> survivorsAutobots, List<Transformer> survivorsDecepticons);
}
