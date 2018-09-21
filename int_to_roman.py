import math, sys

numerals = {1: 'I', 5: 'V', 10: 'X', 50: 'L', 100: 'C', 500: 'D', 1000: 'M'}


def int_to_roman(number):
    result = [0 for i in range(len(numerals))]

    for i, numeral_value in enumerate(list(numerals.keys())[::-1]):
        if number - numeral_value >= 0:
            times = math.floor(number / numeral_value)
            result[i] = times
            number -= times * numeral_value

    result.reverse()
    numeral_values = list(numerals.values())
    final_result = []

    for i, times in enumerate(result[::2]):
        i *= 2

        if i != 0 and result[i - 2] == 4 and result[i - 1]:
            final_result.append(numeral_values[i])
            final_result.append(numeral_values[i - 2])

        if times == 4 and not result[i + 1]:
            final_result.append(numeral_values[i + 1])
            final_result.append(numeral_values[i])

        elif times != 4:
            if result[i]:
                final_result.append(result[i] * numeral_values[i])
            if len(result) - 1 != i and result[i + 1]:
                final_result.append(result[i + 1] * numeral_values[i + 1])

    return ' '.join(final_result[::-1])


print(int_to_roman(int(sys.argv[1])))
