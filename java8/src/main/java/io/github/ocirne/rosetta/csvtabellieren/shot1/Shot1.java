package io.github.ocirne.rosetta.csvtabellieren.shot1;

import com.google.common.collect.ImmutableList.Builder;
import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

// Der erste Schuß
public class Shot1 implements CsvTabulator {

    @Override
    public List<String> tabelliere(List<String> csvZeilen) {
        List<List<String>> csvData = createData(csvZeilen);
        List<Integer> maxWidth = calcMaxWidth(csvData);
        Builder<String> resultBuilder = new Builder<>();
        String line = "";
        for (int j = 0; j < csvData.size(); j++) {
            List<String> row = csvData.get(j);
            for (int i = 0; i < row.size(); i++) {
                line += leftPad(row.get(i), maxWidth.get(i), ' ');
                line += '|';
            }
            resultBuilder.add(line);
            line = "";
            if (j == 0) {
                for (int i = 0; i < row.size(); i++) {
                    line += leftPad("", maxWidth.get(i), '-');
                    line += '+';
                }
                resultBuilder.add(line);
                line = "";
            }
        }
        return resultBuilder.build();
    }

    /** füllt einen String mit Leerzeichen auf */
    private String leftPad(String s, int width, char c) {
        StringBuilder builder = new StringBuilder(s);
        while (builder.length() < width) {
            builder.append(c);
        }
        return builder.toString();
    }

    /** macht aus Zeilen Zellen */
    private List<List<String>> createData(List<String> csvZeilen) {
        return csvZeilen.stream()
                .map(line -> Arrays.asList(line.split(";")))
                .collect(Collectors.toList());
    }

    /** ermittelt die maximale Breite pro Spalte aufgrund der Zellenbreite */
    private List<Integer> calcMaxWidth(List<List<String>> csvCells) {
        Integer[] maxWidth = new Integer[csvCells.get(0).size()];
        for (int i = 0; i < csvCells.get(0).size(); i++) {
            maxWidth[i] = 0;
        }
        for (List<String> row : csvCells) {
            for (int i = 0; i < row.size(); i++) {
                maxWidth[i] = max(maxWidth[i], row.get(i).length());
            }
        }
        return Arrays.asList(maxWidth);
    }
}
