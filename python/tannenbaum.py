# https://ccd-school.de/coding-dojo/function-katas/tannenbaum/

b = " "
a = "*"
x = "X"
i = "I"
n = "\n"


def tannenbaum(h=5, s=True):
    """
    >>> tannenbaum()
         *
         X
        XXX
       XXXXX
      XXXXXXX
     XXXXXXXXX
         I
    >>> tannenbaum(2, s=False)
      X
     XXX
      I
    """
    # fmt: off
    print(n.join(([h*b+a]if s else[])+[(h-j)*b+(2*j+1)*x for j in range(h)]+[h*b+i]))
    # fmt: on


if __name__ == "__main__":
    tannenbaum()
