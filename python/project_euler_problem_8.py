from math import prod
from timeit import timeit
from functools import cache


@cache
def read_input():
    """
    Liest den Input und cached diesen.

    """
    return "".join(line.strip() for line in open("../resources/pe8.txt").readlines())


def product(s):
    """
    Wandelt den Eingabestring in einzelne Zahlen um und berechnet das Produkt.

    >>> product('1234')
    24
    >>> product('7411')
    28
    """
    return prod(int(c) for c in list(s))


def all_substrings(s, length):
    """
    Erzeugt alle Substrings von s der gegebenen Länge.

    >>> list(all_substrings('', 3))
    []
    >>> list(all_substrings('ab', 3))
    []
    >>> list(all_substrings('abcdef', 3))
    ['abc', 'bcd', 'cde', 'def']
    """
    return (s[i : i + length] for i in range(len(s) - length + 1))


def candidates(line, length):
    """
    Erzeugt alle interessanten Substrings aus der Eingabe: Enthalten keine 0 und haben die gegebene Länge.

    >>> list(candidates("abcd0e0fghij", 3))
    ['abc', 'bcd', 'fgh', 'ghi', 'hij']
    """
    for no_zeros in line.split("0"):
        for sub in all_substrings(no_zeros, length):
            yield sub


def problem8a(length=13):
    """
    Löst Project Euler Problem 8 durch Betrachtung aller Substrings.

    >>> problem8a(4)
    5832
    """
    return max(product(s) for s in all_substrings(read_input(), length))


def problem8b(length=13):
    """
    Löst Project Euler Problem 8 durch Betrachtung nur der relevanten Substrings (ohne 0).

    >>> problem8b(4)
    5832
    """
    return max(product(s) for s in candidates(read_input(), length))


if __name__ == "__main__":
    print(problem8a())
    print(problem8b())

    print("         alle:", timeit(problem8a, number=1000))
    print("nur relevante:", timeit(problem8b, number=1000))
