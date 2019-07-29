# Day 12 

## Graphics to do:
0. Change the formulation to have a standard view vector and plane basis vectors, and transform those when necessary. 
1. Fix SceneCube so each of the faces aligns with the ordered convention we use in Cube.
	- With this we are adopting the following convention:
		- Front: largest x, varies in y,z 
		- Left: smallest y, varies in x,z
		- Up: largest z, varies in y,z 
		- Bottom: smallest z, varies in y,z 
		- Right: largest y, varies in x,z
		- Back: smallest x, varies in y,z 
2. Write a method in Scene3D which generates the sceneCubes necessary to form the rubicks cube. 
	- Figure out how to store this information to allow for simple transforms and updates. 
		- Change SceneCube to not allow for general transforms but only rotations about x,y, or z axis. This allows us to update each face to be the correct one. 
3. Figure out how to draw a 3D polygon onto the screen.
	- Find a way for framing the view. 
		- Since we know that the only thing we want to render is a cube, we can limit the camera to be in a spherical track around the cube. 
			This allows us to simply calculations quite a bit. 
		- Maybe implement a zooming feature with the scroll wheel? but this seems unnecessary for our purposes. 
		- Otherwise camera controls will be rotations along this. 
4. Implement a control system for the cube, which correctly alters the Scene and the cube. 




To control cube:
- Keep the colors the same when animating the cube, while physically changing the cube.
- After animation is done, change the pointers in sceneCube array to be at the correct position in the scene. cube(0,0,0) always points to the top left cube, etc. Then use the gameCube to color the faces of the sceneCubes.