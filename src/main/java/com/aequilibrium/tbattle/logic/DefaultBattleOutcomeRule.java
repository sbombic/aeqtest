package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Affiliation;
import com.aequilibrium.tbattle.model.BattleOutcome;
import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultBattleOutcomeRule implements BattleOutcomeRule {

    @Override
    public BattleOutcome calculateOutcome(int battles, List<Transformer> autobots, List<Transformer> decepticons,
                                          List<Transformer> survivorsAutobots, List<Transformer> survivorsDecepticons) {
        BattleOutcome battleOutcome = new BattleOutcome();
        battleOutcome.setNumberOfBattles(battles);

        // Calculate battle score
        int score = survivorsAutobots.size() - survivorsDecepticons.size();

        // Copy remaining bots
        if(autobots.size() > battles) {
            survivorsAutobots.addAll(autobots.stream().skip(battles).collect(Collectors.toList()));
        }
        if(decepticons.size() > battles) {
            survivorsDecepticons.addAll(decepticons.stream().skip(battles).collect(Collectors.toList()));
        }

        // Choose winners/losers
        List<Transformer> winningTeam;
        List<Transformer> loserTeam;
        if(score == 0) {
            // We have a tie
            battleOutcome.setWinningTeamType("Tie!");
            return battleOutcome;
        }
        else if(score > 0) {
            // autobots won
            battleOutcome.setWinningTeamType(Affiliation.A.getName());
            winningTeam = survivorsAutobots;
            loserTeam = survivorsDecepticons;
        }
        else {
            // decepticons won
            battleOutcome.setWinningTeamType(Affiliation.D.getName());
            winningTeam = survivorsDecepticons;
            loserTeam = survivorsAutobots;
        }

        battleOutcome.setWinningTeam(winningTeam.stream().map(Transformer::getName).collect(Collectors.toList()));
        battleOutcome.setSurvivors(loserTeam.stream().map(Transformer::getName).collect(Collectors.toList()));

        return battleOutcome;
    }
}
