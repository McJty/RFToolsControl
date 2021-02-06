package mcjty.rftoolscontrol.modules.processor.items;

import mcjty.lib.builder.TooltipBuilder;
import mcjty.lib.tooltips.ITooltipSettings;
import mcjty.rftoolscontrol.RFToolsControl;
import mcjty.rftoolscontrol.setup.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.List;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.parameter;

public class CPUCoreItem extends Item implements ITooltipSettings {

    private final int tier;

    private final Lazy<TooltipBuilder> tooltipBuilder = () -> new TooltipBuilder()
            .info(header(),
                    parameter("speed", stack -> Integer.toString(Config.coreSpeed[getTier()].get())),
                    parameter("power", stack -> Integer.toString(Config.coreRFPerTick[getTier()].get())));

    public CPUCoreItem(int tier) {
        super(new Properties()
                .maxStackSize(1)
                .group(RFToolsControl.setup.getTab()));
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, worldIn, list, flag);
        tooltipBuilder.get().makeTooltip(getRegistryName(), stack, list, flag);
    }
}
