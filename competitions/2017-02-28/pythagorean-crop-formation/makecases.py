#!/usr/bin/env python3

"""
File: MakeCases.py
Author: Matthew Cole
Date: 2/23/17
"""
import random

if __name__ == "__main__":
	
	MAX_SUM = 2000000
	CASES = 4
	
	random.seed()
	
	# Find all primitives less than MAX_SUM
	#Root node for tree of primitive pythagorean triples,
	#implemented as an array of triples
	triples = [(3,4,5)]
	i = 0

	#Generate a tree of primitive triples
	#Terminating function is based on filling the tree with all triples
	#whose sum is less than the MAX_SUM. All children of a given node are guaranteed
	#to have sums larger than their parent because of the Berggren transformations.
	while(i < len(triples)):
		#Get the next triple
		(a,b,c) = triples[i]
		
		#Perform Berggren's transformation on the triple
		t1 = (a - 2*b + 2*c, 2*a - b + 2*c, 2*a - 2*b + 3*c)
		t2 = (a + 2*b + 2*c, 2*a + b + 2*c, 2*a + 2*b + 3*c)
		t3 = (-a + 2*b + 2*c, -2*a + b + 2*c, -2*a + 2*b + 3*c)
		
		#Add the transformed triples to the list of triples and triple sums
		if sum(t1) <= MAX_SUM: triples.append(t1)
		if sum(t2) <= MAX_SUM: triples.append(t2)
		if sum(t3) <= MAX_SUM: triples.append(t3)
		
		#Increment the index
		i += 1
	
	#Generate basic case
	case = 0
	infile = str("input%i.txt" % case)
	outfile = str("output%i.txt" % case)
	n = 2
	case_triples = [(3,4,5),(10,24,26)]
	
	print(n)
	print(case_triples)
	
	with open(infile, 'w') as fout:
		fout.write(str(n) + "\n")
		for ct in case_triples:
			fout.write(str(sum(ct)))
			fout.write('\n')
	
	with open(outfile,'w') as fout:
		for ct in case_triples:
			fout.write(" ".join(str(side) for side in ct))
			fout.write('\n')
	
	#Generate n-1 test cases
	for case in range(1,CASES-1):
		infile = str("input%i.txt" % case)
		outfile = str("output%i.txt" % case)
		
		# Select number of triangles in this case
		if n < 9:
			n = random.randrange(n+1,10)
		
		# Generate case triples. Multiply by a random coefficient so that
		# not all triples are primitive 
		case_triples = list()
		for ct in range(n):
			t = random.randrange(0,len(triples)-1)
			case_triples.append(tuple(side*random.randrange(1,max(2,MAX_SUM // sum(triples[t]) - 1)) 
				for side in triples[t]))
		
		print(n)
		print(case_triples)
		
		# Write test cases to files
		with open(infile, 'w') as fout:
			fout.write(str(n) + "\n")
			for ct in case_triples:
				fout.write(str(sum(ct)))
				fout.write('\n')
		
		with open(outfile,'w') as fout:
			for ct in case_triples:
				fout.write(" ".join(str(side) for side in ct))
				fout.write('\n')
	
	# Generate nth test case
	case = CASES-1
	infile = str("input%i.txt" % case)
	outfile = str("output%i.txt" % case)
	
	# Select number of triangles in this case
	n = 10
	
	# Generate case triples. Multiply by a random coefficient so that
	# not all triples are primitive 
	case_triples = list()
	for ct in range(n):
		t = random.randrange(0,len(triples)-1)
		case_triples.append(tuple(side*random.randrange(1,max(2,MAX_SUM // sum(triples[t]) - 1)) 
			for side in triples[t]))
	
	print(n)
	print(case_triples)
	
	# Write test cases to files
	with open(infile, 'w') as fout:
		fout.write(str(n) + "\n")
		for ct in case_triples:
			fout.write(str(sum(ct)))
			fout.write('\n')
	
	with open(outfile,'w') as fout:
		for ct in case_triples:
			fout.write(" ".join(str(side) for side in ct))
			fout.write('\n')

