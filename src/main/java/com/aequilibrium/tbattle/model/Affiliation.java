package com.aequilibrium.tbattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Affiliation {

    A("Autobot"), D("Decepticon");

    private String name;
}
