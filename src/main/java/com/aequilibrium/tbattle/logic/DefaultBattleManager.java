package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Affiliation;
import com.aequilibrium.tbattle.model.BattleOutcome;
import com.aequilibrium.tbattle.model.Transformer;
import com.aequilibrium.tbattle.repos.TransformersRepo;
import com.google.common.collect.Collections2;
import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DefaultBattleManager implements BattleManager {

    private final TransformersRepo repo;

    private final BattleRulesFactory battleRulesFactory;

    private final BattleOutcomeRule battleOutcomeRule;

    public DefaultBattleManager(@Autowired TransformersRepo repo,
                                @Autowired BattleRulesFactory battleRulesFactory,
                                @Autowired BattleOutcomeRule battleOutcomeRule) {
        this.repo = repo;
        this.battleRulesFactory = battleRulesFactory;
        this.battleOutcomeRule = battleOutcomeRule;
    }

    @Override
    public BattleOutcome battle(List<Long> battleSetup) {
        List<Transformer> bots = repo.findAllById(battleSetup);

        if(bots == null || bots.isEmpty()) {
            return BattleOutcome.noBattle();
        }

        Map<Affiliation, List<Transformer>> botsMap = bots.stream().sorted()
                .collect(Collectors.groupingBy(Transformer::getType));
        List<Transformer> autobots = botsMap.getOrDefault(Affiliation.A, Collections.emptyList());
        List<Transformer> decepticons = botsMap.getOrDefault(Affiliation.D, Collections.emptyList());

        List<Transformer> survivorsAutobots = new ArrayList<>(autobots.size());
        List<Transformer> survivorsDecepticons = new ArrayList<>(decepticons.size());

        Pair<List<Transformer>, List<Transformer>> survivors = Pair.of(survivorsAutobots, survivorsDecepticons);

        BattleRule battleRules = battleRulesFactory.getBattleRules();
        try {
            Streams.zip(autobots.stream(), decepticons.stream(), (autobot, decepticon) -> Pair.of(autobot, decepticon))
                    .forEach(pair -> battleRules.apply(pair, survivors));
        }
        catch (TotalAnnihilationException e) {
            return BattleOutcome.totalAnnihilation();
        }

        return battleOutcomeRule.calculateOutcome(Math.min(autobots.size(), decepticons.size()),
                autobots, decepticons, survivorsAutobots, survivorsDecepticons);
    }

}
