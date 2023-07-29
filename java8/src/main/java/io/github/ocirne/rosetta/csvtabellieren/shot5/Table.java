package io.github.ocirne.rosetta.csvtabellieren.shot5;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;

/** repräsentiert eine (CSV)-Tabelle */
class Table {

    private final Row header;

    private final List<Row> body;

    private final Map<Integer, Integer> columnWidths;

    Table(List<String> csvZeilen) {
        this.columnWidths = new HashMap<>();
        Iterator<String> iterator = csvZeilen.iterator();
        header = new Row(iterator.next());
        ImmutableList.Builder<Row> builder = ImmutableList.builder();
        iterator.forEachRemaining(line -> builder.add(new Row(line)));
        this.body = builder.build();
    }

    Row getHeader() {
        return header;
    }

    List<Row> getBody() {
        return body;
    }

    /** Zeilen. Eine Zeile kennt die Zellen in der Zeile. */
    class Row {

        private final List<Cell> cells;

        Row(String csvRow) {
            String[] cellValues = csvRow.split(";");
            ImmutableList.Builder<Cell> builder = ImmutableList.builder();
            for (int i = 0; i < cellValues.length; i++) {
                builder.add(new Cell(cellValues[i], i));
            }
            this.cells = builder.build();
        }

        List<Cell> getCells() {
            return cells;
        }
    }

    /** Zellen. Jede Zelle kennt ihren Wert und ihren Spaltenindex. */
    class Cell {

        private final String value;

        private final int columnIndex;

        Cell(String value, int columnIndex) {
            this.value = value;
            this.columnIndex = columnIndex;
            columnWidths.put(columnIndex, max(columnWidths.getOrDefault(columnIndex, 0), value.length()));
        }

        String getValue() {
            return value;
        }

        int getWidth() {
            return columnWidths.get(columnIndex);
        }
    }
}
