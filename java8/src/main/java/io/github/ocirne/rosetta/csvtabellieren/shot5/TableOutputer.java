package io.github.ocirne.rosetta.csvtabellieren.shot5;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/** kümmert sich um den Output einer Tabelle */
class TableOutputer {

    private final Table table;

    TableOutputer(Table table) {
        this.table = table;
    }

    /** Gibt die Tabelle als Liste von Strings zurück */
    List<String> createPlainOutput() {
        ImmutableList.Builder<String> result = ImmutableList.builder();
        result.add(formatContentRow(table.getHeader()));
        result.add(formatHeaderLine(table.getHeader()));
        for (Table.Row row : table.getBody()) {
            result.add(formatContentRow(row));
        }
        return result.build();
    }

    /** formatiert die Zeile unter der Headerzeile */
    private String formatHeaderLine(Table.Row headerRow) {
        return formatGenericRow(headerRow.getCells(), s -> "", '-', "+");
    }

    /** formatiert eine Zeile mit Inhalt */
    private String formatContentRow(Table.Row contentRow) {
        return formatGenericRow(contentRow.getCells(), Table.Cell::getValue, ' ', "|");
    }

    /** formatiert eine generische Zeile */
    private String formatGenericRow(List<Table.Cell> cells, Function<Table.Cell, String> extractCellValue, char paddingChar, String separator) {
        return cells.stream()
                .map(cell -> Strings.padEnd(extractCellValue.apply(cell), cell.getWidth(), paddingChar))
                .collect(Collectors.joining(separator)) + separator;
    }
}
