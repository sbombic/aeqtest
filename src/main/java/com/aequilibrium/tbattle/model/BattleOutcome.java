package com.aequilibrium.tbattle.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BattleOutcome {

    private int numberOfBattles;
    private String winningTeamType;
    private List<String> winningTeam;
    private List<String> survivors;

    public static BattleOutcome totalAnnihilation() {
        BattleOutcome result = new BattleOutcome();
        result.setNumberOfBattles(0);
        result.setWinningTeamType("Total annihilation.");
        return result;
    }

    public static BattleOutcome noBattle() {
        BattleOutcome result = new BattleOutcome();
        result.setNumberOfBattles(0);
        result.setWinningTeamType("None");
        return result;
    }
}
