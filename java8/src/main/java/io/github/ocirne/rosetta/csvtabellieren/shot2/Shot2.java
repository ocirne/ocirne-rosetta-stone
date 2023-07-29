package io.github.ocirne.rosetta.csvtabellieren.shot2;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.*;
import java.util.function.Function;

import static java.lang.Math.max;

// fehlt:
// - checker framework?
// - Fehlerbehandlung
// - guava zip?

public class Shot2 implements CsvTabulator {

    public List<String> tabelliere(List<String> csvZeilen) {
        Table table = new Table(csvZeilen);
        ImmutableList.Builder<String> result = new ImmutableList.Builder<>();
        result.add(createRow(table.getHeader()),
                   createHeaderLine(table.getHeader()));
        table.getBodyRows()
                .forEach(row -> result.add(createRow(row)));
        return result.build();
    }

    private String createHeaderLine(Row row) {
        return createRow(row, unused -> "", '-', '+');
    }

    private String createRow(Row row) {
        return createRow(row, Cell::getValue, ' ', '|');
    }

    private String createRow(Row row, Function<Cell, String> cellValue, char pad, char sep) {
        StringBuilder result = new StringBuilder();
        for (Cell cell : row.getCells()) {
            result.append(Strings.padEnd(cellValue.apply(cell), cell.getWidth(), pad));
            result.append(sep);
        }
        return result.toString();
    }

    static class Table {

        final Row header;

        final List<Row> bodyRows;

        Table(List<String> csvZeilen) {
            Iterator<String> iterator = csvZeilen.iterator();
            Map<Integer, Column> columns = new HashMap<>();
            this.header = new Row(iterator.next(), columns);
            this.bodyRows = new LinkedList<>();
            while(iterator.hasNext()) {
                this.bodyRows.add(new Row(iterator.next(), columns));
            }
        }

        public Row getHeader() {
            return header;
        }

        List<Row> getBodyRows() {
            return bodyRows;
        }
    }

    static class Column {
        int width;

        public int getWidth() {
            return width;
        }

        public void updateWidth(int width) {
            this.width = max(this.width, width);
        }
    }

    static class Row {

        List<Cell> cells;

        public Row(String csvRow, Map<Integer, Column> columns) {
            String[] cellValues = csvRow.split(";");
            // Precondition: #cellValues = #columns
            this.cells = new ArrayList<>();
            for (int i = 0; i < cellValues.length; i++) {
                String value = cellValues[i];
                if (!columns.containsKey(i)) {
                    columns.put(i, new Column());
                }
                Column column = columns.get(i);
                this.cells.add(new Cell(value, column));
                column.updateWidth(value.length());
            }
        }

        public List<Cell> getCells() {
            return cells;
        }
    }

    static class Cell {

        final String value;

        final Column column;

        Cell(String value, Column column) {
            this.value = value;
            this.column = column;
        }

        public String getValue() {
            return value;
        }

        public int getWidth() {
            return column.getWidth();
        }
    }
}
