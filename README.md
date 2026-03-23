# Project 2: Non-deterministic Finite Automata

* Author: Randy Bauer, Oliver Hill
* Class: CS361 Section 001
* Semester: Spring 2026

## Overview

This program defines and implements a non-deterministic finite automaton (NFA) in Java. 
The NFA class allows users to build an NFA and simulate its behavior on given input strings, doing so by computing 
the eClosures via a depth-first search, determining whether a string is accepted, tracking how many simultaneous states 
exist during execution, and checking whether the NFA behaves like a DFA.

## Reflection

Randy:

I felt that this project went a little quicker than p1. Many of the fundamental method implementations for the NFA were identical to the DFA. 
Reading through the interface javadocs and referencing some of the previous project methods helped that part move quickly. Oliver took care of the 
maxCopies() method which set the framework for me to complete the accepts() method later. Admittedly, I was confused on the purpose of maxCopies() and had 
to take several passes and write out some string traces to grasp it. However, it proved quite useful for the accepts() implementation since the traversal behavior very similar.
I now feel like I have a better understanding of the expressive power differences between DFA's and NFA's, particularly the the ability of NFA's to branch into multiple states.

**Note:** We suspect a bad test line in test3_2(). One of the assertEquals() args calls a toStates() method which was not part of either interface. After running through that NFA instance on paper and checking off tests manually, we were able to discern the intent behind the test and create a substitution for the bad test line that tests the same behavior.

Oliver:

With a (hopefully correct) implementation of a DFA from P1, this project seemed to go by a lot quicker than P1 took to understand. As Randy stated above,
most of the fundamental methods were identical and could be implemented the same. I took care of MaxCopies(), the transition methods, eclosure, and state getters
to help with the test driven development when we understood what the tests were trying to accomplish. Even most of these methods were pretty simple, other than moving the
getTransition and addTransition methods to the NFAState.java class instead of having them implemented inside of the existing NFA.java class. The interface javadocs 
were quite easy to interpret, but with careful review and time over the break I was able to allot a couple of hours to understanding how the code is supposed to be working based on the 
given tests (other than test 3_2). I made sure to handle maxCopies(), in which Randy was able to handle the accepts() method based on my logic from maxCopies().

Overall, I do think this project was interesting, and fundamental to our interpretation of exactly how an NFA is different from a DFA, having completed both projects now without
a large issue at all.


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
