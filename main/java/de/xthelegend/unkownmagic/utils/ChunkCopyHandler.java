package de.xthelegend.unkownmagic.utils;

import net.minecraft.block.Block;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ChunkCopyHandler {
	public Block[] xPos = new Block[16 * 16];
	public Block[] zPos = new Block[256 * 256];
	
	public Block[] yPos = new Block[16 * 16];
	
	public Block currentBlock;
	
	public void getChunk(World world,int x, int y, int z)
	{
		for(int ix = 0; ix < 16; ix++)
		{
			xPos[ix] = world.getBlock(x + ix, y, z);
			if(ix == 16)
				ix = 0;
			
			
		}
		
		for(int iy = 0; iy < 256; iy++)
		{
			yPos[iy] = world.getBlock(x, y + iy, z);
		}
		
		for(int iz = 0; iz < 16; iz++)
		{
			zPos[iz] = world.getBlock(x, y , z + iz);
		}
		
	}
	
	public void CopyChunk(int x, int y, int z, int newX, int newY, int newZ)
	{
		
	}

}
