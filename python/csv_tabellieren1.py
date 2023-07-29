from typing import List

# https://ccd-school.de/coding-dojo/function-katas/csv-tabellieren/


def get_csv_row(csv_line: str) -> List[str]:
    """
    Zerlegt eine CSV Zeile in einzelne Zellen.

    >>> get_csv_row("Name;Strasse;Ort;Alter")
    ['Name', 'Strasse', 'Ort', 'Alter']
    """
    return csv_line.split(";")


def get_max_width(csv_table: List[List[str]], column_index: int) -> int:
    """
    Ermittelt die maximale Breite einer Spalte.

    >>> get_max_width([
    ... ["a", "alpha", "eins"],
    ... ["b", "beta", "zwei"],
    ... ["c", "gamma", "drei"]
    ... ], 1)
    5
    """
    return max(len(row[column_index]) for row in csv_table)


def get_max_column_widths(csv_table: List[List[str]]) -> List[int]:
    """
    Ermittelt je Spalte die maximale Breite.

    >>> get_max_column_widths([
    ... ["a", "alpha", "eins"],
    ... ["b", "beta", "zwei"],
    ... ["c", "gamma", "drei"]
    ... ])
    [1, 5, 4]
    """
    return [get_max_width(csv_table, index) for index in range(len(csv_table[0]))]


def format_csv_row(csv_row: List[str], widths: List[int]) -> str:
    """
    Formatiert eine einzelne Tabellenzeile mit den Spaltenbreiten.

    >>> format_csv_row(["a", "alpha", "eins"], [3, 7, 4])
    'a  |alpha  |eins|'
    """
    return (
        "|".join(cell_value.ljust(width) for cell_value, width in zip(csv_row, widths))
        + "|"
    )


def format_csv_header(widths: List[int]) -> str:
    """
    Formatiert die Headerunterschrift anhand der Spaltenbreiten.

    >>> format_csv_header([3, 7, 4])
    '---+-------+----+'
    """
    return "+".join("".ljust(width, "-") for width in widths) + "+"


def pretty_print_table(csv_table: List[List[str]], widths: List[int]):
    """
    Gibt die Tabelle mit maximalen Spaltenbreiten aus.

    >>> pretty_print_table([
    ... ['Lateinisch', "Griechisch", "Zahlen"],
    ... ["a", "alpha", "eins"],
    ... ["b", "beta", "zwei"],
    ... ["c", "gamma", "drei"]
    ... ],
    ... [10, 10, 6])
    Lateinisch|Griechisch|Zahlen|
    ----------+----------+------+
    a         |alpha     |eins  |
    b         |beta      |zwei  |
    c         |gamma     |drei  |
    """
    print(format_csv_row(csv_table[0], widths))
    print(format_csv_header(widths))
    for row in csv_table[1:]:
        print(format_csv_row(row, widths))


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
    csv_table = [get_csv_row(csv_line) for csv_line in csv_input]
    max_column_width = get_max_column_widths(csv_table)
    pretty_print_table(csv_table, max_column_width)
