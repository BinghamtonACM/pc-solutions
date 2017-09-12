#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <sstream>
#include <cassert>
#include <stack>
#include <unordered_map>

using namespace std;

unordered_map<string, string> single = {
	{"zero", "0"}, {"one", "1"}, {"two", "2"}, {"three", "3"}, {"four", "4"},
	{"five", "5"}, {"six", "6"}, {"seven", "7"}, {"eight", "8"}, {"nine", "9"},
	{"ten", "10"}, {"eleven", "11"}, {"twelve", "12"}, {"thirteen", "13"}, 
	{"fourteen", "14"}, {"fifteen", "15"}, {"sixteen", "16"}, {"seventeen", "17"},
	{"eighteen", "18"}, {"nineteen", "19"}
};
unordered_map<string, string> binary = {
	{"twenty", "2"}, {"thirty", "3"}, {"forty", "4"}, {"fifty", "5"}, 
	{"sixty", "6"}, {"seventy", "7"}, {"eighty", "8"}, {"ninety", "9"}
};
unordered_map<string, string> operation = {
	{"plus", "+"}, {"minus", "-"}, {"times", "*"}, {"divided", "/"}
};
//operator precedence
unordered_map<string, int> op_prec = {
	{"+", 0}, {"-", 0}, {"*", 1}, {"/", 1}
};
stack<string> token;
stack<float> nums;
void convert(string &input, string &expression) {

	//logic to manually convert the text into a mathematical expression
	//I realize now that I could have gone straight from the input to polish notation...
	while(cin >> input) {
		if(single.find(input) != single.end()) {
			//if a number from the "single" map appears
			expression += single[input] + " ";
		} else if(binary.find(input) != binary.end()) {
			//else if a number from the "binary" map appears then 
			//append to our expression the first digit of the #
			expression += binary[input];
			cin >> input;
			if(single.find(input) != single.end()) {
				//append the 2nd digit of the # if it does not end in zero
				expression += single[input] + " ";
			} else if(operation.find(input) != operation.end()) {
				//else if it ends in zero then get the next symbol,
				//which must be an operation	
				expression += "0 ";
				unordered_map<string, string>::iterator it = operation.find(input);
				if((*it).first == "divided") {
					expression += "/ ";
					//we do a seperate case for divided to
					//get rid of the "by" part of "divided by"
					cin >> input;
				} else {
					//append to expression the corresponding math operation
					expression += operation[input] + " ";
				}
			}
		} else if(operation.find(input) != operation.end()) {	
			unordered_map<string, string>::iterator it = operation.find(input);
			if((*it).first == "divided") {
				expression += "/ ";
				cin >> input;
			} else {
				expression += operation[input] + " ";
			}
		} else {
			//error
		}
	}
}
void pref(string &expression, string &prefix) {
	//shunting-yard implementation

	//each operation has a (right or left) associativity 
	//for this problem, + - * / are all left associative
	//We keep an output string and an operator stack

	istringstream iss(expression);
	string temp;

	//we read through the entire string and handle appropriately:
	while(iss >> temp) {
		//if we encounter a token
		if(op_prec.find(temp) != op_prec.end()) {	//token 
			//if our token stack is not empty
			//and the token's (o1) precedence is less than or equal to 
			//the top of the stack's (o2), then first pop o2 off the  
			//stack and append it to our prefix. Then push our o1 
			//onto the stack
			if(!token.empty() && (op_prec[temp] <= op_prec[token.top()])) {
				prefix = prefix + token.top() + " ";
				token.pop();
			}
			token.push(temp);
		} else {	//number
			//if we encounter a number, then append it to our prefix expression
			prefix = prefix + temp + " ";
		}
	}
	while(!token.empty()) {
		prefix = prefix + token.top() + " ";
		token.pop();
	}
}
int compute() {
	return 0;
}
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */  
	string input, expression = "", prefix = ""; 
	convert(input, expression);
	//cout << expression << endl;
	pref(expression, prefix);
	//cout << prefix << endl;
	istringstream polish(prefix);
	float answer = 0;
	string temp = "";
	while(polish >> temp) {
		//push #s from our expression onto the stack
		//if we encounter a math operation from our expression
		//then pop the top 2 #s from the stack
		//perfrom the operation and then push it back
		//onto the stack. The final # left on the stack
		//is the answer to the entire expression.
		if(op_prec.find(temp) != op_prec.end()) {	//token
			float num2 = nums.top();
			nums.pop();
			float num1 = nums.top();
			nums.pop();
			unordered_map<string, int>::iterator it = op_prec.find(temp);
			if((*it).first == "+") {
				nums.push(num1 + num2);
			} else if((*it).first == "-") {
				nums.push(num1 - num2);
			} else if((*it).first == "*") {
				nums.push(num1 * num2);
			} else if((*it).first == "/") {
				nums.push(num1 / num2);
			}
		} else {	//number
			int num = stoi(temp);
			nums.push(num);
		}
	}
	answer = nums.top();
	cout << (int)answer << endl;
    return 0;
}









