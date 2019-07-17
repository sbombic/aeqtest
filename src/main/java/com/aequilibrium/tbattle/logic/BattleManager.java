package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.BattleOutcome;

import java.util.List;


public interface BattleManager {
    BattleOutcome battle(List<Long> battleSetup);
}
