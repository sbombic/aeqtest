package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.TestUtils;
import com.aequilibrium.tbattle.model.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PrimalsRuleTest {

    @InjectMocks
    private PrimalsRule rule;

    @Mock
    private BattleRule mockedRule;

    @Mock
    private List<Transformer> survivorsAutobots;

    @Mock
    private List<Transformer> survivorsDecepticon;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = TotalAnnihilationException.class)
    public void applyPrimalsAnnihilation() {
        rule.apply(TestUtils.getAnnihilationPair(), Pair.of(Collections.emptyList(), Collections.emptyList()));
    }

    @Test(expected = TotalAnnihilationException.class)
    public void applyClonesAnnihilation() {
        rule.apply(TestUtils.getPrimalClonesPair(), Pair.of(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    public void applyOptimusVsOrdinary() {
        rule.apply(TestUtils.getOptimusAndOrdinary(), Pair.of(survivorsAutobots, survivorsDecepticon));
        verify(survivorsAutobots, times(1)).add(TestUtils.OPTIMUS);
        verify(survivorsDecepticon, never()).add(any());
        verify(mockedRule, never()).apply(any(), any());
    }

    @Test
    public void applyPredakingVsOrdinary() {
        rule.apply(TestUtils.getPredakingAndOrdinary(), Pair.of(survivorsAutobots, survivorsDecepticon));
        verify(survivorsDecepticon, times(1)).add(TestUtils.PREDAKING);
        verify(survivorsAutobots, never()).add(any());
        verify(mockedRule, never()).apply(any(), any());
    }

    @Test
    public void applyNoPrimals() {
        Pair<Transformer, Transformer> pair = TestUtils.getOrdinaryPair();
        Pair<List<Transformer>, List<Transformer>> survivors = Pair.of(survivorsAutobots, survivorsDecepticon);
        rule.apply(pair, survivors);
        verify(survivorsAutobots, never()).add(any());
        verify(survivorsDecepticon, never()).add(any());
        verify(mockedRule, times(1)).apply(pair, survivors);
    }

}