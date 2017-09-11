#!/usr/bin/env python3

"""
File: solution.py
Author: Matthew Cole
Date: 2/23/17
"""
import sys

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
	
	CASES = 6
	
	for case in range(CASES):
		print("Case %d:" % case)
		infile = str("input%d.txt" % case)
		outfile = str("output%d.txt" % case)
		
		# 0. Read file for words input
		with open(infile, 'r') as fin:
			words0 = [word for word in fin.readline().split()]
		
			print("0", " ".join(words0))
			
			# 1. Resolve joins
			JOINS = {word1+word2:(word1,word2) for word1 in PLSO for word2 in PLSO}
			words1 = []
			i = 0
			while i < len(words0):
				if words0[i] in JOINS:
					words1.append(JOINS[words0[i]][0])
					words1.append(JOINS[words0[i]][1])
					i += 1
				else:
					words1.append(words0[i])
					i += 1
			
			print("1", " ".join(words1))
			
			# 2. Resolve splits
			words2 = []
			i = 0
			while i < len(words1)-1:
				if words1[i]+words1[i+1] in PLSO:
					words2.append(words1[i]+words1[i+1])
					i += 2
				elif i == (len(words1) - 2):
					words2.append(words1[-2])
					words2.append(words1[-1])
					break
				else:
					words2.append(words1[i])
					i += 1

					
			print("2", " ".join(words2))
					
			# 3. Resolve insert/deletes
			words3 = []
			for word in words2:
				for pword in PLSO:
					if isPLSO(word, pword):
						words3.append(pword)
						break
				else:
					words3.append(word)
			
			print("3", " ".join(words3))
			
			# 4. Resolve transposes
			words4 = []
			for word in words3:
				if word in PLSO:
					words4.append(word)
					continue
				for i in range(len(word)-1):
					tword = word[:i] + word[i+1] + word[i] + word[i+2:]
					if tword in PLSO:
						words4.append(tword)
						break
				else:
					words4.append(word)
				
			print("4", " ".join(words4))
			
			# 5. Resolve garbles, print final message
			words5 = []
			for word in words4:
				if word in PLSO:
					words5.append(word)
				else:
					words5.append("????")
			
			print("5", " ".join(words5))
			with open(outfile, 'w') as fout:
				fout.write(" ".join(words5))