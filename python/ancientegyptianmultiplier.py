def f(x, y):
    while x:
        if x & 1:
            yield y
        x >>= 1
        y <<= 1


def mul(x: int, y: int) -> int:
    return sum(f(x, y))


for i in range(101):
    for j in range(101):
        assert i * j == mul(i, j)
