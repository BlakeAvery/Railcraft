/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2018
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.modules;

import mods.railcraft.api.core.RailcraftModule;
import mods.railcraft.api.crafting.Crafters;
import mods.railcraft.api.crafting.IBlastFurnaceCrafter;
import mods.railcraft.api.crafting.IRockCrusherCrafter;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.aesthetics.brick.BrickTheme;
import mods.railcraft.common.blocks.aesthetics.brick.BrickVariant;
import mods.railcraft.common.blocks.aesthetics.generic.EnumGeneric;
import mods.railcraft.common.blocks.machine.equipment.EquipmentVariant;
import mods.railcraft.common.blocks.machine.worldspike.WorldspikeVariant;
import mods.railcraft.common.blocks.ore.EnumOre;
import mods.railcraft.common.blocks.ore.EnumOreMagic;
import mods.railcraft.common.carts.RailcraftCarts;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.items.ItemDust;
import mods.railcraft.common.items.Metal;
import mods.railcraft.common.items.ModItems;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.ic2.IC2Plugin;
import mods.railcraft.common.plugins.misc.Mod;
import mods.railcraft.common.util.crafting.*;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.misc.Code;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

@RailcraftModule(value = "railcraft:factory", description = "coke oven, blast furnace, rolling machine, rock crusher, etc...")
public class ModuleFactory extends RailcraftModulePayload {
    private static final int COKE_COOK_TIME = 1800;
    private static final int COKE_COOK_CREOSOTE = 500;

