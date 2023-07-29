package io.github.ocirne.rosetta.csvtabellieren.shot0;

import com.google.common.collect.ImmutableList;
import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

/**
 * Als Basis für eine Refactoring Kata eine "historisch gewachsene" Variante.
 */
public class Shot0Casual implements CsvTabulator {

    @Override
    public List<String> tabelliere(List<String> csvRows) {
        ArrayList<List<String>> csvTable = new ArrayList<>();
        int j;
        int i;
        int t;
        ArrayList<String> result = new ArrayList<>();
        String line = "";
        for (String row : csvRows) {
            csvTable.add(Arrays.asList(row.split(";")));
        }
        int[] maxColumnWidth = new int[csvTable.get(0).size()];
        for (i = 0; i < csvTable.get(0).size(); i++) {
            maxColumnWidth[i] = 0;
        }
        for (List<String> row : csvTable) {
            for (i = 0; i < row.size(); i++) {
                maxColumnWidth[i] = max(maxColumnWidth[i], row.get(i).length());
            }
        }
        for (j = 0; j < csvTable.size(); j++) {
            List<String> cell = csvTable.get(j);
            for (i = 0; i < cell.size(); i++) {
                line += cell.get(i);
                for (t = 0; t < maxColumnWidth[i] - cell.get(i).length(); t++) {
                    line += ' ';
                }
                line += '|';
            }
            result.add(line);
            line = "";
            if (j == 0) {
                for (i = 0; i < cell.size(); i++) {
                    for (t = 0; t < maxColumnWidth[i]; t++) {
                        line += '-';
                    }
                    line += '+';
                }
                result.add(line);
                line = "";
            }
        }
        return result;
    }
}
