# JavaSkipList
This is my own implementation of a skip list in Java used for a project in my Data Structures class.

The main file includes a simple example showing how the skip list would look with a few sample values in them. The way I created this implementation was by first creating a "SkipListEntry" which is essentially a simple mode or key-value pair.
Each level of the skip list is simply an arrayList from the java.util package, which will allow for both variable size in each level while saving some memory and ease of removal and addition to each level. 

The way the intial skip list is generated is by first creating a base level from all the nodes to be included, then the levels after the base are created based on the level directly below it and using java's Random class to choose if a value should be promoted to the next level of the skip list or not.
