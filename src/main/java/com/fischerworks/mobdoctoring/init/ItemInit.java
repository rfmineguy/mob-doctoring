package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.items.*;
import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import com.fischerworks.mobdoctoring.util.SerumType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class  ItemInit {
    public static final DeferredRegister<Item> ITEMS_REG = DeferredRegister.create(ForgeRegistries.ITEMS, MobDoctoring.MOD_ID);

    //items
    public static final RegistryObject<Item> COMMON_SPECIMEN     = ITEMS_REG.register("specimen_common",     () -> new Item(new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> DANGEROUS_SPECIMEN  = ITEMS_REG.register("specimen_dangerous",  () -> new Item(new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> VIOLENT_SPECIMEN    = ITEMS_REG.register("specimen_violent",    () -> new Item(new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> IMPOSSIBLE_SPECIMEN = ITEMS_REG.register("specimen_impossible", () -> new Item(new Item.Properties().group(MobDoctoring.TAB)));

    public static final RegistryObject<Item> COMMON_ESSENCE     = ITEMS_REG.register("essence_common",     () -> new EssenseBottleItem(MobDoctoringTier.COMMON, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> DANGEROUS_ESSENCE  = ITEMS_REG.register("essence_dangerous",  () -> new EssenseBottleItem(MobDoctoringTier.DANGEROUS, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> VIOLENT_ESSENCE    = ITEMS_REG.register("essence_violent",    () -> new EssenseBottleItem(MobDoctoringTier.VIOLENT, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<Item> IMPOSSIBLE_ESSENCE = ITEMS_REG.register("essence_impossible", () -> new EssenseBottleItem(MobDoctoringTier.IMPOSSIBLE, new Item.Properties().group(MobDoctoring.TAB)));

    public static final RegistryObject<Item> COMMON_HANDLE     = ITEMS_REG.register("handle_common",     () -> new Item(new Item.Properties().group(MobDoctoring.TAB).maxStackSize(1)));
    public static final RegistryObject<Item> DANGEROUS_HANDLE  = ITEMS_REG.register("handle_dangerous",  () -> new Item(new Item.Properties().group(MobDoctoring.TAB).maxStackSize(1)));
    public static final RegistryObject<Item> VIOLENT_HANDLE    = ITEMS_REG.register("handle_violent",    () -> new Item(new Item.Properties().group(MobDoctoring.TAB).maxStackSize(1)));
    public static final RegistryObject<Item> IMPOSSIBLE_HANDLE = ITEMS_REG.register("handle_impossible", () -> new Item(new Item.Properties().group(MobDoctoring.TAB).maxStackSize(1)));

    public static final RegistryObject<Item>      CATALYST_DUST = ITEMS_REG.register("catalyst_dust", () -> new Item(new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<BlockItem> CATALYST_ORE  = ITEMS_REG.register("catalyst_ore", () -> new BlockItem(BlockInit.CATALYST_ORE.get(), new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<BlockItem> MOB_CENTRIFUGE  = ITEMS_REG.register("block_mob_cauldron", () -> new BlockItem(BlockInit.MOB_CENTRIFUGE.get(), new Item.Properties().group(MobDoctoring.TAB)));

    //serum items
    //change base to Item()
    public static final RegistryObject<Item> ANTIBODIES_BASE   = ITEMS_REG.register("antibodies_base", () -> new SerumItem(SerumType.ANTIBODY_BASE, new Item.Properties().group(MobDoctoring.TAB).maxStackSize(1)));
    public static final RegistryObject<Item> ANTIBODIES        = registerSerumItem("antibodies", SerumType.ANTIBODY);
    public static final RegistryObject<Item> UNDEAD_SERUM      = registerSerumItem("serum_undead", SerumType.UNDEAD);
    public static final RegistryObject<Item> ARACHNID_SERUM    = registerSerumItem("serum_arachnid", SerumType.ARACHNIDS);
    public static final RegistryObject<Item> BONES_SERUM       = registerSerumItem("serum_bones", SerumType.BONES);
    public static final RegistryObject<Item> CREEPY_SERUM      = registerSerumItem("serum_creepy", SerumType.CREEPY);
    public static final RegistryObject<Item> ENDER_SERUM       = registerSerumItem("serum_ender", SerumType.ENDER);
    public static final RegistryObject<Item> GHAST_SERUM       = registerSerumItem("serum_ghast", SerumType.GHAST);
    public static final RegistryObject<Item> GUARDIAN_SERUM    = registerSerumItem("serum_guardian", SerumType.GUARDIAN);
    public static final RegistryObject<Item> MAGMA_SERUM       = registerSerumItem("serum_magma", SerumType.MAGMA);
    public static final RegistryObject<Item> PIGLIN_SERUM      = registerSerumItem("serum_piglin", SerumType.PIGLIN);
    public static final RegistryObject<Item> BLAZE_SERUM       = registerSerumItem("serum_blaze", SerumType.BLAZE);
    public static final RegistryObject<Item> SLIME_SERUM       = registerSerumItem("serum_slime", SerumType.SLIME);
    public static final RegistryObject<Item> SHULK_SERUM       = registerSerumItem("serum_shulk", SerumType.SHULK);


    //syringe items
    public static final RegistryObject<Item> EMPTY_SYRINGE      = registerSyringeItem("syringe_empty", SerumType.NONE);
    public static final RegistryObject<Item> ANTIBODY_SYRINGE   = registerSyringeItem("syringe_antibodies", SerumType.ANTIBODY);
    public static final RegistryObject<Item> UNDEAD_SYRINGE     = registerSyringeItem("syringe_undead", SerumType.UNDEAD);
    public static final RegistryObject<Item> ARACHNID_SYRINGE   = registerSyringeItem("syringe_arachnid", SerumType.ARACHNIDS);
    public static final RegistryObject<Item> BONES_SYRINGE      = registerSyringeItem("syringe_bones", SerumType.BONES);
    public static final RegistryObject<Item> CREEPY_SYRINGE     = registerSyringeItem("syringe_creepy", SerumType.CREEPY);
    public static final RegistryObject<Item> ENDER_SYRINGE      = registerSyringeItem("syringe_ender", SerumType.ENDER);
    public static final RegistryObject<Item> GHAST_SYRINGE      = registerSyringeItem("syringe_ghast", SerumType.GHAST);
    public static final RegistryObject<Item> GUARDIAN_SYRINGE   = registerSyringeItem("syringe_guardian", SerumType.GUARDIAN);
    public static final RegistryObject<Item> MAGMA_SYRINGE      = registerSyringeItem("syringe_magma", SerumType.MAGMA);
    public static final RegistryObject<Item> PIGLIN_SYRINGE     = registerSyringeItem("syringe_piglin", SerumType.PIGLIN);
    public static final RegistryObject<Item> BLAZE_SYRINGE      = registerSyringeItem("syringe_blaze", SerumType.BLAZE);
    public static final RegistryObject<Item> SLIME_SYRINGE      = registerSyringeItem("syringe_slime", SerumType.SLIME);
    public static final RegistryObject<Item> SHULK_SYRINGE      = registerSyringeItem("syringe_shulk", SerumType.SHULK);

    //swords
    public static final RegistryObject<MobSwordItem> COMMON_SWORD = ITEMS_REG.register("sword_common", () -> new MobSwordItem(MobDoctoringTier.COMMON, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobSwordItem> DANGEROUS_SWORD = ITEMS_REG.register("sword_dangerous", () -> new MobSwordItem(MobDoctoringTier.DANGEROUS, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobSwordItem> VIOLENT_SWORD = ITEMS_REG.register("sword_violent", () -> new MobSwordItem(MobDoctoringTier.VIOLENT, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobSwordItem> IMPOSSIBLE_SWORD = ITEMS_REG.register("sword_impossible", () -> new MobSwordItem(MobDoctoringTier.IMPOSSIBLE, new Item.Properties().group(MobDoctoring.TAB)));
//    public static final RegistryObject<SwordTest> COMMON_SWORD = ITEMS_REG.register("sword_common", () -> new SwordTest(MobDoctoringTier.COMMON, new Item.Properties().group(MobDoctoring.TAB)));
//    public static final RegistryObject<SwordTest> DANGEROUS_SWORD = ITEMS_REG.register("sword_dangerous", () -> new SwordTest(MobDoctoringTier.DANGEROUS, new Item.Properties().group(MobDoctoring.TAB)));
//    public static final RegistryObject<SwordTest> VIOLENT_SWORD = ITEMS_REG.register("sword_violent", () -> new SwordTest(MobDoctoringTier.VIOLENT, new Item.Properties().group(MobDoctoring.TAB)));
//    public static final RegistryObject<SwordTest> IMPOSSIBLE_SWORD = ITEMS_REG.register("sword_impossible", () -> new SwordTest(MobDoctoringTier.IMPOSSIBLE, new Item.Properties().group(MobDoctoring.TAB)));

    //axes
    public static final RegistryObject<MobAxeItem> COMMON_AXE = ITEMS_REG.register("axe_common", () -> new MobAxeItem(MobDoctoringTier.COMMON, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobAxeItem> DANGEROUS_AXE = ITEMS_REG.register("axe_dangerous", () -> new MobAxeItem(MobDoctoringTier.DANGEROUS, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobAxeItem> VIOLENT_AXE = ITEMS_REG.register("axe_violent", () -> new MobAxeItem(MobDoctoringTier.VIOLENT, new Item.Properties().group(MobDoctoring.TAB)));
    public static final RegistryObject<MobAxeItem> IMPOSSIBLE_AXE = ITEMS_REG.register("axe_impossible", () -> new MobAxeItem(MobDoctoringTier.IMPOSSIBLE, new Item.Properties().group(MobDoctoring.TAB)));


    private static RegistryObject<Item> registerSerumItem(String name, SerumType type) {
        return ITEMS_REG.register(name, () -> new SerumItem(type, new Item.Properties().group(MobDoctoring.TAB)));
    }

    private static RegistryObject<Item> registerSyringeItem(String name, SerumType type) {
        return ITEMS_REG.register(name, () -> new SyringeItem(type, new Item.Properties().group(MobDoctoring.TAB)));
    }
}