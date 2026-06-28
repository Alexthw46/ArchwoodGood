package com.alexthw.archwood_good.worldgen.trees;

import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.worldgen.AWGConfiguredFeatures;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.world.tree.MagicTree;
import net.minecraft.world.level.block.grower.TreeGrower;

public class AWGTreeGrowers {

    public static final TreeGrower ORANGE_ARCHWOOD_TREE = MagicTree.getGrower(ArchwoodGood.res("orange_archwood_tree").toString(), AWGConfiguredFeatures.ORANGE_SAPLING_TREE_KEY);

    public static final TreeGrower WHITE_ARCHWOOD_TREE = MagicTree.getGrower(ArchwoodGood.res("white_archwood_tree").toString(), AWGConfiguredFeatures.WHITE_SAPLING_TREE_KEY);

    public static final TreeGrower FADING_ARCHWOOD_TREE = MagicTree.getGrower(ArsNouveau.prefix("fading_archwood_tree").toString(), AWGConfiguredFeatures.FADING_SAPLING_TREE_KEY);

}
