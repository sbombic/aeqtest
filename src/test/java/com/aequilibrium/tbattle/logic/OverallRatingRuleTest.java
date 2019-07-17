package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.TestUtils;
import com.aequilibrium.tbattle.model.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class OverallRatingRuleTest {

    private BattleRule rule = new OverallRatingRule();

    @Mock
    private List<Transformer> survivorsAutobots;

    @Mock
    private List<Transformer> survivorsDecepticons;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void applyAutobotWins() {
        rule.apply(TestUtils.getOrdinaryPairOverallAutobotWins(), Pair.of(survivorsAutobots, survivorsDecepticons));
        verify(survivorsAutobots, times(1)).add(TestUtils.HUBCAP);
        verify(survivorsDecepticons, never()).add(any());
    }

    @Test
    public void applyDecepticonWins() {
        rule.apply(TestUtils.getOrdinaryPairOverallDecepticonWins(), Pair.of(survivorsAutobots,survivorsDecepticons));
        verify(survivorsAutobots, never()).add(any());
        verify(survivorsDecepticons, times(1)).add(TestUtils.SOUNDWAVE);
    }

    @Test
    public void applyBothDestroyed() {
        rule.apply(TestUtils.getOrdinaryPairOverallTie(), Pair.of(survivorsAutobots, survivorsDecepticons));
        verify(survivorsAutobots, never()).add(any());
        verify(survivorsDecepticons, never()).add(any());
    }
}