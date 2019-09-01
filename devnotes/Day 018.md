# Day 18

First day back to working. 

Started working on the Megacube structure and its optimizing. It's a little confusing to talk about cubes when you're inside a cube, but I think I was mostly clear on the terminology. 

## Goals
- Finish Megacube Sceneobject optimizations. 
- Start working on move animations. 
- Work on agents for solving the cube, and attach the agents to each scene. 

## Fixes/Updates
- Realized that there is no reason to put everything into a priority queue and then back into a list, only to loop over the list again. Instead, the process can be broken down into inserting into a PQ and extracting and drawing in that order. This saves a constant addition of O(n) time. This needs to be updated for polyhedron as well. 
- Created Megacube class outline and implemented trivial methods (getAVgDist and applyTransform)


## TODO for next time
- Finish megacube
- Start animations
- Update updateDrawable for Polyhedron 

## Notes:
- Much of the similarity between RubicksScene3D and MegaCube object has been noted, though I think that the encapsulation is necessary and can lead me to some cool things in the future. The main concern is that handling animations might be a bit of a pain and require too much redudant information to be stored. THough I suppose that no scene will contain more than one mega-cube as all rotations use matrix operations, and those require that the cube be centered about the origin. 

- The optimizations I found can be generalized to a class of objects which contain and manage other sceneObjects. The most base sceneObject being a Polygon3D and everything which utilizes this a "manager"