# Project 2: Non-deterministic Finite Automata

* Author: Randy Bauer, Oliver Hill
* Class: CS361 Section 001
* Semester: Spring 2026

## Overview

This program defines and implements a non-deterministic finite automaton (NFA) in Java. The NFA class allows users to build an NFA and simulate its behavior on given input strings, doing so by computing the eClosures via a depth-first search, determining whether a string is accepted, tracking how many simultaneous states exist during execution, and checking whether the NFA behaves like a DFA.

## Reflection

Randy:

I felt that this project went a little quicker that p1. Many of the fundamental methods implementations for the NFA were identical to the DFA. Reading through the interface javadocs and referencing some of the previous project methods helped that part move quickly.

Oliver took care of the maxCopies() method which set the framework for me to complete the accepts() method later. Admittedly, I was confused on the purpose of maxCopies() and had to take several passes and write out some string traces to grasp it. However, it proved quite useful for the accepts() implementation since the traversal behavior very similar.

I now feel like I have a better understanding of the expressive power differences between DFA's and NFA's, particularly the the ability of NFA's to branch into multiple states.

Oliver:



## Compiling and Running

Local (JUnit 4):

javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" fa/*.java fa/nfa/*.java test/nfa/*.java

java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore test.nfa.NFATest

Onyx:

[you@onyx]$ javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java

[you@onyx]$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.nfa.NFATest

## Results

Results from our project return passing results from each test given using the JUnit 4 tester.

## Sources used

- Project 1 files and Project 2 instructions
- Java Collections Framework documentation
- Lecture notes