    public ModuleFactory() {
        setEnabledEventHandler(new ModuleEventHandler() {

            @Override
            public void construction() {
                add(
                        RailcraftBlocks.ANVIL_STEEL,
                        RailcraftBlocks.EQUIPMENT,
                        RailcraftItems.COKE,
                        RailcraftBlocks.COKE_OVEN,
                        RailcraftBlocks.COKE_OVEN_RED,
                        RailcraftBlocks.BLAST_FURNACE,
                        RailcraftBlocks.ROCK_CRUSHER,
                        RailcraftBlocks.STEAM_OVEN,
                        RailcraftBlocks.TANK_IRON_GAUGE,
                        RailcraftBlocks.TANK_IRON_VALVE,
                        RailcraftBlocks.TANK_IRON_WALL,
                        RailcraftBlocks.TANK_STEEL_GAUGE,
                        RailcraftBlocks.TANK_STEEL_VALVE,
                        RailcraftBlocks.TANK_STEEL_WALL,
                        RailcraftBlocks.TANK_WATER,
                        RailcraftBlocks.CHEST_METALS,
                        RailcraftCarts.CHEST_METALS
                );
                Code.setValue(Crafters.class, null, BlastFurnaceCrafter.INSTANCE, "blastFurnace");
                Code.setValue(Crafters.class, null, CokeOvenCrafter.INSTANCE, "cokeOven");
                Code.setValue(Crafters.class, null, RockCrusherCrafter.INSTANCE, "rockCrusher");
            }

            @Override
            public void init() {
                {
                    int smeltTime = IBlastFurnaceCrafter.SMELT_TIME;
                    Metal steel = Metal.STEEL;
                    IBlastFurnaceCrafter bf = Crafters.blastFurnace();
                    bf.addRecipe("railcraft:ingot_steel", Ingredient.fromItem(Items.IRON_INGOT), smeltTime,
                            RailcraftItems.INGOT.getStack(1, steel), 1);

                    bf.addRecipe("railcraft:smelt_helmet", Ingredient.fromItem(Items.IRON_HELMET), smeltTime * 5,
                            RailcraftItems.INGOT.getStack(5, steel), 5);
                    bf.addRecipe("railcraft:smelt_chestplate", Ingredient.fromItem(Items.IRON_CHESTPLATE), smeltTime * 8,
                            RailcraftItems.INGOT.getStack(8, steel), 8);
                    bf.addRecipe("railcraft:smelt_leggings", Ingredient.fromItem(Items.IRON_LEGGINGS), smeltTime * 7,
                            RailcraftItems.INGOT.getStack(7, steel), 7);
                    bf.addRecipe("railcraft:smelt_boots", Ingredient.fromItem(Items.IRON_BOOTS), smeltTime * 4,
                            RailcraftItems.INGOT.getStack(4, steel), 4);

                    bf.addRecipe("railcraft:smelt_horse_armor", Ingredient.fromItem(Items.IRON_HORSE_ARMOR), smeltTime * 4,
                            RailcraftItems.INGOT.getStack(4, steel), 4);

                    bf.addRecipe("railcraft:smelt_sword", Ingredient.fromItem(Items.IRON_SWORD), smeltTime * 2,
                            RailcraftItems.INGOT.getStack(2, steel), 2);
                    bf.addRecipe("railcraft:smelt_shovel", Ingredient.fromItem(Items.IRON_SHOVEL), smeltTime,
                            RailcraftItems.INGOT.getStack(1, steel), 1);
                    bf.addRecipe("railcraft:smelt_pickaxe", Ingredient.fromItem(Items.IRON_PICKAXE), smeltTime * 3,
                            RailcraftItems.INGOT.getStack(3, steel), 3);
                    bf.addRecipe("railcraft:smelt_axe", Ingredient.fromItem(Items.IRON_AXE), smeltTime * 3,
                            RailcraftItems.INGOT.getStack(3, steel), 3);
                    bf.addRecipe("railcraft:smelt_hoe", Ingredient.fromItem(Items.IRON_HOE), smeltTime * 2,
                            RailcraftItems.INGOT.getStack(2, steel), 2);
                    bf.addRecipe("railcraft:smelt_shears", Ingredient.fromItem(Items.SHEARS), smeltTime * 2,
                            RailcraftItems.INGOT.getStack(2, steel), 2);

                    bf.addRecipe("railcraft:smelt_crowbar", RailcraftItems.CROWBAR_IRON.getIngredient(), smeltTime * 3,
                            RailcraftItems.INGOT.getStack(3, steel), 3);

                    bf.addRecipe("railcraft:smelt_door", Ingredient.fromItem(Items.IRON_DOOR), smeltTime * 6,
                            RailcraftItems.INGOT.getStack(6, steel), 6);
                    bf.addRecipe("railcraft:smelt_trapdoor", Ingredient.fromItem(Item.getItemFromBlock(Blocks.IRON_TRAPDOOR)), smeltTime * 6,
                            RailcraftItems.INGOT.getStack(4, steel), 4);

                    int recycleTime = smeltTime / 2;
                    bf.addRecipe("railcraft:recycle_helmet", RailcraftItems.ARMOR_HELMET_STEEL.getIngredient(), recycleTime * 4,
                            RailcraftItems.INGOT.getStack(4, steel), 0);
                    bf.addRecipe("railcraft:recycle_chestplate", RailcraftItems.ARMOR_CHESTPLATE_STEEL.getIngredient(), recycleTime * 6,
                            RailcraftItems.INGOT.getStack(6, steel), 0);
                    bf.addRecipe("railcraft:recycle_leggings", RailcraftItems.ARMOR_LEGGINGS_STEEL.getIngredient(), recycleTime * 5,
                            RailcraftItems.INGOT.getStack(5, steel), 0);
                    bf.addRecipe("railcraft:recycle_boots", RailcraftItems.ARMOR_BOOTS_STEEL.getIngredient(), recycleTime * 3,
                            RailcraftItems.INGOT.getStack(3, steel), 0);

                    bf.addRecipe("railcraft:recycle_sword", RailcraftItems.SWORD_STEEL.getIngredient(), recycleTime,
                            RailcraftItems.INGOT.getStack(1, steel), 0);
                    bf.addRecipe("railcraft:recycle_pickaxe", RailcraftItems.PICKAXE_STEEL.getIngredient(), recycleTime * 2,
                            RailcraftItems.INGOT.getStack(2, steel), 0);
                    bf.addRecipe("railcraft:recycle_hoe", RailcraftItems.HOE_STEEL.getIngredient(), recycleTime,
                            RailcraftItems.INGOT.getStack(1, steel), 0);
                    bf.addRecipe("railcraft:recycle_axe", RailcraftItems.AXE_STEEL.getIngredient(), recycleTime * 2,
                            RailcraftItems.INGOT.getStack(2, steel), 0);
                    bf.addRecipe("railcraft:recycle_shears", RailcraftItems.SHEARS_STEEL.getIngredient(), recycleTime,
                            RailcraftItems.INGOT.getStack(1, steel), 0);
                }
                {
                    IRockCrusherCrafter rc = Crafters.rockCrusher();
                    if (EnumGeneric.CRUSHED_OBSIDIAN.isEnabled() || RailcraftItems.DUST.isEnabled()) {
                        IRockCrusherCrafter.IRecipeBuilder builder = rc.makeRecipe("railcraft:obsidian", Ingredients.from(Blocks.OBSIDIAN));
                        if (EnumGeneric.CRUSHED_OBSIDIAN.isEnabled())
                            builder.addOutput(EnumGeneric.CRUSHED_OBSIDIAN.getStack());
                        if (RailcraftItems.DUST.isEnabled()) {
                            builder.addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN), 0.25f);
                            if (!EnumGeneric.CRUSHED_OBSIDIAN.isEnabled())
                                builder.addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN));
                        }
                        builder.register();
                    }


                    if (EnumGeneric.CRUSHED_OBSIDIAN.isEnabled() && RailcraftItems.DUST.isEnabled()) {
                        rc.makeRecipe("railcraft:obsidian_crushed", Ingredients.from(EnumGeneric.CRUSHED_OBSIDIAN))
                                .addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN))
                                .addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN), 0.25f)
                                .register();
                    }


                    //TODO move to individual classes once we do split up
                    if (WorldspikeVariant.STANDARD.isAvailable()) {
                        getWorldSpikeBuilder("railcraft:recycleWorldspikeStandard", WorldspikeVariant.STANDARD.getIngredient())
                                .addOutput(new ItemStack(Items.DIAMOND), 0.5f)
                                .register();

                    }

                    if (WorldspikeVariant.PERSONAL.isAvailable()) {
                        getWorldSpikeBuilder("railcraft:recycleWorldspikePersonal", WorldspikeVariant.PERSONAL.getIngredient())
                                .addOutput(new ItemStack(Items.EMERALD), 0.5f)
                                .register();
                    }

                    if (WorldspikeVariant.PASSIVE.isAvailable()) {
                        getWorldSpikeBuilder("railcraft:recycleWorldspikePassive", WorldspikeVariant.PASSIVE.getIngredient())
                                .addOutput(new ItemStack(Blocks.PRISMARINE), 0.5f)
                                .register();
                    }

                    rc.makeRecipe("minecraft:cobblestone", Ingredients.from(Blocks.COBBLESTONE))
                            .addOutput(new ItemStack(Blocks.GRAVEL))
                            .addOutput(new ItemStack(Items.FLINT), 0.10f)
                            .register();

                    rc.makeRecipe("minecraft:cobblestone_mossy", Ingredients.from(Blocks.MOSSY_COBBLESTONE))
                            .addOutput(new ItemStack(Blocks.GRAVEL))
                            .addOutput(new ItemStack(Blocks.VINE), 0.80f)
                            .register();

                    rc.makeRecipe("minecraft:gravel", Ingredients.from(Blocks.GRAVEL))
                            .addOutput(new ItemStack(Blocks.SAND))
                            .addOutput(new ItemStack(Items.GOLD_NUGGET), 0.001f)
                            .addOutput(new ItemStack(Items.DIAMOND), 0.00005f)
                            .register();

                    rc.makeRecipe("minecraft:stone", Ingredients.from(Blocks.STONE))
                            .addOutput(new ItemStack(Blocks.COBBLESTONE))
                            .register();

                    rc.makeRecipe("minecraft:sandstone", Ingredients.from(Blocks.SANDSTONE))
                            .addOutput(new ItemStack(Blocks.SAND, 4))
                            .register();

                    rc.makeRecipe("minecraft:brick", Ingredients.from(Blocks.BRICK_BLOCK))
                            .addOutput(new ItemStack(Items.BRICK, 3))
                            .addOutput(new ItemStack(Items.BRICK), 0.5f)
                            .register();

                    rc.makeRecipe("minecraft:clay", Ingredients.from(Blocks.CLAY))
                            .addOutput(new ItemStack(Items.CLAY_BALL, 4))
                            .register();

                    rc.makeRecipe("minecraft:stonebrick", Ingredients.from(Blocks.STONEBRICK))
                            .addOutput(new ItemStack(Blocks.COBBLESTONE))
                            .register();

                    rc.makeRecipe("minecraft:stairs_stone", Ingredients.from(Blocks.STONE_STAIRS))
                            .addOutput(new ItemStack(Blocks.GRAVEL))
                            .register();

                    rc.makeRecipe("minecraft:stairs_stonebrick", Ingredients.from(Blocks.STONE_BRICK_STAIRS))
                            .addOutput(new ItemStack(Blocks.COBBLESTONE))
                            .register();

                    rc.makeRecipe("minecraft:stairs_nether", Ingredients.from(Blocks.NETHER_BRICK_STAIRS))
                            .addOutput(new ItemStack(Blocks.NETHER_BRICK))
                            .register();

                    rc.makeRecipe("minecraft:stairs_brick", Ingredients.from(Blocks.BRICK_STAIRS))
                            .addOutput(new ItemStack(Items.BRICK, 4))
                            .addOutput(new ItemStack(Items.BRICK), 0.5f)
                            .addOutput(new ItemStack(Items.BRICK), 0.5f)
                            .register();

                    rc.makeRecipe("minecraft:slab_stone", Ingredients.from(Blocks.STONE_SLAB, 0))
                            .addOutput(new ItemStack(Blocks.COBBLESTONE), 0.45f)
                            .register();

                    rc.makeRecipe("minecraft:slab_stone", Ingredients.from(Blocks.STONE_SLAB, 1))
                            .addOutput(new ItemStack(Blocks.SAND), 0.45f)
                            .register();

                    rc.makeRecipe("minecraft:slab_stone", Ingredients.from(Blocks.STONE_SLAB, 3))
                            .addOutput(new ItemStack(Blocks.GRAVEL), 0.45f)
                            .register();

                    rc.makeRecipe("minecraft:slab_stone", Ingredients.from(Blocks.STONE_SLAB, 4))
                            .addOutput(new ItemStack(Items.BRICK))
                            .addOutput(new ItemStack(Items.BRICK), 0.75f)
                            .register();

                    rc.makeRecipe("minecraft:slab_stone", Ingredients.from(Blocks.STONE_SLAB, 5))
                            .addOutput(new ItemStack(Blocks.COBBLESTONE), 0.45f)
                            .register();

                    rc.makeRecipe("minecraft:ice", Ingredients.from(Blocks.ICE))
                            .addOutput(new ItemStack(Blocks.SNOW), 0.85f)
                            .addOutput(new ItemStack(Items.SNOWBALL), 0.25f)
                            .register();

                    rc.makeRecipe("minecraft:fence_nether", Ingredients.from(Blocks.NETHER_BRICK_FENCE))
                            .addOutput(new ItemStack(Blocks.NETHER_BRICK))
                            .register();

                    rc.makeRecipe("minecraft:glowstone", Ingredients.from(Blocks.GLOWSTONE))
                            .addOutput(new ItemStack(Items.GLOWSTONE_DUST, 3))
                            .addOutput(new ItemStack(Items.GLOWSTONE_DUST), 0.75f)
                            .register();

                    rc.makeRecipe("minecraft:redstone_lamp", Ingredients.from(Blocks.REDSTONE_LAMP))
                            .addOutput(new ItemStack(Items.GLOWSTONE_DUST, 3))
                            .addOutput(new ItemStack(Items.GLOWSTONE_DUST), 0.75f)
                            .addOutput(new ItemStack(Items.REDSTONE, 3))
                            .addOutput(new ItemStack(Items.REDSTONE), 0.75f)
                            .register();

                    rc.makeRecipe("minecraft:bone", Ingredients.from(Items.BONE))
                            .addOutput(new ItemStack(Items.DYE, 4, 15))
                            .register();

                    rc.makeRecipe("minecraft:blaze_rod", Ingredients.from(Items.BLAZE_ROD))
                            .addOutput(new ItemStack(Items.BLAZE_POWDER, 2))
                            .addOutput(new ItemStack(Items.BLAZE_POWDER), 0.65f)
                            .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SULFUR), 0.5f)
                            .addOutput(new ItemStack(Items.BLAZE_POWDER), 0.25f)
                            .addOutput(new ItemStack(Items.BLAZE_POWDER), 0.25f)
                            .register();

                    //todo: Investigate if we should spawn the respective cobblestone variant from crushing ores. This would need to be applied to IC2 ores as well if done
                    rc.makeRecipe("minecraft:ore_redstone", Ingredients.from(Blocks.REDSTONE_ORE))
                            .addOutput(new ItemStack(Items.REDSTONE, 6))
                            .addOutput(new ItemStack(Items.REDSTONE, 2), 0.85f)
                            .addOutput(new ItemStack(Items.REDSTONE, 1), 0.25f)
                            .addOutput(new ItemStack(Items.GLOWSTONE_DUST), 0.1f)
                            .register();

                    rc.makeRecipe("minecraft:ore_diamond", Ingredients.from(Blocks.DIAMOND_ORE))
                            .addOutput(new ItemStack(Items.DIAMOND))
                            .addOutput(new ItemStack(Items.DIAMOND), 0.85f)
                            .addOutput(new ItemStack(Items.DIAMOND), 0.25f)
                            .addOutput(new ItemStack(Items.COAL), 0.1f)
                            .register();

                    rc.makeRecipe("railcraft:ore_diamond_dark", RailcraftBlocks.ORE.getIngredient(EnumOre.DARK_DIAMOND))
                            .addOutput(new ItemStack(Items.DIAMOND))
                            .addOutput(new ItemStack(Items.DIAMOND), 0.85f)
                            .addOutput(new ItemStack(Items.DIAMOND), 0.25f)
                            .addOutput(new ItemStack(Items.COAL), 0.1f)
                            .register();

                    rc.makeRecipe("minecraft:ore_emerald", Ingredients.from(Blocks.EMERALD_ORE))
                            .addOutput(new ItemStack(Items.EMERALD))
                            .addOutput(new ItemStack(Items.EMERALD), 0.85f)
                            .addOutput(new ItemStack(Items.EMERALD), 0.25f)
                            .register();

                    rc.makeRecipe("railcraft:ore_emerald_dark", RailcraftBlocks.ORE.getIngredient(EnumOre.DARK_EMERALD))
                            .addOutput(new ItemStack(Items.EMERALD))
                            .addOutput(new ItemStack(Items.EMERALD), 0.85f)
                            .addOutput(new ItemStack(Items.EMERALD), 0.25f)
                            .register();

                    rc.makeRecipe("minecraft:ore_lapis", Ingredients.from(Blocks.LAPIS_ORE))
                            .addOutput(new ItemStack(Items.DYE, 8, 4))
                            .addOutput(new ItemStack(Items.DYE, 1, 4), 0.85f)
                            .addOutput(new ItemStack(Items.DYE, 1, 4), 0.35f)
                            .register();

                    rc.makeRecipe("railcraft:ore_lapis_dark", RailcraftBlocks.ORE.getIngredient(EnumOre.DARK_LAPIS))
                            .addOutput(new ItemStack(Items.DYE, 8, 4))
                            .addOutput(new ItemStack(Items.DYE, 1, 4), 0.85f)
                            .addOutput(new ItemStack(Items.DYE, 1, 4), 0.35f)
                            .register();

                    if (RailcraftItems.DUST.isEnabled()) {
                        rc.makeRecipe("minecraft:coal", Ingredients.from(Items.COAL, 0))
                                .addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.COAL))
                                .register();

                        rc.makeRecipe("minecraft:ore_coal", Ingredients.from(Blocks.COAL_ORE))
                                .addOutput(RailcraftItems.DUST.getStack(2, ItemDust.EnumDust.COAL))
                                .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.COAL), 0.65f)
                                .addOutput(new ItemStack(Items.COAL), 0.15f)
                                .addOutput(new ItemStack(Items.DIAMOND), 0.001f)
                                .register();

                        rc.makeRecipe("minecraft:block_coal", Ingredients.from(Blocks.COAL_BLOCK, 0))
                                .addOutput(RailcraftItems.DUST.getStack(9, ItemDust.EnumDust.COAL))
                                .register();

                        rc.makeRecipe("minecraft:charcoal", Ingredients.from(Items.COAL, 1))
                                .addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.CHARCOAL))
                                .register();

                        rc.makeRecipe("minecraft:block_charcoal", Ingredients.from("blockCharcoal"))
                                .addOutput(RailcraftItems.DUST.getStack(9, ItemDust.EnumDust.CHARCOAL))
                                .register();

                        rc.makeRecipe("minecraft:ender_pearl", Ingredient.fromItem(Items.ENDER_PEARL))
                                .addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.ENDER))
                                .register();

                        rc.makeRecipe("railcraft:ore_sulfur", RailcraftBlocks.ORE.getIngredient(EnumOre.SULFUR))
                                .addOutput(RailcraftItems.DUST.getStack(5, ItemDust.EnumDust.SULFUR))
                                .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SULFUR), 0.85f)
                                .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SULFUR), 0.35f)
                                .register();

                        rc.makeRecipe("railcraft:ore_saltpeter", RailcraftBlocks.ORE.getIngredient(EnumOre.SALTPETER))
                                .addOutput(RailcraftItems.DUST.getStack(3, ItemDust.EnumDust.SALTPETER))
                                .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SALTPETER), 0.85f)
                                .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SALTPETER), 0.35f)
                                .register();
                    }
                }

                //TODO class declaration
