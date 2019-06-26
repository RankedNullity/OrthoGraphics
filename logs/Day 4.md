# Day 4

Working on move turns. Trying to find a invariant for the cube for easy move programming.

Found the following: (writing the adjacent faces in clockwise order)

Front : Left, Top, Right, Bottom
0     : 1   ,  2 ,   4  ,   3

Left  : Front, Bottom, Back, Up
1     : 0    ,    3  ,  5  , 2

Here I realize that I can move Up to the front of the list (The list must be circular mod 4)

Left  : Up, Front, Bottom, Back
1     : 2 ,   0  ,    3  ,  5  

Here my active hypothesis is that the first two ints in the list are the next 2 items in the list (mod 3) and then the next 2 after that is just 5 - list[i] for the first two. (this should make sense since these are the opposite faces of the first 2 in that order). 

Here we try that for the next one:
 
**Predicted **

2 : 0, 1, 5, 4
This translates to:

Up: Front, Left, Back, Right 

This works!

So when given an action (int face, int slice, boolean clockwise) we can do the following:

int[] adjFaces = { face + 1 % 3, face + 2 % 3, 5 - adjFaces[0], 5 - adjFaces[1] }


### Interim: Figuring out how the faces are orientated relative in each other. 
Trying to see how the indexing would work with certain orientations.

After doing the indexing for some of the faces in various directions, I found the following: 
- I tried all orientated the same direction within the T-shape expansion (unfolding the cube) and front and back being the same orientation
	- Every face has its own index rotations and there are no clear patterns in the indexing
	- 
-

Now, I want to see if there is a consistent rotation for the cubes to get them to align to the second attempt, (because the second one is easy to manage)
from any face. There is a very easy way to have cheap rotations, as I can simply put a number {0 , 1 ,2, 3}  representing the number of clockwise quarter turns, and simply change the get and size method depending on the value of this field. Very inexpensive. However, this does up the level of complication, so I'd avoid this if possible. 

Now to see if I can find such a rotation.

_... Mathing ..._

~~WE DID IT! I found that the number of clockwise quarter turns to transform the first orientation into the second is simply {0,1,2,3} or some rotation of that. 
Now I have to check whether or not this fits with the current ordering of the faces, and how to correspond the number of times to rotate it to its position in the ordering. ~~

Oops I just realized that the first representation is the same, but the face not requiring rotations becomes up instead of bottom.
So now,
-------------------------------------------------

if face != UP, LEFT, or FRONT:
	get the action that is equivalent to the action from the other side of the cube
	use that action as the action to use.
		

if  face == UP:
	Do no rotations
elif face == LEFT:
	Rotate the first 3, 3, and the last 1 1. 
elif face == FRONT:
	Rotate [1, 2 ,3, 0] 
	

if slice == 0
	rotate the front face the correct direction
elif slice == cube.size - 1
	rotate the back face the opposite direction
		
*Here I realized that to rotate an entire outside face efficiently, I will have to implement that no matter what.*

Finished implementing rotations. Need to test now.

## GLOBAL TODO 
1. Finish FullStickerCube
	- Write Moves
		- Write Matrix Rotations implementation inside Matrix class to allow for cheap rotations of existing matrices. 
			- Fix get method
			- Fix get size methods 
		- ~~Fix getColorArray for FullStickerCube~~ getColorArray uses matrix.get(j, k), which means once that is updated, everything will work fine
2. Finish Graphics
3. Test Moving the cube after graphics is done.
2. Test the cube for minimal one hot positions, and build/test that.
3. Write and test matrix multiplication.
4. Write and test ML system.



	


