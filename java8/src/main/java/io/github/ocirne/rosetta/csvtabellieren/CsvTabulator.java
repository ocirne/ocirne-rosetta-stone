package io.github.ocirne.rosetta.csvtabellieren;

import java.util.List;

public interface CsvTabulator {

    List<String> tabelliere(List<String> csvZeilen);
}
