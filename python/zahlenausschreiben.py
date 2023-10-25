numbers = {
    1: "eins",
    2: "zwei",
    3: "drei",
    4: "vier",
    5: "fünf",
    6: "sechs",
    7: "sieben",
    8: "acht",
    9: "neun",
    10: "zehn",
    11: "elf",
    12: "zwölf",
    16: "sechzehn",
    17: "siebzehn",
    20: "zwanzig",
    30: "dreißig",
    60: "sechzig",
    70: "siebzig",
    100: "einhundert",
}

baz = [
    ("eintausend", "tausend"),
    ("eine Million ", " Millionen "),
    ("eine Milliarde ", " Milliarden "),
    ("eine Billion ", " Billionen "),
    ("eine Billiarde ", " Billiarden "),
    ("eine Trillion ", " Trillionen "),
]


def foo(i, p):
    assert 0 <= i <= 999
    if i in numbers:
        if i == 1 and p > 0:
            return "ein"
        return numbers[i]
    s = ""
    f100, r100 = divmod(i, 100)
    if f100 > 0:
        if f100 == 1:
            s += "einhundert"
        else:
            s += numbers[f100] + "hundert"
    if r100 > 0 and r100 in numbers:
        s += numbers[r100]
    else:
        zehner, einer = divmod(r100, 10)
        if zehner == 0:
            s += numbers[einer]
        else:
            if einer == 0:
                if zehner * 10 in numbers:
                    s += numbers[zehner * 10]
                else:
                    s += numbers[zehner] + "zig"
            elif einer == 1:
                s += "ein"
                if zehner > 1:
                    s += "und"
                if zehner * 10 in numbers:
                    s += numbers[zehner * 10]
                else:
                    s += numbers[zehner] + "zig"
            else:
                s += numbers[einer]
                if zehner > 1:
                    s += "und"
                if zehner * 10 in numbers:
                    s += numbers[zehner * 10]
                else:
                    s += numbers[zehner] + "zig"
    return s


def bar(n, p=0):
    d, r = divmod(n, 1000)
    if d > 0:
        if d == 1:
            if r == 0:
                return baz[p][0]
            else:
                return baz[p][0] + foo(r, p)
        else:
            if r == 0:
                return bar(d, p + 1)
            else:
                return bar(d, p + 1) + baz[p][1] + foo(r, p)
    return foo(r, p)


def spell_german_number(n):
    """
    >>> spell_german_number(0)
    'null'
    >>> spell_german_number(1)
    'eins'
    >>> spell_german_number(2)
    'zwei'
    >>> spell_german_number(10)
    'zehn'
    >>> spell_german_number(11)
    'elf'
    >>> spell_german_number(13)
    'dreizehn'
    >>> spell_german_number(16)
    'sechzehn'
    >>> spell_german_number(27)
    'siebenundzwanzig'
    >>> spell_german_number(30)
    'dreißig'
    >>> spell_german_number(60)
    'sechzig'
    >>> spell_german_number(80)
    'achtzig'
    >>> spell_german_number(100)
    'einhundert'
    >>> spell_german_number(121)
    'einhunderteinundzwanzig'
    >>> spell_german_number(242)
    'zweihundertzweiundvierzig'
    >>> spell_german_number(1000)
    'eintausend'
    >>> spell_german_number(1121)
    'eintausendeinhunderteinundzwanzig'
    >>> spell_german_number(1980)
    'eintausendneunhundertachtzig'
    >>> spell_german_number(2525)
    'zweitausendfünfhundertfünfundzwanzig'
    >>> spell_german_number(1234567890)
    'eine Milliarde zweihundertvierunddreißig Millionen fünfhundertsiebenundsechzigtausendachthundertneunzig'
    >>> spell_german_number(1234567890987654321)
    'eine Trillion zweihundertvierunddreißig Billiarden fünfhundertsiebenundsechzig Billionen achthundertneunzig Milliarden neunhundertsiebenundachtzig Millionen sechshundertvierundfünfzigtausenddreihunderteinundzwanzig'  # noqa: E501
    >>> spell_german_number(1000000000000000000)
    'eine Trillion'
    """
    if n == 0:
        return "null"
    return bar(n).strip()
