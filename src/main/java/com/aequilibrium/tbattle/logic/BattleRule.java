package com.aequilibrium.tbattle.logic;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.List;

public interface BattleRule {
    void apply(Pair<Transformer, Transformer> pair, Pair<List<Transformer>, List<Transformer>> survivors);
}
