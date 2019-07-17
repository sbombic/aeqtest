package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AbstractBattleRuleTest {

    private Pair<Transformer, Transformer> pair;

    private Pair<List<Transformer>, List<Transformer>> survivors;

    @Mock
    private Transformer autobot;
    @Mock
    private Transformer decepticon;

    @Mock
    private BattleRule mockedRule;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        pair = Pair.of(autobot, decepticon);
        survivors = Pair.of(Arrays.asList(autobot), Arrays.asList(decepticon));
    }

    @Test
    public void testNoNextRule() {
        BattleRule rule = new DummyRule();
        // shouldn't get NPE in next call
        rule.apply(pair, survivors);
    }

    @Test
    public void nextRule() {
        BattleRule rule = new DummyRule(mockedRule);
        rule.apply(pair, survivors);
        verify(mockedRule, times(1)).apply(pair, survivors);
    }

    @Test
    public void autobot() {
        DummyRule rule = new DummyRule();
        assertThat(rule.autobot(pair)).isSameAs(autobot);
    }

    @Test
    public void decepticon() {
        DummyRule rule = new DummyRule();
        assertThat(rule.decepticon(pair)).isSameAs(decepticon);
    }

    private class DummyRule extends AbstractBattleRule {

        public DummyRule() {
            super();
        }

        public DummyRule(BattleRule nextRule) {
            super(nextRule);
        }

        @Override
        public void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors) {
            nextRule(pair, survivors);
        }
    }
}