package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.TestUtils;
import com.aequilibrium.tbattle.model.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SkillCheckRuleTest {

    @InjectMocks
    private SkillCheckRule rule;

    @Mock
    private BattleRule mockedRule;

    @Mock
    private List<Transformer> survivorsAutobots;

    @Mock
    private List<Transformer> survivorsDecepticon;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void applyBothSkilled() {
        Pair<Transformer, Transformer> pair = TestUtils.getOrdinaryPairOverallAutobotWins();
        Pair<List<Transformer>, List<Transformer>> survivors = Pair.of(survivorsAutobots, survivorsDecepticon);
        rule.apply(pair, survivors);
        verify(mockedRule, times(1)).apply(pair, survivors);
        verify(survivorsAutobots, never()).add(any());
        verify(survivorsDecepticon, never()).add(any());
    }

    @Test
    public void applyAutobotMoreSkilled() {
        Pair<Transformer, Transformer> pair = TestUtils.getOrdinaryPairDecepticonChicken();
        Pair<List<Transformer>, List<Transformer>> survivors = Pair.of(survivorsAutobots, survivorsDecepticon);
        rule.apply(pair, survivors);
        verify(mockedRule, never()).apply(pair, survivors);
        verify(survivorsAutobots, times(1)).add(TestUtils.BUMBLEBEE);
        verify(survivorsDecepticon, never()).add(any());
    }

    @Test
    public void applyDecepticonMoreSkilled() {
        Pair<Transformer, Transformer> pair = TestUtils.getOrdinaryPair();
        Pair<List<Transformer>, List<Transformer>> survivors = Pair.of(survivorsAutobots, survivorsDecepticon);
        rule.apply(pair, survivors);
        verify(mockedRule, never()).apply(pair, survivors);
        verify(survivorsAutobots, never()).add(any());
        verify(survivorsDecepticon, times(1)).add(TestUtils.SOUNDWAVE);
    }
}