# Day 6

## GLOBAL TODO 
1. Finish FullStickerCube
	- Write Moves
		
2. Finish Graphics
3. Test Moving the cube after graphics is done.
2. Test the cube for minimal one hot positions, and build/test that.
3. Write and test matrix multiplication.
4. Write and test ML system.

## Today's Progress
Going to finish Cube turning on the full sticker face. 
After talking to my friend and colleague about the writing the ML, we came to the conclusion that any library we write which doesn't use GPU programming or simd instructions will be lacking severely in computational speed. Considering that the paper took 44 hours to train on 3 Titan XP GPUS on a 32 Core Xenon E5-2560 server, doing a higher dimensional computation on a less optimized framework seems like an extremely bad idea. Therefore, I think it is best to let this project do something much simpler than the goal of "comparing high dimensional Deepcube vs. performance on cube reduction network and DeepCube on a smaller dimension." So I think the direction of this project is going to change to simply making a full generalizable Rubicks cube with graphics support, and try to solve it using traditional AI methods such as Q-learning, deep q learning, or double q-learning. 