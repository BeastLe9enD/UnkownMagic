package de.xthelegend.unkownmagic.common.items;


import de.xthelegend.unkownmagic.MainMod;
import de.xthelegend.unkownmagic.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;



public class ItemEssenceSeed extends ItemSeeds implements IPlantable {

    public static Block BlockCrop;

    public ItemEssenceSeed(Block blockCrop, Block blockSoil) {

        super(blockCrop, blockSoil);
        BlockCrop = blockCrop;
        this.setCreativeTab(MainMod.tabUnkownMagic);
        this.setTextureName(Reference.MODID + ":" + "essence_seeds");
        setUnlocalizedName("essence:seed");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        if (side != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)
                    ) {
                world.setBlock(x, y + 1, z, BlockCrop, 0, 2);
                --itemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {

        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {

        return BlockCrop;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {

        return 0;
    }


}