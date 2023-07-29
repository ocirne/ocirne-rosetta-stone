package io.github.ocirne.rosetta.csvtabellieren.shot4;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.Math.max;
import static java.util.function.Function.identity;

public class Shot4 implements CsvTabulator {

    private Map<Integer, Integer> columns = new HashMap<>();

    private Row header;

    private List<Row> bodyRows = new LinkedList<>();

    public List<String> tabelliere(List<String> csvZeilen) {
        createInternalRepresentation(csvZeilen);
        return constructOutput();
    }

    private void createInternalRepresentation(List<String> csvZeilen) {
        Iterator<String> iterator = csvZeilen.iterator();
        header = new Row(iterator.next(), columns);
        while(iterator.hasNext()) {
            bodyRows.add(new Row(iterator.next(), columns));
        }
    }

    private List<String> constructOutput() {
        Builder<String> result = ImmutableList.builder();
        result.add(createRow(header));
        result.add(createHeaderUnderLine(header));
        for (Row bodyRow : bodyRows) {
            result.add(createRow(bodyRow));
        }
        return result.build();
    }

    private String createHeaderUnderLine(Row row) {
        return createRow(row, unused -> "", '-', '+');
    }

    private String createRow(Row row) {
        return createRow(row, identity(), ' ', '|');
    }

    private String createRow(Row row, Function<String, String> cellValue, char pad, char sep) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < row.cells.size(); i++) {
            result.append(Strings.padEnd(cellValue.apply(row.cells.get(i)), columns.get(i), pad));
            result.append(sep);
        }
        return result.toString();
    }

    static class Row {

        final List<String> cells;

        Row(String csvRow, Map<Integer, Integer> columns) {
            String[] cellValues = csvRow.split(";");
            Builder<String> builder = ImmutableList.builder();
            for (int i = 0; i < cellValues.length; i++) {
                String value = cellValues[i];
                columns.put(i, max(columns.getOrDefault(i, 0), value.length()));
                builder.add(value);

            }
            this.cells = builder.build();
        }
    }
}
