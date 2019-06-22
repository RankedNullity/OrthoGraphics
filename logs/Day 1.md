# Day 1 

## Goals From Last Time
- [ ] Pick a JDK
- [ ] Install and build with JOGL
- [ ] Finish Cube Formulation
- [ ] FInish ML Library
- [ ] Add necessary data structures for AST (for layers in the ML structure)
- [ ] Finish necessary linalg operators (Matrix Multiplication, inverse, etc)


## Goals for Today
- Finish the cube representation.
- Start on Matrix Multiplication if I have time.

## General Log
Day 0 ended, and much of the logistics and initial beginnings went by smoothly. I still haven't picked a JDK, or installed a JOGL version, but I think I'll do that after I get the rest of everything working. 


## Cube Formulation:
In the formulation for the cube, I realized that I was envisioning the move (face, slice, clockwise) as directly facing into the cube. I.e. ("Front", 2, True) on a 3x3 cube would be equivalent to (R) in the traditional sense. However, I realized that this does not encompass all of the possible moves. To remedy this, I decided to change the move to refer to the slice parallel to the face. So, ("Front", 0, True) refers to the action which turns the front face clockwise. On a 3x3, this would be (F).  ("Front", 1, True) would refer to the action which turns the slice just behind front face clockwise, and so forth. This is in order to account for the fact that the moves on a 3x3 in the classic formulation, (F, R, L, U, D, B) and their respective counterclockwise turns, while sufficient on a 2x2, are not easily generalized to higher dimensions. Therefore, to allow for generality, I will be adopting this ("Face", slice, clockwise) metric instead of the classic. 

One issue that does arise when doing this however, is the issue of orientation. In the 3x3 cube, the cube can be represented such that the middle sticker of each face doesn't have to be represented because those stickers will be permanently in the same place, and the orientation of those stickers will determine the unique goal state of the cube. In even dimensions, there is no immediately obvious equivalent simplification to this, and in odd dimensions, there is no clear answer on whether or not it is beneficial to fix the middle of each cube. 

I think that it is best for generalization's sake to allow movement of the middle. While this results in 24 times the state space, and 24 goal states, it results in easier generalization of smaller to higher dimensional cubes. (particularly in the fact that you can generalize even to odd number cubes and vice versa now)

### Formulation details
For all cubes (except the 1x1 which I will not count), there are 8 edge pieces. Each edge piece has 3 possible orientations. This is a 8 x 24 matrix.

For each edge piece, there are 12 possible locations, with 2 possible orientations. 
12 x 24. 

Not entirely sure if the above is true. There might be the case for large enough cube that there are 24 possible locations. Going to start working on the full-sticker representation to test this. 



## TODO
- Look up weight pruning algorithms for trained networks.
- Read the paper on training reduced networks.
