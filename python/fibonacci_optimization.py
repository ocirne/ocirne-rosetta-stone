from functools import cache
from math import sqrt
from timeit import timeit

HARDCODED = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]

# https://listry.com/list-of-the-first-1000-fibonacci-numbers/
F1 = 1
F10 = 55
F25 = 75025
F50 = 12586269025
F100 = 354224848179261915075
F1000 = 43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875


def hardcoded(n: int) -> int:
    return HARDCODED[n]


def recursive_by_definition(n: int) -> int:
    """ see https://en.wikipedia.org/wiki/Fibonacci_sequence#Definition """
    if n == 0:
        return 0
    if n == 1:
        return 1
    return recursive_by_definition(n - 1) + recursive_by_definition(n - 2)


def recursive_variant(n: int) -> int:
    if n < 2:
        return n
    return recursive_variant(n - 1) + recursive_variant(n - 2)


@cache
def cached_recursive(n: int) -> int:
    if n < 2:
        return n
    return cached_recursive(n - 1) + cached_recursive(n - 2)


def iterative(n: int) -> int:
    f1, f2 = 0, 1
    for _ in range(n):
        f1, f2 = f2, f1 + f2
    return f1


root5 = sqrt(5)
phi = (1+root5)/2


def binet(n: int) -> int:
    return round(phi**n / root5)


if __name__ == "__main__":
    assert hardcoded(1) == F1
    assert recursive_by_definition(1) == F1
    assert recursive_variant(1) == F1
    assert cached_recursive(1) == F1
    assert iterative(1) == F1
    assert binet(1) == F1

    print("f(1), 10.000 runs")
    print("        hardcoded:", timeit(lambda: hardcoded(1), number=10000))
    print("    by definition:", timeit(lambda: recursive_by_definition(1), number=10000))
    print("recursive variant:", timeit(lambda: recursive_variant(1), number=10000))
    print("        cached(!):", timeit(lambda: cached_recursive(1), number=10000))  # TODO end result is also cached!
    print("        iterative:", timeit(lambda: iterative(1), number=10000))
    print("            binet:", timeit(lambda: binet(1), number=10000))
    print()

    assert hardcoded(10) == F10
    assert recursive_by_definition(10) == F10
    assert recursive_variant(10) == F10
    assert cached_recursive(10) == F10
    assert iterative(10) == F10
    assert binet(10) == F10

    print("f(10), 10.000 runs")
    print("        hardcoded:", timeit(lambda: hardcoded(10), number=10000))
    print("    by definition:", timeit(lambda: recursive_by_definition(10), number=10000))
    print("recursive variant:", timeit(lambda: recursive_variant(10), number=10000))
    print("        cached(!):", timeit(lambda: cached_recursive(10), number=10000))  # TODO end result is also cached!
    print("        iterative:", timeit(lambda: iterative(10), number=10000))
    print("            binet:", timeit(lambda: binet(10), number=10000))
    print()

    assert recursive_by_definition(25) == F25
    assert recursive_variant(25) == F25
    assert cached_recursive(25) == F25
    assert iterative(25) == F25
    assert binet(25) == F25

    print("f(25), 100 runs")
    print("        hardcoded: -- not available")
    print("    by definition:", timeit(lambda: recursive_by_definition(25), number=100))
    print("recursive variant:", timeit(lambda: recursive_variant(25), number=100))
    print("        cached(!):", timeit(lambda: cached_recursive(25), number=100))
    print("        iterative:", timeit(lambda: iterative(25), number=100))
    print("            binet:", timeit(lambda: binet(25), number=100))
    print()

    assert cached_recursive(50) == F50
    assert iterative(50) == F50
    assert binet(50) == F50

    print("f(50), 100 runs")
    print("    by definition: -- too slow")
    print("recursive variant: -- too slow")
    print("        cached(!):", timeit(lambda: cached_recursive(30), number=100))
    print("        iterative:", timeit(lambda: iterative(30), number=100))
    print("            binet:", timeit(lambda: binet(30), number=100))
    print()

    assert cached_recursive(100) == F100
    assert iterative(100) == F100

    print("f(100), 100 runs")
    print("        cached(!):", timeit(lambda: cached_recursive(100), number=100))
    print("        iterative:", timeit(lambda: iterative(100), number=100))
    print("         binet(!):", timeit(lambda: binet(100), number=100))
    print()

    print("binet is faster, but wrong")
    f100_cache_recursive = cached_recursive(100)
    f100_iterative = iterative(100)
    f100_binet = binet(100)

    print(f100_cache_recursive, f100_cache_recursive == F100)
    print(f100_iterative, f100_iterative == F100)
    print(f100_binet, f100_binet == F100)
    print()

    assert iterative(1000) == F1000

    print("f(1000), 100 runs")
    print("           cached: -- maximum recursion depth exceeded")
    print("        iterative:", timeit(lambda: iterative(1000), number=100))
    print("         binet(!): -- wrong results")
    print()

    print("f(10000), 10 runs")
    print("        iterative:", timeit(lambda: iterative(10_000), number=10))
    print()
