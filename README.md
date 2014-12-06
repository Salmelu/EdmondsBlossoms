# Edmonds
## Introduction
This little project is an implementation of Edmonds' blossom algorithm. It finds a maximum matching in graph G by finding augmenting paths and augmenting an already existing matching. 

## Purpose
It was created as a university project and is meant for education purposes. You can reuse it in any way you wish, but there is no warranty or support of any kind.

## Compatibility
This project was written under Eclipse in Java 8, version 1.8.05. It uses some of the Java 8 functions, therefore it isn't compatible with earlier versions.

## License
Copyright (c) 2014 Michal Staruch (Salmelu)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 

## Usage
```
java -jar Edmonds.jar [-n] file1 file2 ... <br/>
-n	if specified, program won't create .dot output files <br/>
```
Files are parsed by a simple parser which doesn't handle any errors in the input file. Input file should have a format of
- a list of vertices on the first line, separated by comma
- each edge on another following line

```
Example input:
a,b,c 
a --- b 
b --- c 
c --- a 

Example output:
Matching:
c --- a
```