//                EnumMachineBeta metalsChest = EnumMachineBeta.METALS_CHEST;
//                if (metalsChest.isAvailable())
//                    CraftingPlugin.addRecipe(metalsChest.getStack(),
//                            "GPG",
//                            "PAP",
//                            "GPG",
//                            'A', new ItemStack(Blocks.ANVIL),
//                            'P', new ItemStack(Blocks.PISTON),
//                            'G', "gearSteel");
                if (RailcraftModuleManager.isModuleEnabled(ModuleStructures.class)) {
                    if (RailcraftBlocks.BLAST_FURNACE.isLoaded() && BrickTheme.INFERNAL.getBlock() != null) {

                        ItemStack stack = RailcraftBlocks.BLAST_FURNACE.getStack(4);
                        CraftingPlugin.addRecipe(stack,
                                " B ",
                                "BPB",
                                " B ",
                                'B', BrickTheme.INFERNAL.getStack(1, BrickVariant.BRICK),
                                'P', Items.MAGMA_CREAM);
                    }
                    if (RailcraftBlocks.COKE_OVEN.isLoaded() && BrickTheme.SANDY.getBlock() != null) {
                        ItemStack stack = RailcraftBlocks.COKE_OVEN.getStack();
                        CraftingPlugin.addRecipe(stack,
                                " B ",
                                " S ",
                                " B ",
                                'B', BrickTheme.SANDY.getStack(1, BrickVariant.BRICK),
                                'S', "sand");
                    }
                }

                if (EnumGeneric.BLOCK_COKE.isEnabled()) {
                    Crafters.cokeOven().addRecipe("railcraft:coke_block", Ingredients.from(Blocks.COAL_BLOCK),
                            EnumGeneric.BLOCK_COKE.getStack(), Fluids.CREOSOTE.get(COKE_COOK_CREOSOTE * 9), COKE_COOK_TIME * 9);
                    ItemStack stack = EnumGeneric.BLOCK_COKE.getStack();
                    CraftingPlugin.addRecipe(stack,
                            "CCC",
                            "CCC",
                            "CCC",
                            'C', RailcraftItems.COKE);
                    CraftingPlugin.addShapelessRecipe(RailcraftItems.COKE.getStack(9), stack);
                }
            }

            private IRockCrusherCrafter.IRecipeBuilder getWorldSpikeBuilder(String name, Ingredient ingredient) {
                IRockCrusherCrafter.IRecipeBuilder builder = Crafters.rockCrusher().makeRecipe(name, ingredient);
                if (EnumGeneric.CRUSHED_OBSIDIAN.isEnabled()) {
                    builder.addOutput(EnumGeneric.CRUSHED_OBSIDIAN.getStack());
                    builder.addOutput(EnumGeneric.CRUSHED_OBSIDIAN.getStack(), 0.5f);
                } else {
                    builder.addOutput(new ItemStack(Blocks.OBSIDIAN));
                    builder.addOutput(new ItemStack(Blocks.OBSIDIAN), 0.5f);
                }
                builder.addOutput(new ItemStack(Blocks.OBSIDIAN), 0.25f);
                if (RailcraftItems.DUST.isEnabled()) {
                    builder.addOutput(RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN), 0.25f);
                }
                builder.addOutput(new ItemStack(Items.GOLD_NUGGET, 16));
                builder.addOutput(new ItemStack(Items.GOLD_NUGGET, 8), 0.5f);
                builder.addOutput(new ItemStack(Items.GOLD_NUGGET, 8), 0.5f);
                builder.addOutput(new ItemStack(Items.GOLD_NUGGET, 4), 0.5f);
                return builder;
            }

            private void registerCrushedOreRecipe(Ingredient ore, ItemStack dust) {
                if (InvTools.isEmpty(dust))
                    return;

                Crafters.rockCrusher().makeRecipe("ic2:crushedOre", ore)
                        .addOutput(InvTools.copy(dust, 2))
                        .register();
            }

            @Override
            public void postInit() {
                BlastFurnaceCrafter.INSTANCE.postInit();
                //TODO this is not right
                if (!EquipmentVariant.ROLLING_MACHINE_POWERED.isAvailable())
                    RollingMachineCraftingManager.copyRecipesToWorkbench();
                if (!RailcraftBlocks.BLAST_FURNACE.isEnabled() || RailcraftConfig.forceEnableSteelRecipe())
                    registerAltSteelFurnaceRecipe();

                String[] logs = {"logWood", "woodRubber"};
                for (String oreTag : logs) {
                    Crafters.cokeOven().addRecipe("railcraft:" + oreTag, Ingredients.from(oreTag),
                            new ItemStack(Items.COAL, 1, 1), Fluids.CREOSOTE.get(250), COKE_COOK_TIME);
                }

                if (Mod.FORESTRY.isLoaded()) {
                    Crafters.rockCrusher().makeRecipe("forestry:apatite", Ingredient.fromStacks(ModItems.APATITE_ORE.getStack()))
                            .addOutput(ModItems.APATITE.getStack(4))
                            .addOutput(ModItems.APATITE.getStack(), 0.85f)
                            .addOutput(ModItems.APATITE.getStack(), 0.25f)
                            .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SULFUR), 0.2f)
                            .addOutput(RailcraftItems.DUST.getStack(1, ItemDust.EnumDust.SALTPETER), 0.05f)
                            .register();
                }

                if (Mod.anyLoaded(Mod.IC2, Mod.IC2_CLASSIC)) {
                    boolean classic = Mod.IC2_CLASSIC.isLoaded();
                    ItemStack crushedIron = classic ? ModItems.DUST_IRON.getStack() : ModItems.CRUSHED_IRON.getStack();
                    ItemStack crushedGold = classic ? ModItems.DUST_GOLD.getStack() : ModItems.CRUSHED_GOLD.getStack();
                    ItemStack crushedCopper = classic ? ModItems.DUST_COPPER.getStack() : ModItems.CRUSHED_COPPER.getStack();
                    ItemStack crushedTin = classic ? ModItems.DUST_TIN.getStack() : ModItems.CRUSHED_TIN.getStack();
                    ItemStack crushedSilver = classic ? ModItems.DUST_SILVER.getStack() : ModItems.CRUSHED_SILVER.getStack();
                    ItemStack crushedLead = ModItems.CRUSHED_LEAD.getStack();
                    ItemStack crushedUranium = classic ? ModItems.URANIUM_DROP.getStack() : ModItems.CRUSHED_URANIUM.getStack();

                    if (RailcraftConfig.canCrushOres()) {
                        registerCrushedOreRecipe(Ingredients.from(Blocks.IRON_ORE), crushedIron);
                        registerCrushedOreRecipe(Ingredients.from(Blocks.GOLD_ORE), crushedGold);

                        registerCrushedOreRecipe(Ingredients.from("oreCopper"), crushedCopper);
                        registerCrushedOreRecipe(Ingredients.from("oreTin"), crushedTin);
                        registerCrushedOreRecipe(Ingredients.from("oreSilver"), crushedSilver);
                        registerCrushedOreRecipe(Ingredients.from("oreLead"), crushedLead);
                        registerCrushedOreRecipe(Ingredients.from("oreUranium"), crushedUranium);
                    }

                    if (RailcraftConfig.getRecipeConfig("ic2.macerator.ores")) {

                        ItemStack firestoneOre = EnumOreMagic.FIRESTONE.getStack();
                        ItemStack firestoneRaw = RailcraftItems.FIRESTONE_RAW.getStack();
                        IC2Plugin.addMaceratorRecipe(firestoneOre, firestoneRaw);

                        List<ItemStack> ores = OreDictionary.getOres("orePoorCopper");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedCopper, 2);
                        }

                        ores = OreDictionary.getOres("orePoorTin");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedTin, 2);
                        }

                        ores = OreDictionary.getOres("orePoorIron");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedIron, 2);
                        }

                        ores = OreDictionary.getOres("orePoorGold");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedGold, 2);
                        }

                        ores = OreDictionary.getOres("orePoorSilver");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedSilver, 2);
                        }

                        ores = OreDictionary.getOres("orePoorLead");
                        for (ItemStack ore : ores) {
                            IC2Plugin.addMaceratorRecipe(ore, 3, crushedLead, 2);
                        }
                    } else {
                        IC2Plugin.removeMaceratorDustRecipes(crushedIron, crushedGold, crushedCopper, crushedTin, crushedSilver, crushedLead, crushedUranium);
                    }
                }
            }
        });
        setDisabledEventHandler(new ModuleEventHandler() {
            @Override
            public void postInit() {
                RollingMachineCraftingManager.copyRecipesToWorkbench();
                registerAltSteelFurnaceRecipe();
            }
        });
    }

    void registerAltSteelFurnaceRecipe() {
        CraftingPlugin.addFurnaceRecipe(new ItemStack(Items.IRON_NUGGET, 1), RailcraftItems.NUGGET.getStack(Metal.STEEL), 0);
    }
}
