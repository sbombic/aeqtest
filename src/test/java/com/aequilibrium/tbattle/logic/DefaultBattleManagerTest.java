package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.TestUtils;
import com.aequilibrium.tbattle.model.BattleOutcome;
import com.aequilibrium.tbattle.model.Transformer;
import com.aequilibrium.tbattle.repos.TransformersRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DefaultBattleManagerTest {

    @InjectMocks
    private DefaultBattleManager manager;

    @Mock
    private TransformersRepo mockedRepo;

    @Mock
    private BattleRulesFactory mockedRulesFactory;

    @Mock
    private BattleOutcomeRule mockedOutcomeRule;

    @Mock
    private BattleRule mockedRule;

    @Mock
    private BattleOutcome mockedOutcome;

    @Captor
    private ArgumentCaptor<Pair<Transformer, Transformer>> pairCaptor;

    private List<Long> battleSetup;

    private List<Transformer> transformers;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        battleSetup = Arrays.asList(1l, 2l, 3l, 4l, 5l);
        transformers = TestUtils.getTransformers();

        when(mockedRepo.findAllById(battleSetup)).thenReturn(transformers);
        when(mockedRulesFactory.getBattleRules()).thenReturn(mockedRule);
        when(mockedOutcomeRule.calculateOutcome(eq(1), any(), any(), any(), any())).thenReturn(mockedOutcome);
    }

    @Test
    public void battle() {
        doNothing().when(mockedRule).apply(pairCaptor.capture(), any());

        BattleOutcome outcome = manager.battle(battleSetup);
        assertThat(outcome).isSameAs(mockedOutcome);
        Pair<Transformer, Transformer> pairThatFought = pairCaptor.getValue();
        assertThat(pairThatFought).isNotNull();
        assertThat(pairThatFought.getFirst()).isSameAs(TestUtils.BLUESTREAK);
        assertThat(pairThatFought.getSecond()).isSameAs(TestUtils.SOUNDWAVE);
    }

    @Test
    public void battleAnnihilation() {
        doThrow(TotalAnnihilationException.class).when(mockedRule).apply(pairCaptor.capture(), any());

        BattleOutcome outcome = manager.battle(battleSetup);
        assertThat(outcome.getNumberOfBattles()).isEqualTo(0);
        assertThat(outcome.getWinningTeamType()).isEqualTo("Total annihilation.");
    }
}