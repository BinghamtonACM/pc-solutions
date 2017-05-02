#!/usr/bin/env python3

"""
File: solution.py
Author: Matthew Cole
Date: 4/18/17
"""

from string import punctuation, whitespace
from sys import stdin
from itertools import chain

if __name__ == "__main__":
	
	# Read the words from standard input, remove one level of nesting,
	# such that words is a flat, ordered list of each word appearing in stdin
	_ = int(input())
	words = [line.split() for line in stdin]
	words = list(chain.from_iterable(words))
	
	# Remove punctuation and whitespace characters from each word, then casefold
	words = [word.strip(punctuation+whitespace).casefold() for word in words]
	
	# Make a list of tuples of each word in stdin and its following word
	next_words = [(word, nword) for word, nword in zip(words, words[1:])]
	
	# Construct a dictionary of each word and all words following it
	next_word_dict = dict()
	for word, nword in next_words:
		if word in next_word_dict:
			if nword not in next_word_dict[word]:
				next_word_dict[word] += [nword]
		else:
			next_word_dict[word] = [nword]
	
	# Sort and print
	for word in sorted(next_word_dict.keys()):
		print(word, " ".join(sorted(next_word_dict[word])))