# Day 0

First thing, approach the cube formulation. Two main ways of doing this.
## Minimal One Hot Encoding (MOHE) vs Full Sticker Color Encoding (FSCE)
- [ ] FSCE
	- Easy to graphically display
	- Easy to code
	- Computationally expensive
- [ ] MOHE
	- Harder to code
	- Least expensive
	- Hard to display
	
Will start off using only MOHE. If need be, will implement a MOHE -> FSFE method for use in graphics.

Realized that it is probably better to use a matrix for the internals of the rubicks cube, regardless of what encoding I decide on. So, postponing cube for now to work on linalg library. 

Downloaded JUnit 5.

Realize I need to use a JDK for JOGL anyway, so figuring out how to use that now. 

Downloaded OpenJDK 12.0.1

Built project with Gradle (not completely sure what this does yet)

Changed Project view to hierarchial. My sanity is restored.