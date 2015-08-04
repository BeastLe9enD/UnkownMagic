package de.xthelegend.unkownmagic.common.blocks;


import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.xthelegend.unkownmagic.MainMod;
import de.xthelegend.unkownmagic.reference.Reference;

public class BlockEssenceCrop extends BlockCrops implements IGrowable {

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockEssenceCrop() {

        this.setTickRandomly(true);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
        this.setBlockName("essence_crop");
        this.setBlockTextureName(Reference.MODID + ":" + "essence_crop");
    }

    

    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        int l = world.getBlockMetadata(x, y, z);
        if (l <= 2) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
        } else if (l <= 4) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        } else if (l <= 6) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {

        if (world.getBlock(x, y, z) instanceof BlockEssenceCrop) {
            if (world.getBlockMetadata(x, y, z) == 8) {
                world.setBlockMetadataWithNotify(x, y - 1, z, 5, 2);
            }
        }
    }

    
    @Override
    protected boolean canPlaceBlockOn(Block block) {

        return block == Blocks.farmland;
    }

   
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int meta = world.getBlockMetadata(x, y, z);

            if ((meta < 7) && (world.getBlock(x, y -1, z) instanceof BlockFarmland)) {
                if (random.nextInt(30) == 0) {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
                }
            }
            if ((meta == 7) && (world.getBlock(x, y - 1, z) instanceof BlockFarmland) && (world.getBlock(x, y + 1, z) instanceof BlockAir)) {
                world.setBlock(x, y + 1, z, MainMod.BlockEssenceCrop, 8, 2);
            }
            // If the bottom somehow becomes fully grown, correct it
            if ((meta > 7) && (world.getBlock(x, y - 1, z) instanceof BlockFarmland)) {
                world.setBlockMetadataWithNotify(x, y, z, 7, 2);
                world.setBlock(x, y + 1, z, MainMod.BlockEssenceCrop, 8, 2);
            }

        }
    }

 
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {

        if (meta < 0 || meta > 8) {
            meta = 8;
        }

        return this.iconArray[meta];
    }

    
    @Override
    public int getRenderType() {

        return 1;
    }

   
    @Override
    protected Item func_149866_i() {

        return MainMod.essenceDust;
    }

 
    @Override
    protected Item func_149865_P() {

    	return MainMod.essenceSeeds;
    }

  
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_) {

        super.dropBlockAsItemWithChance(world, x, y, z, p_149690_5_, p_149690_6_, 0);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int p_149650_3_) {

        return meta == 8 ? this.func_149865_P() : this.func_149866_i();
    }

 
    @Override
    public int quantityDropped(Random random) {

        return 1;
    }

 
    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean isClient) {

        return world.getBlockMetadata(x, y, z) < 7;
    }

  
    @Override
    public boolean func_149852_a(World world, Random random, int x, int y, int z) {

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {

        return this.func_149866_i();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

        this.iconArray = new IIcon[9];

        for (int i = 0; i < this.iconArray.length; ++i) {
            int tex = 0;
            if (i == 0 || i == 1) { tex = 0; }
            else if (i == 2) { tex = 1; }
            else if ((i == 3) || (i == 4)) { tex = 2; }
            else if ((i == 5) || (i == 6)) { tex = 3; }
            else if (i == 7) { tex = 4; }
            else if (i == 8) { tex = 5; }

            this.iconArray[i] = iconRegister.registerIcon(this.getTextureName() + "_stage_" + tex);
        }
    }


    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {

        int meta = world.getBlockMetadata(x, y, z);
        if (world.getBlock(x, y + 1, z) instanceof BlockAir && (meta < 7) && !(world.getBlock(x, y - 1, z) instanceof BlockEssenceCrop)) {
            meta = meta + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
            if (meta > 6) {
                world.setBlockMetadataWithNotify(x, y, z, 7, 2);
                world.setBlock(x, y + 1, z, MainMod.BlockEssenceCrop, 8, 2);
            } else {
                world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if (metadata >= 8) {
            for (int i = 0; i < 3 + fortune; ++i) {
                if (world.rand.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(this.func_149865_P(), 1, 0));
                }
            }
            if (world.rand.nextBoolean()) {
                ret.add(new ItemStack(this.func_149866_i(), 1, 0));
            }
        } else if (metadata == 7) {
            ret.add(new ItemStack(this.func_149866_i(), 1 + world.rand.nextInt(2), 0));
        } else {
            ret.add(new ItemStack(this.func_149866_i(), 1, 0));
        }
        return ret;
    }

 
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {

        return super.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y + 1, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

        super.onNeighborBlockChange(world, x, y, z, block);
        if ((world.getBlockMetadata(x, y, z) == 7) && (world.getBlock(x, y + 1, z) == Blocks.air)) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if ((world.getBlockMetadata(x, y , z) == 8) && (world.getBlockMetadata(x, y - 1, z) != 7)) {
            world.setBlockToAir(x, y, z);
        }
        if ((world.getBlockMetadata(x, y, z) < 8) && (world.getBlock(x, y - 1, z) instanceof BlockEssenceCrop)) {
            world.setBlockToAir(x, y, z);
        }
        this.checkAndDropBlock(world, x, y, z);
    }


    @Override
    protected void checkAndDropBlock(World world, int x, int y, int z) {

        if (!this.canBlockStay(world, x, y, z)) {
            int l = world.getBlockMetadata(x, y, z);
            this.dropBlockAsItem(world, x, y, z, l, 0);
            world.setBlock(x, y, z, Blocks.air, 0, 2);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {

        if (world.getBlock(x, y, z) != this) return super.canBlockStay(world, x, y, z);
        if (world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) && world.getBlock(x, y - 1, z).isFertile(world, x, y - 1, z)) {
            return true;
        }
        return false;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {

    	return EnumPlantType.Crop;
    }
}