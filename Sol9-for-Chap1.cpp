// Chap1-Basics.cpp : Defines the entry point for the console application.
// Author: PipYott

#include "stdafx.h"
#include <iostream>
using namespace std;

int Total=0;
// function that computes all integegers smaller than an n
int sumInf(int n){
	for (int i=n-1;i>=0;i--){
		if (i>=0) {
			Total=Total + i;
		}
	}
	return Total;
}

// main function
 int main()
{  
	int n;
	int result;
	cout << "Please enter an integer  ";
	cin >> n;
	result=sumInf(n);
	cout << "The sum of all the integers smaller than "<< n << " is : "<< result << endl;
	cin.ignore();
	cin.get();
	return 0;
}

