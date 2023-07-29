from typing import List

# https://ccd-school.de/coding-dojo/function-katas/csv-tabellieren/


def format_csv_row(
    csv_row: List[str], widths: List[int], f=lambda c: c, pad=" ", sep="|"
) -> str:
    """
    Formatiert eine einzelne Tabellenzeile mit den Spaltenbreiten.
    Für die Headerunterzeile können Padding und Separator geändert werden.

    >>> format_csv_row(["a", "alpha", "eins"], [3, 7, 4])
    'a  |alpha  |eins|'
    >>> format_csv_row(["a", "alpha", "eins"], [3, 7, 4], f=lambda _: '', pad='-', sep='+')
    '---+-------+----+'
    """
    return (
        sep.join(f(cell).ljust(width, pad) for cell, width in zip(csv_row, widths))
        + sep
    )


def csv_tabellieren(csv_input: List[str]):
    """
    Gibt den CSV Input formatiert mit Spaltenbreiten und Tabellenlinien aus.

    >>> testData = [
    ... "Name;Strasse;Ort;Alter",
    ... "Peter Pan;Am Hang 5;12345 Einsam;42",
    ... "Maria Schmitz;Kölner Straße 45;50123 Köln;43",
    ... "Paul Meier;Münchener Weg 1;87654 München;65",
    ... ]

    >>> csv_tabellieren(testData)
    Name         |Strasse         |Ort          |Alter|
    -------------+----------------+-------------+-----+
    Peter Pan    |Am Hang 5       |12345 Einsam |42   |
    Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |
    Paul Meier   |Münchener Weg 1 |87654 München|65   |
    """
    table = [line.split(";") for line in csv_input]
    widths = [max(len(row[index]) for row in table) for index in range(len(table[0]))]
    print(format_csv_row(table[0], widths))
    print(format_csv_row(table[0], widths, lambda cv: "", "-", "+"))
    for row in table[1:]:
        print(format_csv_row(row, widths))
