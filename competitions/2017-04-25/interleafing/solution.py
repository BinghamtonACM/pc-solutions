#!/usr/bin/env python3

"""
File: solution.py
Author: Matthew Cole
Date: 4/4/17
"""
import operator

def interleave(n, iterable):
	"""
	Interleave items from iterable into n lists.
	"""
	# Make n empty lists
	output = [list() for _ in range(n)]
	
	# Interleave/Distribute
	for group,item in enumerate(iterable):
		group = group % n
		name, available = item
		output[group].append(name)
		
	return output

if __name__ == "__main__":
	
	#Process inputs
	n = int(input())
	names = [name for name in input().split()]
	line = input().split()
	vegetables = list(zip((v for v in line[0::2]),(int(a) for a in line[1::2])))
	
	#Determine available vegetables
	is_available = operator.itemgetter(1)
	available = list(filter(is_available, vegetables))
	
	#Deinterleaving, assigning vegetables to name
	assigned = list(zip(names,interleave(n, available)))
	
	#Print output
	for name,vegs in sorted(assigned):
		print(name, " ".join(sorted(vegs)))