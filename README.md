# string-brackets-and-or

Select all string by ignoreing brackets,"AND" and "OR" and process on that string as your reqirenment. 

string-bracket-and-or, release 1.0 (Feb 2016)
----------------------------------------------

****INTRODUCTION****

This simple logic can help to use string while we have string with AND OR and () brackets.
so for that we can easily use string by splitting AND,OR,(,) but after use of string If we wanted to replace it as it as with proper brackets then it'll create confusion.

This library is developed with JEval to create JEval expression from normal query string to match with some message.


****EXAMPLE****

For Example we have one user define syntex query like: (((Microsoft AND Windows) OR Server) AND (2008 OR R2) OR Enterprise)

and we have message with full details of anyting. now we want to check that user query data is available in our message or not by creating JEval expression.

then we can get output like this to evalute in JEval:
(((indexOf('Enterprise','Microsoft',0)!=-1 && indexOf('Enterprise','Windows',0)!=-1) || indexOf('Enterprise','Server',0)!=-1) && (indexOf('Enterprise','2008',0)!=-1 || indexOf('Enterprise','R2',0)!=-1) || indexOf('Enterprise','Enterprise',0)!=-1)

This is the simplest way to use this logic, you also can customize it as you requires.
