package mcjty.rftoolscontrol.modules.processor.items;

import mcjty.lib.builder.TooltipBuilder;
import mcjty.lib.tooltips.ITooltipSettings;
import mcjty.rftoolscontrol.RFToolsControl;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.List;

import static mcjty.lib.builder.TooltipBuilder.header;

public class GraphicsCardItem extends Item implements ITooltipSettings {

    private final Lazy<TooltipBuilder> tooltipBuilder = () -> new TooltipBuilder().info(header());

    public GraphicsCardItem() {
        super(new Properties()
                .maxStackSize(1)
                .group(RFToolsControl.setup.getTab()));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, worldIn, list, flag);
        tooltipBuilder.get().makeTooltip(getRegistryName(), stack, list, flag);
    }
}
