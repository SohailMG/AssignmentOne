# AssignmentOne
Java assignment consisting of three seperate tasks

#Task 1:-
The task focuses on using java to read and write to files.
given an encrypted data file the goal is to decrypt the file and write the results to another text file.
This file contains a passage of text that is encoded. The encoded data file uses a very simple cipher based on the encryption of the positions of vowels and 
consonants from the English alphabet.

for instance first line of data file contains the following string:
,,,
C12V1 C2 V5C23
,,,

The cipher works by substituting the relevant alphabet character for the position based on the tables
,,,
char[] vowels = {'a','A','e','E'}.....
,,,

During the encoding of a passage of text, a vowel is denoted by inserting the letter ‘V’ followed directly by its 
position index. For example, the uppercase letter ‘A’ is encoded as ‘V2’. Similarly, a consonant is encoded by 
inserting the letter ‘C’ followed directly by its position index. For example, the lowercase letter ‘h’ is encoded 
as ‘C11’.



