Problem Statement
=================
The intent of the problem is to consume input data, apply business rules, then generate output. As it turns out I/O and transformation is a majority of what computers do.

Requirements
============
Produce a program that consumes the input files and produce outputs according to specification. The requirements are:

1. Takes two program arguments: `pathToDirectory`, `outputFile`
2. The `pathToDirectory` consists of two files: `input1.csv`, `input2.json`
3. Writes output to the specified `outputFile` 

The `outputFile` must be a text file with one JSON object per line. Each JSON object, when pretty-printed, should look like the following:
```
{
  "collectionId": "input1.csv",
  "sites": [
    {
      "id": "12344",
      "name": "example.com",
      "mobile": 1,
      "keywords": "sports,tennis,recreation",
      "score": 117.49
    },
    {
      "id": "12345",
      "name": "example.jp",
      "mobile": 1,
      "keywords": "japan,travel",
      "score": 38
    }
  ]
}
```
Assume the keywords property is populated via a function that takes a "site" object and return a comma-delimited string. In this example, you may use the `KeywordService` interface as the specification for resolving keywords.

Keep the Following In Mind
--------------------------
1. You have as much time as you need to finish. We understand people have busy lives and appreciate you taking the time to work on this.
2. The code does not need to be perfect, but it needs to be: 
    - Properly organized and documented.
    - Runnable from the command line of a Unix-like system.
3. You can use any programming language, though JVM based language is preferred.
4. You can use free open source libraries to parse and output data. We're not crazy - you're not expected to write a JSON parser.
5. You can use libraries as appropriate, but we ask you to avoid libraries that performs the bulk of the excercise.
6. Document how to run the program in a file named RUNME.txt 
    - Assume a Unix-like OS such as Linux, BSD, or OS X
    - The exact commands used to build the program
    - The exact commands used to run unit tests
    - The exact commands used to execute the program
7. Be prepared to discuss your solution, choices, and tradeoffs.
