#!/usr/bin/env python3

"""
File: solution.py
Author: Matthew Cole
Date: 2/23/17
"""

PLSO = frozenset(word for word in "ALFA BRAVO CHARLIE DELTA ECHO FOXTROT GOLF HOTEL INDIA JULIETT KILO LIMA MIKE NOVEMBER OSCAR PAPA QUEBEC ROMEO SIERRA TANGO UNIFORM VICTOR WHISKEY XRAY YANKEE ZULU".split())

def isPLSO(str1, str2):
	"""
	Uses the Wagner-Fischer algorithm for computing edit distance.
	Has efficient run time but inefficient space usage for large strings.
	INPUT: strings str1 and str2 - two strings to be compared, with length m and n respectively
	#OUTPUT: integer ld - the Levenshtein distance between str1 and str2
	"""

	m = len(str1)
	n = len(str2)

	#Build table d, initialize with 0 values
	d = list()
	d = [[0 for x in range(0,m+1)] for x in range(0,n+1)]

	#Fill source prefixes
	for i in range(0,m+1):
		d[0][i] = i

	#Fill target prefixes
	for j in range(0,n+1):
		d[j][0] = j

	#Calculate ld at table position[i][j]
	for j in range(1,n+1):
		for i in range(1,m+1):
			#If characters match at each position, no operation is required
			if str1[i-1] == str2[j-1]:
				d[j][i] = d[j-1][i-1]
			#Otherwise, calculate minimum cost for each operation
			else:
				d[j][i] = min(
				d[j][i-1] + 1,	#deletion
				d[j-1][i] + 1,	#insertion
				d[j-1][i-1] + 1	#substitution
				)

	#Return Levenshtein Distance
	return d[n][m] <= 1

if __name__ == "__main__":

	#Get test case
	tokens = [token for token in input().split()]

#	if __debug__:
#		print("0", " ".join(tokens))

	# 1. Resolve joins
	JOINS = {token1+token2:(token1,token2)
			for token1 in PLSO for token2 in PLSO}
	tokens1 = []
	i = 0
	while i < len(tokens):
		if tokens[i] in JOINS:
			tokens1.append(JOINS[tokens[i]][0])
			tokens1.append(JOINS[tokens[i]][1])
			i += 1
		else:
			tokens1.append(tokens[i])
			i += 1

#	if __debug__:
#		print("1", " ".join(tokens1))

	# 2. Resolve splits
	tokens2 = []
	i = 0
	while i < len(tokens1)-1:
		if tokens1[i]+tokens1[i+1] in PLSO:
			tokens2.append(tokens1[i]+tokens1[i+1])
			i += 2
		elif i == (len(tokens1) - 2):
			tokens2.append(tokens1[-2])
			tokens2.append(tokens1[-1])
			break
		else:
			tokens2.append(tokens1[i])
			i += 1


#	if __debug__:
#		print("2", " ".join(tokens2))

	# 3. Resolve insert/deletes
	tokens3 = []
	for word in tokens2:
		for pword in PLSO:
			if isPLSO(word, pword):
				tokens3.append(pword)
				break
		else:
			tokens3.append(word)

#	if __debug__:
#		print("3", " ".join(tokens3))

	# 4. Resolve transposes
	tokens4 = []
	for word in tokens3:
		if word in PLSO:
			tokens4.append(word)
			continue
		for i in range(len(word)-1):
			tword = word[:i] + word[i+1] + word[i] + word[i+2:]
			if tword in PLSO:
				tokens4.append(tword)
				break
		else:
			tokens4.append(word)

#	if __debug__:
#		print("4", " ".join(tokens4))

	# 5. Resolve garbles, print final message
	tokens5 = []
	for word in tokens4:
		if word in PLSO:
			tokens5.append(word)
		else:
			tokens5.append("????")

#	if __debug__:
#		print("5", " ".join(tokens5))

	print(" ".join(tokens5))
