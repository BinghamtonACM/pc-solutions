#!/usr/bin/env python3

"""
File: solution.py
Author: Matthew Cole
Date: 3/1/17
"""

if __name__ == "__main__":
	import sys

	#Receive inputs, find the largest sum in the input cases
	num_cases = int(input())
	sums = [int(n) for n in sys.stdin]
	max_sum = max(sums)
	
	#Root node for tree of primitive pythagorean triples,
	#implemented as an array of tuple triples
	triples = [(3,4,5)]
	i = 0

	#Generate a tree of primitive triples
	#Terminating function is based on filling the tree with all triples
	#whose sum is less than the max_sum. 
	#All children of a given node are guaranteed to have sums larger 
	#than their parent because of the Berggren transformations.
	while(i < len(triples)):
		#Get the next triple
		(a,b,c) = triples[i]
		
		#Perform Berggren's transformation on the triple
		t1 = (a - 2*b + 2*c, 2*a - b + 2*c, 2*a - 2*b + 3*c)
		t2 = (a + 2*b + 2*c, 2*a + b + 2*c, 2*a + 2*b + 3*c)
		t3 = (-a + 2*b + 2*c, -2*a + b + 2*c, -2*a + 2*b + 3*c)
		
		#Add the transformed triples to the list of triples
		#if the child triple has a potentially useful sum
		if sum(t1) <= max_sum: triples.append(t1)
		if sum(t2) <= max_sum: triples.append(t2)
		if sum(t3) <= max_sum: triples.append(t3)
		
		#Increment the index
		i += 1

	#For each sum in the test case, 
	#Find a primitive triple which is similar in ratio 
	#to the right triangle with that sum.
	#Then print each side for the given right triangle
	for s in sums:
		for t in triples:
			if s % sum(t) == 0:
				k = int(s / sum(t))
				rtriangle = tuple(k*side for side in t)
				print(" ".join(str(side) for side in rtriangle))
