def naive_is_alphabetical(word):
    """ Suboptimal O(n*log(n)) solution

        Compares sorted word to itself
        If the two are equal, then the word
        was already in order.
    """
    return word == ''.join(sorted(word))


def is_alphabetical(word):
    """ Optimal O(n) solution

        Verifies for each pair of subsequent
        letters if the former is less than the latter.
    """
    pairs = zip(word, word[1:])
    for prev, current in pairs:
        if prev > current:
            return False
    return True

def main():
    word = input('\nPlease enter a word.\n>> ')

    print("\n\t"
        "According to the naive sorting implementation, "
        "is the word in alphabetical order?\n"
        "\t{}".format('Yes!' if naive_is_alphabetical(word) else 'No!'))

    print("\n\t"
        "According to the optimal implementation, "
        "is the word in alphabetical order?\n"
        "\t{}".format('Yes!' if is_alphabetical(word) else 'No!'))

    print()

if __name__ == "__main__":
    main()
