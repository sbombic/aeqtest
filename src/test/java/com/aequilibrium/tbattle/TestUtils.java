package com.aequilibrium.tbattle;

import com.aequilibrium.tbattle.model.Affiliation;
import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static final Transformer OPTIMUS = new Transformer(1, "Optimus Prime", Affiliation.A, 10, 10, 10, 10, 10, 10, 10, 10);

    public static final Transformer BAD_OPTIMUS = new Transformer(2, "Optimus Prime", Affiliation.D, 10, 10, 10, 10, 10, 10, 10, 10);

    public static final Transformer PREDAKING = new Transformer(3, "Predaking", Affiliation.D, 10, 10, 10, 10, 10, 10, 10, 10);

    public static final Transformer SOUNDWAVE = new Transformer(4, "Soundwave", Affiliation.D, 8,9,2,6,7,5,6,10);

    public static final Transformer BLUESTREAK = new Transformer(5, "Bluestreak", Affiliation.A, 6,6,7,9,5,2,9,7);

    public static final Transformer HUBCAP = new Transformer(6, "Hubcap", Affiliation.A, 4,4,4,4,4,4,4,4);

    public static final Transformer WEAKLING = new Transformer(7, "Weakling", Affiliation.D, 2, 2, 2, 2, 2, 2, 2, 2);

    public static final Transformer SOUNDSHOCK = new Transformer(8, "Soundshock", Affiliation.A, 8,9,2,6,7,5,6,10);

    // :-D
    public static final Transformer BICYCLE = new Transformer(9, "Bicycle", Affiliation.A, 2, 2, 2, 2, 2, 1, 2, 2);

    public static final Transformer BUMBLEBEE = new Transformer(10, "Bumblebee", Affiliation.A, 9, 9, 9, 9, 9, 9, 9, 9);

    public static List<Transformer> getTransformers() {
        return Arrays.asList(SOUNDWAVE, BLUESTREAK, HUBCAP);
    }

    public static Pair<Transformer, Transformer> getAnnihilationPair() {
        return Pair.of(OPTIMUS, PREDAKING);
    }

    public static Pair<Transformer, Transformer> getPrimalClonesPair() {
        return Pair.of(OPTIMUS, BAD_OPTIMUS);
    }

    public static Pair<Transformer, Transformer> getOptimusAndOrdinary() {
        return Pair.of(OPTIMUS, SOUNDWAVE);
    }

    public static Pair<Transformer, Transformer> getPredakingAndOrdinary() {
        return Pair.of(BLUESTREAK, PREDAKING);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPair() {
        return Pair.of(BLUESTREAK, SOUNDWAVE);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPairOverallAutobotWins() {
        return Pair.of(HUBCAP, WEAKLING);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPairOverallDecepticonWins() {
        return Pair.of(HUBCAP, SOUNDWAVE);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPairOverallTie() {
        return Pair.of(SOUNDSHOCK, SOUNDWAVE);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPairAutobotChicken() {
        return Pair.of(BICYCLE, SOUNDWAVE);
    }

    public static Pair<Transformer, Transformer> getOrdinaryPairDecepticonChicken() {
        return Pair.of(BUMBLEBEE, WEAKLING);
    }
}
