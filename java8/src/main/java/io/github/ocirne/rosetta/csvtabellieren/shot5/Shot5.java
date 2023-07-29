package io.github.ocirne.rosetta.csvtabellieren.shot5;

import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.List;

public class Shot5 implements CsvTabulator {

    @Override
    public List<String> tabelliere(List<String> csvZeilen) {
        Table table = new Table(csvZeilen);
        TableOutputer outputer = new TableOutputer(table);
        return outputer.createPlainOutput();
    }
}
