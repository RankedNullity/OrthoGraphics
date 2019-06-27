# Day 5

Realized that there is a slight problem with the Matrix class that we have. 
Currently multiplying a matrix which has been rotated is asymptotically worse than just having a deepcopy of the rotated matrix. 

Want to have quick access rotation functionality (which is not O(n^2)) for the cube, but want to have just the 
double matrix for multiplications. 

## Breakdown
Why I want really cheap rotations:
- Rotating a slice in the cube happens very often, and when it happens, I only need to access a certain row of the cube, and the remaining values can be left untouched. In this case, deep-copying the entire [face] matrix is overly expensive and doesn't need to be done.

Why having the cheap rotation implementation is bad vs. traditional:
- When traversing over the entire array (as for matrix multiplication), the index recalculations make the process needlessly longer. (an^2 opposed to n^2)


Solution:
- Use cheap rotation implementation for the cube when it turns, and get the deepcopied version whenever we use multiply. 

Problem:
- Converting the CRI (cheap rotation implementation) to traditional requires that an^2 iteration over the entire CRI matrix anyways. So this is only saves time if the matrix is used to multiply more than once. 

I dont know if this is the case? I think the best course of action is to make a Matrix interface and change the current implementations to their respectiveNames.