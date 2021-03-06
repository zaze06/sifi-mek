package me.alien.meksifi.mek;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class Provider implements ICapabilityProvider {
    public ItemStack stack;
    public int maxEnergy = 100;


    public final LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergyStorage);

    public Provider(ItemStack stack) {
        this.stack = stack;
    }


    private <T> T createEnergyStorage() {
        return (T) new Energy();
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        if (capability == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return LazyOptional.empty();
    }
}