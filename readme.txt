Student name: Fabio Vitalba
Lab: #3 - Frameworks

-------Activities completed-------
Basic Activity, Framework upgrade, Documentation

-------Problems and Incomplete Activities------- 
I am not sure if the "Framework upgrade" was implemented as required.
I cannot seem to find any real advantage in the current implementation of the upgrade compared to the GameRule implementation (except maybe that the upgrade allows to define listeners for all Tasks, instead of only class-specific ones).
Therefore, I added persistence to my GameConditions, which defines whether a condition is always active or only until it's true for the first time. (Meaning a Condition + Listener combo is only executed once if they are not persistent.)

-------AI Usage Declaration-------
I used ChatGPT *after* implementing the GameCondition/GamificationListener, to see if there were better/other ways to implement.
However, it did not give me any clues to what a better implementation could have been, so I kept my implementation.

-------Additional Comments-------
I created my documentation using the README.md file, since it allowed me to include mermaidjs-diagrams directly. I then used pandoc to help me create the pdf from it.
For better viewing I'd recommend looking at the README.md file, but it requires a mermaidjs-diagram-plugin for IntelliJ (and I assume Eclipse as well).
In the documentation I tell the user that they may look at the actual GamificationHelloWorld-module for examples, so I included this module as well.

-------Points claimed-------
Basic activity: 3
Framework Upgrade +2
Documentation: +1
