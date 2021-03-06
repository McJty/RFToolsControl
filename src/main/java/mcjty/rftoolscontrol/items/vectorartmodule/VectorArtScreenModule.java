package mcjty.rftoolscontrol.items.vectorartmodule;

import mcjty.lib.varia.BlockPosTools;
import mcjty.lib.varia.WorldTools;
import mcjty.rftools.api.screens.IScreenDataHelper;
import mcjty.rftools.api.screens.IScreenModule;
import mcjty.rftoolscontrol.api.parameters.Tuple;
import mcjty.rftoolscontrol.blocks.ModBlocks;
import mcjty.rftoolscontrol.blocks.processor.ProcessorTileEntity;
import mcjty.rftoolscontrol.config.ConfigSetup;
import mcjty.rftoolscontrol.compat.rftoolssupport.ModuleDataVectorArt;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class VectorArtScreenModule implements IScreenModule<ModuleDataVectorArt> {
    private int dim = 0;
    private BlockPos coordinate = BlockPosTools.INVALID;

    @Override
    public ModuleDataVectorArt getData(IScreenDataHelper h, World worldObj, long millis) {
        World world = DimensionManager.getWorld(dim);
        if (world == null) {
            return null;
        }

        if (!WorldTools.chunkLoaded(world, coordinate)) {
            return null;
        }

        Block block = world.getBlockState(coordinate).getBlock();
        if (block != ModBlocks.processorBlock) {
            return null;
        }

        TileEntity te = world.getTileEntity(coordinate);
        if (te instanceof ProcessorTileEntity) {
            ProcessorTileEntity processor = (ProcessorTileEntity) te;
            return new ModuleDataVectorArt(processor.getGfxOps(), processor.getOrderedOps());
        }
        return null;
    }

    @Override
    public void setupFromNBT(NBTTagCompound tagCompound, int dim, BlockPos pos) {
        if (tagCompound != null) {
            coordinate = BlockPosTools.INVALID;
            if (tagCompound.hasKey("monitorx")) {
                if (tagCompound.hasKey("monitordim")) {
                    this.dim = tagCompound.getInteger("monitordim");
                } else {
                    // Compatibility reasons
                    this.dim = tagCompound.getInteger("dim");
                }
                if (dim == this.dim) {
                    BlockPos c = new BlockPos(tagCompound.getInteger("monitorx"), tagCompound.getInteger("monitory"), tagCompound.getInteger("monitorz"));
                    int dx = Math.abs(c.getX() - pos.getX());
                    int dy = Math.abs(c.getY() - pos.getY());
                    int dz = Math.abs(c.getZ() - pos.getZ());
                    if (dx <= 64 && dy <= 64 && dz <= 64) {
                        coordinate = c;
                    }
                }
            }
        }
    }

    @Override
    public int getRfPerTick() {
        return ConfigSetup.VECTORARTMODULE_RFPERTICK.get();
    }

    @Override
    public void mouseClick(World world, int x, int y, boolean clicked, EntityPlayer player) {
        int xoffset = 0;
        if (x >= xoffset) {
            if (coordinate.getY() != -1) {
                if (!WorldTools.chunkLoaded(world, coordinate)) {
                    return;
                }

                Block block = world.getBlockState(coordinate).getBlock();
                if (block != ModBlocks.processorBlock) {
                    return;
                }

                if (clicked) {
                    TileEntity te = world.getTileEntity(coordinate);
                    if (te instanceof ProcessorTileEntity) {
                        ProcessorTileEntity processor = (ProcessorTileEntity) te;
                        processor.signal(new Tuple(x, y+7));
                    }
                }
            } else if (player != null) {
                player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Module is not linked to a processor!"), false);
            }
        }
    }
}
